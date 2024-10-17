package beyond.samdasoo.lead.repository;

import beyond.samdasoo.lead.Entity.Lead;
import beyond.samdasoo.lead.Entity.Step;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StepRepository extends JpaRepository<Step, Long> {
}
