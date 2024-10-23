package beyond.samdasoo.admin.service;

import beyond.samdasoo.admin.dto.ProductRequestDto;
import beyond.samdasoo.admin.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    // 모든 제품 조회
    List<ProductResponseDto> getAllProducts();
    // 상품 추가
    void addProduct(ProductRequestDto request);
    // 이름으로 상품 조회
    ProductResponseDto getProductByName(String name);

    void deleteProductByNo(Long prodNo);

    void updateProductByNo(Long productNo, ProductRequestDto request);
}
