package beyond.samdasoo.proposal.controller;

import beyond.samdasoo.proposal.dto.ProposalRequestDto;
import beyond.samdasoo.proposal.dto.ProposalResponseDto;
import beyond.samdasoo.proposal.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proposals")
@CrossOrigin(origins = "http://localhost:8080")
public class ProposalController {

    private final ProposalService proposalService;

    @Autowired
    public ProposalController(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    // Endpoint to create a new proposal
    @PostMapping
    public ResponseEntity<ProposalResponseDto> createProposal(@RequestBody ProposalRequestDto proposalRequestDto) {
        ProposalResponseDto responseDto = proposalService.createProposal(proposalRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    // Endpoint to get a proposal by ID (propNo)
    @GetMapping("/{no}")
    public ResponseEntity<ProposalResponseDto> getProposalById(@PathVariable("no") Long propNo) {
        ProposalResponseDto responseDto = proposalService.getProposalById(propNo);
        return ResponseEntity.ok(responseDto);
    }

    // Endpoint to retrieve all proposals
//    @GetMapping
//    public ResponseEntity<List<ProposalResponseDto>> getAllProposals() {
//        List<ProposalResponseDto> proposals = proposalService.getAllProposals();
//        return ResponseEntity.ok(proposals);
//    }
}
