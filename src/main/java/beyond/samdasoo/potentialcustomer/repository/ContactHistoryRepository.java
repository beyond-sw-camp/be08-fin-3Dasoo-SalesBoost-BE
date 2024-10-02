package beyond.samdasoo.potentialcustomer.repository;

import beyond.samdasoo.potentialcustomer.entity.ContactHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactHistoryRepository extends JpaRepository<ContactHistory, Long> {

}
