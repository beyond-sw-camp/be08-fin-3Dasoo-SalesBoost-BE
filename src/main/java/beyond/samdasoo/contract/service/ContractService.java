package beyond.samdasoo.contract.service;

import beyond.samdasoo.contract.dto.ContractRequestDto;
import beyond.samdasoo.contract.dto.ContractResponseDto;
import beyond.samdasoo.contract.entity.Contract;
import beyond.samdasoo.contract.repository.ContractRepository;
import beyond.samdasoo.estimate.entity.Estimate;
import beyond.samdasoo.estimate.repository.EstimateRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContractService {

    private final ContractRepository contractRepository;
    private final EstimateRepository estimateRepository;

    // 모든 계약 조회
    public List<ContractResponseDto> getAllContracts() {
        return contractRepository.findAll()
                .stream()
                .map(ContractResponseDto::new)
                .collect(Collectors.toList());
    }

    // 계약 조회 (단일)
    public ContractResponseDto getContract(Long contractNo) {
        return contractRepository.findById(contractNo)
                .map(ContractResponseDto::new)
                .orElseThrow(() -> new IllegalArgumentException("해당 계약 조회 불가: " + contractNo));
    }

    @Transactional
    public ContractResponseDto createContract(ContractRequestDto requestDto) {
        Estimate estimate = estimateRepository.findById(requestDto.getEstimateNo())
                .orElseThrow(() -> new EntityNotFoundException("해당 견적 조회 불가: " + requestDto.getEstimateNo()));

        Contract contract = Contract.builder()
                .name(requestDto.getName())
                .contractDate(requestDto.getContractDate())
                .startDate(requestDto.getStartDate())
                .endDate(requestDto.getEndDate())
                .taxCls(requestDto.getTaxCls())
                .surtaxYn(requestDto.getSurtaxYn())
                .productCount(requestDto.getProductCount())
                .supplyPrice(requestDto.getSupplyPrice())
                .tax(requestDto.getTax())
                .totalPrice(requestDto.getTotalPrice())
                .paymentTerms(requestDto.getPaymentTerms())
                .warrenty(requestDto.getWarrenty())
                .contractCls(requestDto.getContractCls())
                .expectedArrivalDate(requestDto.getExpectedArrivalDate())
                .arrivalNotificationYn(requestDto.getArrivalNotificationYn())
                .arrivalNotificationDay(requestDto.getArrivalNotificationDay())
                .renewalNotificationYn(requestDto.getRenewalNotificationYn())
                .renewalNotificationDay(requestDto.getRenewalNotificationDay())
                .note(requestDto.getNote())
                .estimate(estimate)
                .build();

        Contract savedContract = contractRepository.save(contract);
        return new ContractResponseDto(savedContract);
    }

    // 계약 수정
    @Transactional
    public ContractResponseDto updateContract(Long contractNo, ContractRequestDto requestDto) {
        Contract contract = contractRepository.findById(contractNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 계약 내용을 찾을 수 없음: " + contractNo));
        Estimate estimate = estimateRepository.findById(requestDto.getEstimateNo())
                .orElseThrow(() -> new EntityNotFoundException("해당 견적 조회 불가: " + requestDto.getEstimateNo()));

        contract = Contract.builder()
                .contractNo(contract.getContractNo())  // 기존 계약번호 유지
                .name(requestDto.getName())
                .contractDate(requestDto.getContractDate())
                .startDate(requestDto.getStartDate())
                .endDate(requestDto.getEndDate())
                .taxCls(requestDto.getTaxCls())
                .surtaxYn(requestDto.getSurtaxYn())
                .productCount(requestDto.getProductCount())
                .supplyPrice(requestDto.getSupplyPrice())
                .tax(requestDto.getTax())
                .totalPrice(requestDto.getTotalPrice())
                .paymentTerms(requestDto.getPaymentTerms())
                .warrenty(requestDto.getWarrenty())
                .contractCls(requestDto.getContractCls())
                .expectedArrivalDate(requestDto.getExpectedArrivalDate())
                .arrivalNotificationYn(requestDto.getArrivalNotificationYn())
                .arrivalNotificationDay(requestDto.getArrivalNotificationDay())
                .renewalNotificationYn(requestDto.getRenewalNotificationYn())
                .renewalNotificationDay(requestDto.getRenewalNotificationDay())
                .note(requestDto.getNote())
                .estimate(estimate)
                .build();

        Contract updatedContract = contractRepository.save(contract);
        return new ContractResponseDto(updatedContract);
    }

    // 계약 삭제
    @Transactional
    public void deleteContract(Long contractNo) {
        contractRepository.deleteById(contractNo);
    }
}
