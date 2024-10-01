package beyond.samdasoo.proposal.controller;

import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.estimate.dto.EstimateRequestDto;
import beyond.samdasoo.estimate.dto.EstimateResponseDto;
import beyond.samdasoo.proposal.dto.ProposalRequestDto;
import beyond.samdasoo.proposal.dto.ProposalResponseDto;
import beyond.samdasoo.proposal.service.ProposalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proposals")
@Tag(name="Proposal APIs",description = "제안 관련 API")

public class ProposalController {

    private final ProposalService proposalService;

    @Autowired
    public ProposalController(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    @PostMapping
    @Operation(summary = "제안 등록",description = "고객에게 제안한 영업 건을 등록")
    public BaseResponse<ProposalResponseDto> createProposal(@RequestBody ProposalRequestDto proposalRequestDto) {
        ProposalResponseDto responseDto = proposalService.createProposal(proposalRequestDto);
        return new BaseResponse<>(responseDto);
    }

    @GetMapping
    @Operation(summary = "모든 제안 조회",description = "등록되어 있는 모든 제안 조회")
    public ResponseEntity<List<ProposalResponseDto>> getAllProposals() {
        List<ProposalResponseDto> responseDtos = proposalService.getAllProposals();
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/{no}")
    @Operation(summary = "특정 제안 조회",description = "제안 번호로 특정 제안 조회")
    public BaseResponse<ProposalResponseDto> getProposalById(@PathVariable("no") Long propNo) {
        ProposalResponseDto responseDto = proposalService.getProposalById(propNo);
        return new BaseResponse<>(responseDto);
    }

    @PatchMapping("/{no}")
    @Operation(summary = "제안 수정",description = "제안 번호로 특정 제안 수정")
    public BaseResponse<ProposalResponseDto> patchUpdateProposal(@PathVariable("no") Long propNo,
                                                                 @RequestBody ProposalRequestDto proposalRequestDto) {
        ProposalResponseDto responseDto = proposalService.updateProposal(propNo, proposalRequestDto);
        return new BaseResponse<>(responseDto);
    }


    @DeleteMapping("/{no}")
    @Operation(summary = "제안 삭제",description = "제안 번호로 특정 제안 삭제")
    public BaseResponse<String> deleteProposal(@PathVariable("no") Long propNo) {
        proposalService.deleteProposal(propNo);
        return new BaseResponse<>("제안이 삭제되었습니다.");
    }
}
