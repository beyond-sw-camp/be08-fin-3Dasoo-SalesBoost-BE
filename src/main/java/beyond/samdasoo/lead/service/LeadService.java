package beyond.samdasoo.lead.service;

import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.customer.entity.Customer;
import beyond.samdasoo.customer.repository.CustomerRepository;
import beyond.samdasoo.lead.Entity.Lead;
import beyond.samdasoo.lead.dto.LeadRequestDto;
import beyond.samdasoo.lead.dto.LeadResponseDto;
import beyond.samdasoo.lead.repository.LeadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static beyond.samdasoo.common.response.BaseResponseStatus.CUSTOMER_NOT_EXIST;
import static beyond.samdasoo.common.response.BaseResponseStatus.LEAD_NOT_EXIST;

@Service
@RequiredArgsConstructor
public class LeadService {
    private final LeadRepository leadRepository;
    private final CustomerRepository CustomerRepository;

    public Lead findLeadById(Long no) {
        return leadRepository.findById(no)
                .orElseThrow(() -> new BaseException(LEAD_NOT_EXIST));
    }

    private Customer findCustomerById(Long no) {
        return CustomerRepository.findById(no)
                .orElseThrow(() -> new BaseException(CUSTOMER_NOT_EXIST));
    }

    public List<LeadResponseDto> getAllLeads() {
        return leadRepository.findAll().stream()
                .map(LeadResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LeadResponseDto getLeadById(Long no) {
        return new LeadResponseDto(findLeadById(no));
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
                .startDate(leadRequestDto.getStartDate())
                .endDate(leadRequestDto.getEndDate())
                .awarePath(leadRequestDto.getAwarePath())
                .note(leadRequestDto.getNote())
                .customer(customer)
                .build();

        Lead savedLead = leadRepository.save(lead);

        return new LeadResponseDto(savedLead);
    }

    @Transactional
    public void updateLead(Long no, LeadRequestDto leadRequestDto) {
        Lead lead = findLeadById(no);
        Customer customer = findCustomerById(leadRequestDto.getCustNo());

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
    }

    public void deleteLead(Long no) {
        leadRepository.delete(findLeadById(no));
    }
}
