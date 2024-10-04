package beyond.samdasoo.admin.repository;

import beyond.samdasoo.admin.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{
    Product findByName(String name);

    boolean existsByName(String name);

    Product getByProdNo(Long productNo);
}
