package beyond.samdasoo.contract.service;

import beyond.samdasoo.contract.dto.ContractRequestDto;
import beyond.samdasoo.contract.dto.ContractResponseDto;
import beyond.samdasoo.contract.entity.Contract;
import beyond.samdasoo.contract.repository.ContractRepository;
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
        Contract contract = new Contract(
                requestDto.getName(),
                requestDto.getContractDate(),
                requestDto.getStartDate(),
                requestDto.getEndDate(),
                requestDto.getTaxCls(),
                requestDto.getSurtaxYn(),
                requestDto.getProductCount(),
                requestDto.getSupplyPrice(),
                requestDto.getTax(),
                requestDto.getTotalPrice(),
                requestDto.getPaymentTerms(),
                requestDto.getWarrenty(),
                requestDto.getContractCls(),
                requestDto.getExpectedArrivalDate(),
                requestDto.getArrivalNotificationYn(),
                requestDto.getArrivalNotificationDay(),
                requestDto.getRenewalNotificationYn(),
                requestDto.getRenewalNotificationDay(),
                requestDto.getNote(),
                requestDto.getEstimateNo()
        );
        Contract savedContract = contractRepository.save(contract);
        return new ContractResponseDto(savedContract);
    }

    @Transactional
    public ContractResponseDto updateContract(Long contractNo, ContractRequestDto requestDto) {
        Contract contract = contractRepository.findById(contractNo)
                .orElseThrow(() -> new IllegalArgumentException("Contract not found with id: " + contractNo));

        // 새로운 값으로 엔티티 필드 업데이트
        contract = new Contract(
                requestDto.getName(),
                requestDto.getContractDate(),
                requestDto.getStartDate(),
                requestDto.getEndDate(),
                requestDto.getTaxCls(),
                requestDto.getSurtaxYn(),
                requestDto.getProductCount(),
                requestDto.getSupplyPrice(),
                requestDto.getTax(),
                requestDto.getTotalPrice(),
                requestDto.getPaymentTerms(),
                requestDto.getWarrenty(),
                requestDto.getContractCls(),
                requestDto.getExpectedArrivalDate(),
                requestDto.getArrivalNotificationYn(),
                requestDto.getArrivalNotificationDay(),
                requestDto.getRenewalNotificationYn(),
                requestDto.getRenewalNotificationDay(),
                requestDto.getNote(),
                requestDto.getEstimateNo()
        );

        Contract updatedContract = contractRepository.save(contract);
        return new ContractResponseDto(updatedContract);
    }

    @Transactional // (2) 삭제 시 트랜잭션 처리를 위해 추가
    public void deleteContract(Long contractNo) {
        contractRepository.deleteById(contractNo);
    }
}
