package beyond.samdasoo.contract.repository;

import beyond.samdasoo.contract.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {

}
