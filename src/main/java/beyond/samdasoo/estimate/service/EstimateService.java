package beyond.samdasoo.estimate.service;

import beyond.samdasoo.admin.entity.Product;
import beyond.samdasoo.admin.repository.ProductRepository;
import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.common.response.BaseResponseStatus;
import beyond.samdasoo.estimate.dto.CreateEstProductDto;
import beyond.samdasoo.estimate.dto.CreateEstimateDto;
import beyond.samdasoo.estimate.dto.EstimateRequestDto;
import beyond.samdasoo.estimate.dto.EstimateResponseDto;
import beyond.samdasoo.estimate.entity.EstProduct;
import beyond.samdasoo.estimate.entity.Estimate;
import beyond.samdasoo.estimate.repository.EstProductRepository;
import beyond.samdasoo.estimate.repository.EstimateRepository;
import beyond.samdasoo.proposal.entity.Proposal;
import beyond.samdasoo.proposal.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EstimateService {

    private final EstimateRepository estimateRepository;
    private final ProposalRepository proposalRepository;
    private final EstProductRepository estProductRepository;
    private final ProductRepository productRepository;

    @Autowired
    public EstimateService(EstimateRepository estimateRepository, ProposalRepository proposalRepository,
                           EstProductRepository estProductRepository, ProductRepository productRepository) {
        this.estimateRepository = estimateRepository;
        this.proposalRepository = proposalRepository;
        this.estProductRepository = estProductRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public EstimateResponseDto createEstimate(CreateEstimateDto createEstimateDto) {

        Proposal proposal = proposalRepository.findById(createEstimateDto.getPropNo())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.PROPOSAL_NOT_EXIST));

        Estimate estimate = new Estimate();
        estimate.setName(createEstimateDto.getName());
        estimate.setEstDate(createEstimateDto.getEstDate());
        estimate.setTaxCls(createEstimateDto.getTaxCls());
        estimate.setSurtaxYn(createEstimateDto.getSurtaxYn());
        estimate.setProdCnt(createEstimateDto.getProdCnt());
        estimate.setSupplyPrice(createEstimateDto.getSupplyPrice());
        estimate.setTax(createEstimateDto.getTax());
        estimate.setTotalPrice(createEstimateDto.getTotalPrice());
        estimate.setNote(createEstimateDto.getNote());
        estimate.setProposal(proposal);

        estimateRepository.save(estimate);

        for (CreateEstProductDto estProductDto : createEstimateDto.getEstProducts()) {

            Long prodNo = estProductDto.getProdNo();
            Product product = productRepository.findById(prodNo)
                    .orElseThrow(() -> new BaseException(BaseResponseStatus.Product_NOT_EXIST));

            EstProduct estProduct = new EstProduct();
            estProduct.setUnitAmt(estProductDto.getUnitAmt());
            estProduct.setDiscount(estProductDto.getDiscount());
            estProduct.setUnitPropAmt(estProductDto.getUnitPropAmt());
            estProduct.setQty(estProductDto.getQty());
            estProduct.setSupplyPrice(estProductDto.getSupplyPrice());
            estProduct.setTax(estProductDto.getTax());
            estProduct.setTotalAmt(estProductDto.getTotalAmt());

            estProduct.setEstimate(estimate);
            estProduct.setProduct(product);

            estimate.getEstProducts().add(estProduct);
        }

        return new EstimateResponseDto(estimate);
    }

    public List<EstimateResponseDto> getAllEstimates() {
        List<Estimate> estimates = estimateRepository.findAll();
        return estimates.stream()
                .map(EstimateResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EstimateResponseDto getEstimateById(Long estNo) {
        Estimate estimate = estimateRepository.findById(estNo)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.ESTIMATE_NOT_EXIST));
        return new EstimateResponseDto(estimate);
    }

    @Transactional
    public EstimateResponseDto updateEstimate(Long estNo, EstimateRequestDto estimateRequestDto) {

        Estimate estimate = estimateRepository.findById(estNo)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.ESTIMATE_NOT_EXIST));

        Optional.ofNullable(estimateRequestDto.getName()).ifPresent(estimate::setName);
        Optional.ofNullable(estimateRequestDto.getEstDate()).ifPresent(estimate::setEstDate);
        Optional.ofNullable(estimateRequestDto.getTaxCls()).ifPresent(estimate::setTaxCls);
        Optional.ofNullable(estimateRequestDto.getSurtaxYn()).ifPresent(estimate::setSurtaxYn);
        Optional.ofNullable(estimateRequestDto.getProdCnt()).ifPresent(estimate::setProdCnt);
        Optional.ofNullable(estimateRequestDto.getSupplyPrice()).ifPresent(estimate::setSupplyPrice);
        Optional.ofNullable(estimateRequestDto.getTax()).ifPresent(estimate::setTax);
        Optional.ofNullable(estimateRequestDto.getTotalPrice()).ifPresent(estimate::setTotalPrice);
        Optional.of(estimateRequestDto.getNote()).ifPresent(estimate::setNote);


        if (estimateRequestDto.getEstProducts() != null) {
            List<EstProduct> existingEstProducts = estimate.getEstProducts();
            estProductRepository.deleteAll(existingEstProducts);

            estimate.getEstProducts().clear();

            for (CreateEstProductDto estProductDto : estimateRequestDto.getEstProducts()) {
                Product product = productRepository.findById(estProductDto.getProdNo())
                        .orElseThrow(() -> new BaseException(BaseResponseStatus.Product_NOT_EXIST));

                EstProduct estProduct = new EstProduct();

                Optional.ofNullable(estProductDto.getUnitAmt()).ifPresent(estProduct::setUnitAmt);
                Optional.ofNullable(estProductDto.getDiscount()).ifPresent(estProduct::setDiscount);
                Optional.ofNullable(estProductDto.getUnitPropAmt()).ifPresent(estProduct::setUnitPropAmt);
                Optional.ofNullable(estProductDto.getQty()).ifPresent(estProduct::setQty);
                Optional.ofNullable(estProductDto.getSupplyPrice()).ifPresent(estProduct::setSupplyPrice);
                Optional.ofNullable(estProductDto.getTax()).ifPresent(estProduct::setTax);
                Optional.ofNullable(estProductDto.getTotalAmt()).ifPresent(estProduct::setTotalAmt);

                estProduct.setEstimate(estimate);
                estProduct.setProduct(product);

                estProductRepository.save(estProduct);
                estimate.getEstProducts().add(estProduct);
            }
        }

        estimate = estimateRepository.save(estimate);

        return new EstimateResponseDto(estimate);
    }

    @Transactional
    public void deleteEstimate(Long estNo) {
        Estimate estimate = estimateRepository.findById(estNo)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.ESTIMATE_NOT_EXIST));

        estimateRepository.delete(estimate);
    }
}
