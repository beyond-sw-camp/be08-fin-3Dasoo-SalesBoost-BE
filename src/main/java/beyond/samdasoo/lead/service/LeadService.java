package beyond.samdasoo.lead.service;

import beyond.samdasoo.admin.entity.Process;
import beyond.samdasoo.admin.entity.SubProcess;
import beyond.samdasoo.admin.repository.ProcessRepository;
import beyond.samdasoo.admin.repository.SubProcessRepository;
import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.customer.entity.Customer;
import beyond.samdasoo.customer.repository.CustomerRepository;
import beyond.samdasoo.lead.Entity.Lead;
import beyond.samdasoo.lead.Entity.QLead;
import beyond.samdasoo.lead.Entity.Step;
import beyond.samdasoo.lead.dto.LeadRequestDto;
import beyond.samdasoo.lead.dto.LeadResponseDto;
import beyond.samdasoo.lead.dto.LeadSearchCond;
import beyond.samdasoo.lead.repository.LeadRepository;
import beyond.samdasoo.lead.repository.StepRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static beyond.samdasoo.common.response.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class LeadService {
    private final JPAQueryFactory queryFactory;
    private final LeadRepository leadRepository;
    private final CustomerRepository customerRepository;
    private final ProcessRepository processRepository;
    private final SubProcessRepository subProcessRepository;
    private final StepRepository stepRepository;

    public Lead findLeadById(Long no) {
        return leadRepository.findById(no)
                .orElseThrow(() -> new BaseException(LEAD_NOT_EXIST));
    }

    private Customer findCustomerById(Long no) {
        return customerRepository.findById(no)
                .orElseThrow(() -> new BaseException(CUSTOMER_NOT_EXIST));
    }

    public List<LeadResponseDto> getAllLeads() {
        return leadRepository.findAll().stream()
                .map(LeadResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<LeadResponseDto> getFilteredLeads(LeadSearchCond searchCond) {
        QLead lead = QLead.lead;

        BooleanBuilder builder = new BooleanBuilder();

        if (searchCond.getStatus() != null) {
            builder.and(lead.status.eq(searchCond.getStatus()));
        }

        if (searchCond.getProcess() != null && searchCond.getProcess() > 0) {
            builder.and(lead.process.eq(searchCond.getProcess()));
        }

        if (searchCond.getSubProcess() != null && searchCond.getSubProcess() > 0) {
            builder.and(lead.subProcess.eq(searchCond.getSubProcess()));

        }

        if (searchCond.getStartDate() != null) {
            builder.and(lead.startDate.goe(searchCond.getStartDate()));
        }

        if (searchCond.getEndDate() != null) {
            builder.and(lead.endDate.loe(searchCond.getEndDate()));
        }

        List<Lead> leads = queryFactory
                .selectFrom(lead)
                .where(builder)  // 동적 조건
                .fetch();

        return leads.stream()
                .map(LeadResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LeadResponseDto getLeadById(Long no) {
        Lead lead = leadRepository.findByIdWithSteps(no)
                .orElseThrow(() -> new BaseException(LEAD_NOT_EXIST));

        return new LeadResponseDto(lead);
    }

    public LeadResponseDto createLead(LeadRequestDto leadRequestDto) {
        Customer customer = findCustomerById(leadRequestDto.getCustNo());

        Lead lead = Lead.builder()
                .name(leadRequestDto.getName())
                .status(leadRequestDto.getStatus())
                .expSales(leadRequestDto.getExpSales())
                .expMargin(leadRequestDto.getExpMargin())
                .expProfit(leadRequestDto.getExpProfit())
                .process(leadRequestDto.getProcess())
                .subProcess(leadRequestDto.getSubProcess())
                .successPer(leadRequestDto.getSuccessPer())
                .startDate(leadRequestDto.getStartDate())
                .endDate(leadRequestDto.getEndDate())
                .awarePath(leadRequestDto.getAwarePath())
                .note(leadRequestDto.getNote())
                .customer(customer)
                .build();

        Lead savedLead = leadRepository.save(lead);

        // 단계 생성
        createSteps(leadRequestDto.getProcess(), leadRequestDto.getSubProcess(), savedLead);

        return new LeadResponseDto(savedLead);
    }

    @Transactional
    public void updateLead(Long no, LeadRequestDto leadRequestDto) {
        Lead lead = findLeadById(no);
        Customer customer = findCustomerById(leadRequestDto.getCustNo());

        Long orgProcess = lead.getProcess();
        Long orgSubProcess = lead.getSubProcess();

        lead.setCustomer(customer);
        Optional.ofNullable(leadRequestDto.getName()).ifPresent(lead::setName);
        Optional.ofNullable(leadRequestDto.getStatus()).ifPresent(lead::setStatus);
        Optional.of(leadRequestDto.getExpSales()).ifPresent(lead::setExpSales);
        Optional.of(leadRequestDto.getExpMargin()).ifPresent(lead::setExpMargin);
        Optional.of(leadRequestDto.getExpProfit()).ifPresent(lead::setExpProfit);
        Optional.of(leadRequestDto.getProcess()).ifPresent(lead::setProcess);
        Optional.of(leadRequestDto.getSubProcess()).ifPresent(lead::setSubProcess);
        Optional.ofNullable(leadRequestDto.getStartDate()).ifPresent(lead::setStartDate);
        Optional.ofNullable(leadRequestDto.getEndDate()).ifPresent(lead::setEndDate);
        Optional.ofNullable(leadRequestDto.getAwarePath()).ifPresent(lead::setAwarePath);
        Optional.ofNullable(leadRequestDto.getNote()).ifPresent(lead::setNote);

        // 프로세스 번호를 수정하면 그전 step 데이터 삭제후 그 프로세스에 맞게 재생성
        if (leadRequestDto.getProcess() != null && !orgProcess.equals(lead.getProcess())) {
            Optional.of(leadRequestDto.getProcess()).ifPresent(lead::setProcess);
            Optional.of(leadRequestDto.getSubProcess()).ifPresent(lead::setSubProcess);

            createSteps(leadRequestDto.getProcess(), leadRequestDto.getSubProcess(), lead);
        }
    }

    public void deleteLead(Long no) {
        leadRepository.delete(findLeadById(no));
    }

    public void createSteps(Long processNo, Long subProcessNo, Lead lead) {
        Process process = processRepository.findById(processNo)
                .orElseThrow(() -> new BaseException(PROCESS_NOT_EXIST));
        List<SubProcess> subProcesses = subProcessRepository.findByProcess(process);

        if (!stepRepository.findByLead(lead).isEmpty()) {
            stepRepository.deleteByLead(lead);
        }

        int level = 0;

        for (SubProcess subProcess : subProcesses) {
            Step step = Step.builder()
                    .level(level++)
                    .completeYn(subProcess.getSubProcessNo() <= subProcessNo ? "Y" : "N")
                    .completeDate(subProcess.getSubProcessNo() <= subProcessNo ? LocalDate.now() : null)
                    .lead(lead)
                    .subProcess(subProcess)
                    .build();

            stepRepository.save(step);
        }
    }
}
