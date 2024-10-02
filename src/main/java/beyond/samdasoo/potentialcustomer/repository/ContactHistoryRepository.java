package beyond.samdasoo.potentialcustomer.repository;

import beyond.samdasoo.potentialcustomer.entity.ContactHistory;
import beyond.samdasoo.potentialcustomer.entity.PotentialCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactHistoryRepository extends JpaRepository<ContactHistory, Long> {

 //   List<ContactHistory> findByPCustomer(PotentialCustomer pCustomer);
}
