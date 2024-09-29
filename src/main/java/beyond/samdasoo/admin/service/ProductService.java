package beyond.samdasoo.admin.service;

import beyond.samdasoo.admin.dto.ProductRequestDto;
import beyond.samdasoo.admin.dto.ProductResponseDto;
import beyond.samdasoo.admin.entity.Product;

import java.util.List;

public interface ProductService {
    // 모든 제품 조회
    List<ProductResponseDto> getAllProducts();
    // 상품 추가
    Product addProduct(ProductRequestDto request);
    // 이름으로 상품 조회
    Product getProductByName(String name);

}
