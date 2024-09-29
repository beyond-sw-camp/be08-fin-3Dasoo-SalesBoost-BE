package beyond.samdasoo.admin.service;

import beyond.samdasoo.admin.dto.ProductRequestDto;
import beyond.samdasoo.admin.dto.ProductResponseDto;
import beyond.samdasoo.admin.entity.Product;
import beyond.samdasoo.admin.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> new ProductResponseDto(
                        product.getProdCode(),
                        product.getName(),
                        product.getEngName(),
                        product.getAbbrName(),
                        product.getUppGroup(),
                        product.getReleaseDate(),
                        product.getDept(),
                        product.getQuantity(),
                        product.getUnit(),
                        product.getField(),
                        product.getSupplyPrice(),
                        product.getTaxrate(),
                        product.getPrice()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Product addProduct(ProductRequestDto request) {
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
                .taxrate(request.getTaxrate())
                .price(request.getPrice())
                .build();


        return productRepository.save(product);
    }

    @Override
    public Product getProductByName(String name) {
        if(productRepository.existsByName(name)){
            return productRepository.findByName(name);
        }

        return null;
    }
}
