package beyond.samdasoo.user.repository;

import beyond.samdasoo.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    int countByJoinDate(LocalDate joinDate);

    Optional<User> findByEmployeeId(String employeeId);
}
