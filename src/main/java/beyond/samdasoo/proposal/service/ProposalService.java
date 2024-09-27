package beyond.samdasoo.proposal.service;

import beyond.samdasoo.proposal.dto.ProposalRequestDto;
import beyond.samdasoo.proposal.dto.ProposalResponseDto;
import beyond.samdasoo.proposal.entity.Proposal;
import beyond.samdasoo.proposal.repository.ProposalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProposalService {

    private final ProposalRepository proposalRepository;
    //private final LeadRepository leadRepository;

    @Autowired
    //public ProposalService(ProposalRepository proposalRepository, LeadRepository leadRepository) {
        public ProposalService(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
        //this.leadRepository = leadRepository;
    }

    @Transactional
    public ProposalResponseDto createProposal(ProposalRequestDto proposalRequestDto) {

        Proposal proposal = new Proposal();

        proposal.setName(proposalRequestDto.getName());
        proposal.setCont(proposalRequestDto.getCont());
        proposal.setReqDate(proposalRequestDto.getReqDate());
        proposal.setStartDate(proposalRequestDto.getStartDate());
        proposal.setEndDate(proposalRequestDto.getEndDate());
        proposal.setSubmitDate(proposalRequestDto.getSubmitDate());
        proposal.setPrDate(proposalRequestDto.getPrDate());
        proposal.setNote(proposalRequestDto.getNote());

        // Only fetch and set Lead if leadNo is provided
//        if (proposalRequestDto.getLeadNo() != null) {
//            Lead lead = leadRepository.findById(proposalRequestDto.getLeadNo())
//                    .orElseThrow(() -> new EntityNotFoundException("Lead not found: " + proposalRequestDto.getLeadNo()));
//            proposal.setLead(lead);
//        }

        proposal = proposalRepository.save(proposal);

        return new ProposalResponseDto(proposal);
    }

    @Transactional(readOnly = true)
    public ProposalResponseDto getProposalById(Long propNo) {
        Proposal proposal = proposalRepository.findById(propNo)
                .orElseThrow(() -> new EntityNotFoundException("Proposal not found: " + propNo));
        return new ProposalResponseDto(proposal);
    }
}
