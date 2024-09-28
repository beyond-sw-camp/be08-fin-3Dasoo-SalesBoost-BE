package beyond.samdasoo.contract.controller;

import beyond.samdasoo.contract.dto.ContractResponseDto;
import beyond.samdasoo.contract.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/contract")
public class ContractController {

    private final ContractService contractService;

    @GetMapping
    public List<ContractResponseDto> getContracts() {
        return contractService.getAllContracts();
    }

    @GetMapping
    public ContractResponseDto getContract(@RequestParam Long contractNo) {
        return contractService.getContract(contractNo);
    }


}
