package beyond.samdasoo.estimate.repository;

import beyond.samdasoo.estimate.entity.Estimate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstimateRepository extends JpaRepository<Estimate, Long> {
}
