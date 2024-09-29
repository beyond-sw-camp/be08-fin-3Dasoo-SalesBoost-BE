package beyond.samdasoo.admin.controller;

import beyond.samdasoo.admin.dto.ProductRequestDto;
import beyond.samdasoo.admin.dto.ProductResponseDto;
import beyond.samdasoo.admin.entity.Product;
import beyond.samdasoo.admin.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // 모든 상품 조회 API
    @GetMapping
    @ResponseBody
    @Operation(summary = "모든 제품 조회", description = "관리자 계정에 등록되어있는 모든 제품을 조회")
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }

    // 상품 추가 API
    @PostMapping
    @ResponseBody
    @Operation(summary = "제품 추가", description = "관리자 계정에 등록되어있는 모든 제품을 조회")
    public Product addProduct(@RequestBody ProductRequestDto request) {
        return productService.addProduct(request);
    }

    // 이름으로 상품 조회 API
    @GetMapping("/{name}")
    @ResponseBody
    @Operation(summary = "제품 검색", description = "관리자 계정에 등록되어있는 제품을 이름으로 조회")
    public ResponseEntity<Product> getProductByName(@PathVariable String name) {
        Product product = productService.getProductByName(name);

        return product != null? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }
}
