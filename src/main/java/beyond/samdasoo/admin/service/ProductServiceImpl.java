package beyond.samdasoo.admin.service;

import beyond.samdasoo.admin.dto.ProductRequestDto;
import beyond.samdasoo.admin.dto.ProductResponseDto;
import beyond.samdasoo.admin.entity.Product;
import beyond.samdasoo.admin.repository.ProductRepository;
import beyond.samdasoo.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Product addProduct(ProductRequestDto request) {
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


        return productRepository.save(product);
    }

    @Override
    public ProductResponseDto getProductByName(String name) {
        Product product  = productRepository.findByName(name);

        if(product == null){
            throw new BaseException(Product_NOT_EXIST);
        }

        return new ProductResponseDto(product);
    }
}
