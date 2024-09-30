package beyond.samdasoo.sales.repository;

import beyond.samdasoo.sales.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales, Integer> {
}
