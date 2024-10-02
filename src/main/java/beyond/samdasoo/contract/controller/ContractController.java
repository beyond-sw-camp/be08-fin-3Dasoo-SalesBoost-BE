package beyond.samdasoo.contract.controller;

import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.contract.dto.ContractRequestDto;
import beyond.samdasoo.contract.dto.ContractResponseDto;
import beyond.samdasoo.contract.service.ContractService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "계약 전체 조회",description = "등록된 계약 전체 조회")
    public BaseResponse<List<ContractResponseDto>> getContracts() {
        List<ContractResponseDto> contracts = contractService.getAllContracts();
        return new BaseResponse<>(contracts);
    }

    @GetMapping("/{no}")
    @Operation(summary = "계약 단건 조회",description = "등록된 계약중 계약 번호로 단건 조회")
    public BaseResponse<ContractResponseDto> getContract(@PathVariable("no") Long no) {
        ContractResponseDto contract = contractService.getContract(no);
        return new BaseResponse<>(contract);
    }

    @PostMapping
    @Operation(summary = "계약 생성",description = "신규 계약 등록")
    public BaseResponse<ContractResponseDto> createContract(@RequestBody ContractRequestDto contractRequestDto) {
        ContractResponseDto contractDto = contractService.createContract(contractRequestDto);
        return new BaseResponse<>(contractDto);
    }

    @DeleteMapping("/{no}")
    @Operation(summary = "계약 삭제",description = "기존 계약 삭제")
    public BaseResponse<String> deleteContract(@PathVariable("no") Long no) {
        contractService.deleteContract(no);
        return new BaseResponse<>("계약 번호 " + no + " 삭제 완료");
    }

    @PatchMapping("/{no}")
    @Operation(summary = "계약 수정",description = "기존 계약 수정")
    public BaseResponse<ContractResponseDto> updateContract(@PathVariable("no") Long no, @RequestBody ContractRequestDto contractRequestDto) {
        ContractResponseDto contract = contractService.updateContract(no, contractRequestDto);

        return new BaseResponse<>(contract);
    }


}
