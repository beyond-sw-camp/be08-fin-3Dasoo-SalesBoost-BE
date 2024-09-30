package beyond.samdasoo.plan.repository;

import beyond.samdasoo.plan.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
