package beyond.samdasoo.admin.service;

import beyond.samdasoo.admin.dto.ProductRequestDto;
import beyond.samdasoo.admin.dto.ProductResponseDto;
import beyond.samdasoo.admin.entity.Product;
import beyond.samdasoo.admin.repository.ProductRepository;
import beyond.samdasoo.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static beyond.samdasoo.common.response.BaseResponseStatus.PRODUCT_ALREADY_EXIST;
import static beyond.samdasoo.common.response.BaseResponseStatus.Product_NOT_EXIST;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> new ProductResponseDto(product))
                .collect(Collectors.toList());
    }

    @Override
    public void addProduct(ProductRequestDto request) {
        boolean exists = productRepository.existsByName(request.getName());

        if(exists){
            throw new BaseException(PRODUCT_ALREADY_EXIST);
        }

        Product product = Product.builder()
                .prodCode(request.getProdCode())
                .name(request.getName())
                .engName(request.getEngName())
                .abbrName(request.getAbbrName())
                .uppGroup(request.getUppGroup())
                .releaseDate(request.getReleaseDate())
                .dept(request.getDept())
                .quantity(request.getQuantity())
                .unit(request.getUnit())
                .field(request.getField())
                .supplyPrice(request.getSupplyPrice())
                .price(request.getPrice())
                .build();


        productRepository.save(product);
    }

    @Override
    public ProductResponseDto getProductByName(String name) {
        Product product  = productRepository.findByName(name);

        if(product == null){
            throw new BaseException(Product_NOT_EXIST);
        }

        return new ProductResponseDto(product);
    }

    @Override
    public void deleteProductByNo(Long productNo) {
        Optional<Product> optionalProduct = productRepository.findById(productNo);

        if(optionalProduct.isEmpty()){
            throw new BaseException(Product_NOT_EXIST);
        }

        productRepository.deleteById(productNo);
    }

    @Override
    public void updateProductByNo(Long productNo, ProductRequestDto request) {
        Optional<Product> optionalProduct = productRepository.findById(productNo);

        if(optionalProduct.isEmpty()){
            throw new BaseException(Product_NOT_EXIST);
        }

        Product product = optionalProduct.get();

        if (request.getProdCode() != null) {
            product.setProdCode(request.getProdCode());
        }
        if (request.getName() != null) {
            product.setName(request.getName());
        }
        if (request.getEngName() != null) {
            product.setEngName(request.getEngName());
        }
        if (request.getAbbrName() != null) {
            product.setAbbrName(request.getAbbrName());
        }
        if (request.getUppGroup() != null) {
            product.setUppGroup(request.getUppGroup());
        }
        if (request.getReleaseDate() != null) {
            product.setReleaseDate(request.getReleaseDate());
        }
        if (request.getDept() != null) {
            product.setDept(request.getDept());
        }
        if (request.getQuantity() != 0) {
            product.setQuantity(request.getQuantity());
        }
        if (request.getUnit() != null) {
            product.setUnit(request.getUnit());
        }
        if (request.getField() != null) {
            product.setField(request.getField());
        }
        if (request.getSupplyPrice() != 0) {
            product.setSupplyPrice(request.getSupplyPrice());
        }
        if (request.getPrice() != 0) {
            product.setPrice(request.getPrice());
        }

        productRepository.save(product);
    }
}
