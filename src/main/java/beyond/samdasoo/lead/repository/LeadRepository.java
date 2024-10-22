package beyond.samdasoo.lead.repository;

import beyond.samdasoo.lead.Entity.Lead;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LeadRepository extends JpaRepository<Lead, Long> {
    @Query("SELECT l FROM Lead l JOIN FETCH l.steps WHERE l.no = :leadNo")
    Optional<Lead> findByIdWithSteps(@Param("leadNo") Long leadNo);
}
