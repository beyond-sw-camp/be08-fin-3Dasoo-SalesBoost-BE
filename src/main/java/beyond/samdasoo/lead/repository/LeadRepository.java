package beyond.samdasoo.lead.repository;

import beyond.samdasoo.lead.Entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadRepository extends JpaRepository<Lead, Long> {
}
