package beyond.samdasoo.sales.repository;

import beyond.samdasoo.sales.entity.Sales;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SalesRepository extends JpaRepository<Sales, Long> {

    @Query("SELECT SUM(s.price) FROM Sales s WHERE FUNCTION('DATE_FORMAT', s.salesDate, '%Y-%m') = :yearMonth")
    Optional<Integer> findSalesCountByMonth(@Param("yearMonth") String yearMonth);
}
