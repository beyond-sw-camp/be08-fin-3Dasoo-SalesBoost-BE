package beyond.samdasoo.act.repository;

import beyond.samdasoo.act.entity.Act;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActRepository extends JpaRepository<Act, Long> {
}
