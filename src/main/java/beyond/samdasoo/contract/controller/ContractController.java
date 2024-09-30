package beyond.samdasoo.contract.controller;

import beyond.samdasoo.contract.dto.ContractRequestDto;
import beyond.samdasoo.contract.dto.ContractResponseDto;
import beyond.samdasoo.contract.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/contract")
public class ContractController {

    private final ContractService contractService;

    @GetMapping
    public List<ContractResponseDto> getContracts() {
        return contractService.getAllContracts();
    }

    @GetMapping("/{no}")
    public ContractResponseDto getContract(@PathVariable("no") Long no) {
        return contractService.getContract(no);
    }

    @PostMapping
    public ContractResponseDto createContract(@RequestBody ContractRequestDto contractRequestDto) {
        return contractService.createContract(contractRequestDto);
    }

    @DeleteMapping("/{no}")
    public void deleteContract(@PathVariable("no") Long no) {
        contractService.deleteContract(no);
    }

    @PatchMapping("/{no}")
    public void updateContract(@PathVariable("no") Long no, @RequestBody ContractRequestDto contractRequestDto) {
        contractService.updateContract(no, contractRequestDto);
    }


}
