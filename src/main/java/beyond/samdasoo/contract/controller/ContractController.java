package beyond.samdasoo.contract.controller;

import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.contract.dto.ContractRequestDto;
import beyond.samdasoo.contract.dto.ContractResponseDto;
import beyond.samdasoo.contract.service.ContractService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Contract APIs", description = "계약 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/contract")
public class ContractController {

    private final ContractService contractService;

    @GetMapping
    public BaseResponse<List<ContractResponseDto>> getContracts() {
        List<ContractResponseDto> contracts = contractService.getAllContracts();
        return new BaseResponse<>(contracts);
    }

    @GetMapping("/{no}")
    public BaseResponse<ContractResponseDto> getContract(@PathVariable("no") Long no) {
        ContractResponseDto contract = contractService.getContract(no);
        return new BaseResponse<>(contract);
    }

    @PostMapping
    public BaseResponse<ContractResponseDto> createContract(@RequestBody ContractRequestDto contractRequestDto) {
        ContractResponseDto contractDto = contractService.createContract(contractRequestDto);
        return new BaseResponse<>(contractDto);
    }

    @DeleteMapping("/{no}")
    public BaseResponse<String> deleteContract(@PathVariable("no") Long no) {
        contractService.deleteContract(no);
        return new BaseResponse<>(no + " 계약 삭제 완료");
    }

    @PatchMapping("/{no}")
    public BaseResponse<String> updateContract(@PathVariable("no") Long no, @RequestBody ContractRequestDto contractRequestDto) {
        contractService.updateContract(no, contractRequestDto);
        return new BaseResponse<>(no + " 계약 수정 완료");
    }


}
