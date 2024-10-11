package beyond.samdasoo.customer.repository;

import beyond.samdasoo.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
