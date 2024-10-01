package beyond.samdasoo.estimate.service;

import beyond.samdasoo.admin.entity.Product;
import beyond.samdasoo.admin.repository.ProductRepository;
import beyond.samdasoo.estimate.dto.EstimateRequestDto;
import beyond.samdasoo.estimate.dto.EstimateResponseDto;
import beyond.samdasoo.estimate.entity.Estimate;
import beyond.samdasoo.estimate.repository.EstimateRepository;
import beyond.samdasoo.proposal.entity.Proposal;
import beyond.samdasoo.proposal.repository.ProposalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstimateService {

    private final EstimateRepository estimateRepository;
    private final ProductRepository productRepository;
    private final ProposalRepository proposalRepository;

    @Autowired
    public EstimateService(EstimateRepository estimateRepository, ProductRepository productRepository, ProposalRepository proposalRepository) {
        this.estimateRepository = estimateRepository;
        this.productRepository = productRepository;
        this.proposalRepository = proposalRepository;
    }

    @Transactional
    public EstimateResponseDto createEstimate(EstimateRequestDto estimateRequestDto) {


        Product product = productRepository.findById(estimateRequestDto.getProdNo())
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + estimateRequestDto.getProdNo()));

        Proposal proposal = proposalRepository.findById(estimateRequestDto.getPropNo())
                .orElseThrow(() -> new EntityNotFoundException("제안을 찾을 수 없습니다: " + estimateRequestDto.getPropNo()));

        Estimate estimate = new Estimate();
        estimate.setName(estimateRequestDto.getName());
        estimate.setEstDate(estimateRequestDto.getEstDate());
        estimate.setTaxCls(estimateRequestDto.getTaxCls());
        estimate.setSurtaxYn(estimateRequestDto.getSurtaxYn());
        estimate.setProdCnt(estimateRequestDto.getProdCnt());
        estimate.setSupplyPrice(estimateRequestDto.getSupplyPrice());
        estimate.setTax(estimateRequestDto.getTax());
        estimate.setTotalPrice(estimateRequestDto.getTotalPrice());
        estimate.setNote(estimateRequestDto.getNote());
        estimate.setProduct(product);
        estimate.setProposal(proposal);

        estimate = estimateRepository.save(estimate);

        return new EstimateResponseDto(estimate);
    }

    public List<EstimateResponseDto> getAllEstimates() {
        List<Estimate> estimates = estimateRepository.findAll();
        return estimates.stream()
                .map(estimate -> new EstimateResponseDto(estimate))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EstimateResponseDto getEstimateById(Long estNo) {
        Estimate estimate = estimateRepository.findById(estNo)
                .orElseThrow(() -> new EntityNotFoundException("견적을 찾을 수 없습니다: " + estNo));
        return new EstimateResponseDto(estimate);
    }

    @Transactional
    public EstimateResponseDto updateEstimate(Long estNo, EstimateRequestDto estimateRequestDto) {
        Estimate estimate = estimateRepository.findById(estNo)
                .orElseThrow(() -> new EntityNotFoundException("견적을 찾을 수 없습니다: " + estNo));

        estimate.setName(estimateRequestDto.getName());
        estimate.setEstDate(estimateRequestDto.getEstDate());
        estimate.setTaxCls(estimateRequestDto.getTaxCls());
        estimate.setSurtaxYn(estimateRequestDto.getSurtaxYn());
        estimate.setProdCnt(estimateRequestDto.getProdCnt());
        estimate.setSupplyPrice(estimateRequestDto.getSupplyPrice());
        estimate.setTax(estimateRequestDto.getTax());
        estimate.setTotalPrice(estimateRequestDto.getTotalPrice());
        estimate.setNote(estimateRequestDto.getNote());

        estimate = estimateRepository.save(estimate);
        return new EstimateResponseDto(estimate);
    }

    @Transactional
    public void deleteEstimate(Long estNo) {
        Estimate estimate = estimateRepository.findById(estNo)
                .orElseThrow(() -> new EntityNotFoundException("견적을 찾을 수 없습니다: " + estNo));

        estimateRepository.delete(estimate);
    }
}
