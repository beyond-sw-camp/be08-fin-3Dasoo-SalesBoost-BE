package beyond.samdasoo.potentialcustomer.repository;

import beyond.samdasoo.potentialcustomer.entity.PotentialCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PotentialCustomerRepository extends JpaRepository<PotentialCustomer, Long> {
}
