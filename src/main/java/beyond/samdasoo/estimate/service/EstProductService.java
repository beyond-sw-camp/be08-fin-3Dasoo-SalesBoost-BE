package beyond.samdasoo.estimate.service;

import beyond.samdasoo.admin.entity.Product;
import beyond.samdasoo.admin.repository.ProductRepository;
import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.common.response.BaseResponseStatus;
import beyond.samdasoo.estimate.dto.EstProductRequestDto;
import beyond.samdasoo.estimate.dto.EstProductResponseDto;
import beyond.samdasoo.estimate.entity.EstProduct;
import beyond.samdasoo.estimate.entity.Estimate;
import beyond.samdasoo.estimate.repository.EstProductRepository;
import beyond.samdasoo.estimate.repository.EstimateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static beyond.samdasoo.common.response.BaseResponseStatus.EST_PRODUCT_NOT_EXIST;

@Service
@RequiredArgsConstructor
public class EstProductService {

    private final EstProductRepository estProductRepository;
    private final EstimateRepository estimateRepository;
    private final ProductRepository productRepository;

    public EstProduct findEstProductById(Long no) {
        return estProductRepository.findById(no)
                .orElseThrow(() -> new BaseException(EST_PRODUCT_NOT_EXIST));
    }

    @Transactional
    public EstProductResponseDto createEstProduct(EstProductRequestDto estProductRequestDto) {

        Product product = productRepository.findById(estProductRequestDto.getProdNo())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.Product_NOT_EXIST));

        Estimate estimate = estimateRepository.findById(estProductRequestDto.getEstNo())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.ESTIMATE_NOT_EXIST));

        EstProduct estProduct = EstProduct.builder()
                .unitAmt(estProductRequestDto.getUnitAmt())
                .discount(estProductRequestDto.getDiscount())
                .unitPropAmt(estProductRequestDto.getUnitPropAmt())
                .qty(estProductRequestDto.getQty())
                .supplyPrice(estProductRequestDto.getSupplyPrice())
                .tax(estProductRequestDto.getTax())
                .totalAmt(estProductRequestDto.getTotalAmt())
                .product(product)
                .estimate(estimate)
                .build();

        estProduct = estProductRepository.save(estProduct);

        return new EstProductResponseDto(estProduct);
    }

    public List<EstProductResponseDto> getEstProductsByEstimate(Long estNo) {
        Estimate estimate = estimateRepository.findById(estNo)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.ESTIMATE_NOT_EXIST));

        return estProductRepository.findByEstimate(estimate)
                .stream()
                .map(estProduct -> new EstProductResponseDto(estProduct))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EstProductResponseDto getEstProductById(Long no) {
        return new EstProductResponseDto(findEstProductById(no));
    }

    @Transactional
    public void updateEstProduct(Long no, EstProductRequestDto estProductRequestDto) {
        EstProduct estProduct = findEstProductById(no);

        Optional.of(estProductRequestDto.getUnitAmt()).ifPresent(estProduct::setUnitAmt);
        Optional.of(estProductRequestDto.getDiscount()).ifPresent(estProduct::setDiscount);
        Optional.of(estProductRequestDto.getUnitPropAmt()).ifPresent(estProduct::setUnitPropAmt);
        Optional.of(estProductRequestDto.getQty()).ifPresent(estProduct::setQty);
        Optional.of(estProductRequestDto.getSupplyPrice()).ifPresent(estProduct::setSupplyPrice);
        Optional.of(estProductRequestDto.getTax()).ifPresent(estProduct::setTax);
        Optional.of(estProductRequestDto.getTotalAmt()).ifPresent(estProduct::setTotalAmt);
    }

    public void deleteEstProduct(Long no) {
        estProductRepository.delete(findEstProductById(no));
    }
}
