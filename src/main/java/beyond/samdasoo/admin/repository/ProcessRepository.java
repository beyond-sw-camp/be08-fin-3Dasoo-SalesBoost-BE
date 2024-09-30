package beyond.samdasoo.admin.repository;

import beyond.samdasoo.admin.entity.Process;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessRepository extends JpaRepository<Process, Long> {
    boolean existsByProcessName(String name);

}
