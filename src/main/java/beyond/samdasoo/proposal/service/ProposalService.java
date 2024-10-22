package beyond.samdasoo.proposal.service;

import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.common.response.BaseResponseStatus;
import beyond.samdasoo.lead.Entity.Lead;
import beyond.samdasoo.lead.repository.LeadRepository;
import beyond.samdasoo.proposal.dto.ProposalRequestDto;
import beyond.samdasoo.proposal.dto.ProposalResponseDto;
import beyond.samdasoo.proposal.entity.Proposal;
import beyond.samdasoo.proposal.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProposalService {

    private final ProposalRepository proposalRepository;
    private final LeadRepository leadRepository;

    @Autowired
    public ProposalService(ProposalRepository proposalRepository, LeadRepository leadRepository) {
        this.proposalRepository = proposalRepository;
        this.leadRepository = leadRepository;
    }

    @Transactional
    public ProposalResponseDto createProposal(ProposalRequestDto proposalRequestDto) {

        Lead lead = leadRepository.findById(proposalRequestDto.getLeadNo())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.LEAD_NOT_EXIST));

        Proposal proposal = new Proposal();
        proposal.setName(proposalRequestDto.getName());
        proposal.setCont(proposalRequestDto.getCont());
        proposal.setReqDate(proposalRequestDto.getReqDate());
        proposal.setStartDate(proposalRequestDto.getStartDate());
        proposal.setEndDate(proposalRequestDto.getEndDate());
        proposal.setSubmitDate(proposalRequestDto.getSubmitDate());
        proposal.setPrDate(proposalRequestDto.getPrDate());
        proposal.setNote(proposalRequestDto.getNote());
        proposal.setLead(lead);

        proposal = proposalRepository.save(proposal);

        return new ProposalResponseDto(proposal);
    }

    public List<ProposalResponseDto> getAllProposals() {
        List<Proposal> proposals = proposalRepository.findAll();
        return proposals.stream()
                .map(proposal -> new ProposalResponseDto(proposal))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProposalResponseDto getProposalById(Long propNo) {
        Proposal proposal = proposalRepository.findById(propNo)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.PROPOSAL_NOT_EXIST));
        return new ProposalResponseDto(proposal);
    }

    @Transactional
    public ProposalResponseDto updateProposal(Long propNo, ProposalRequestDto proposalRequestDto) {
        Proposal proposal = proposalRepository.findById(propNo)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.PROPOSAL_NOT_EXIST));

        proposal.setName(proposalRequestDto.getName());
        proposal.setCont(proposalRequestDto.getCont());
        proposal.setReqDate(proposalRequestDto.getReqDate());
        proposal.setStartDate(proposalRequestDto.getStartDate());
        proposal.setEndDate(proposalRequestDto.getEndDate());
        proposal.setSubmitDate(proposalRequestDto.getSubmitDate());
        proposal.setPrDate(proposalRequestDto.getPrDate());
        proposal.setNote(proposalRequestDto.getNote());

        proposal = proposalRepository.save(proposal);
        return new ProposalResponseDto(proposal);
    }

    @Transactional
    public void deleteProposal(Long propNo) {
        proposalRepository.findById(propNo)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.PROPOSAL_NOT_EXIST));

        proposalRepository.deleteById(propNo);
    }
}
