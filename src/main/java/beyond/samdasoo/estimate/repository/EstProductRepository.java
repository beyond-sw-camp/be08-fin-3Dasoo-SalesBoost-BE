package beyond.samdasoo.estimate.repository;

import beyond.samdasoo.estimate.entity.EstProduct;
import beyond.samdasoo.estimate.entity.Estimate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstProductRepository extends JpaRepository<EstProduct, Long> {
    List<EstProduct> findByEstimate(Estimate estimate);
}
