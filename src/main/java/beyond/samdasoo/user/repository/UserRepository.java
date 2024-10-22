package beyond.samdasoo.user.repository;

import beyond.samdasoo.user.dto.UserDepartmentDto;
import beyond.samdasoo.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    int countByJoinDate(LocalDate joinDate);

    Optional<User> findByEmployeeId(String employeeId);

    boolean existsByEmail(String email);

    @Query(value = "SELECT new beyond.samdasoo.user.dto.UserDepartmentDto(u.id, u.name,d.deptName) FROM User u JOIN u.department d")
    List<UserDepartmentDto> findAllUsersWithDepartmentNames();
}
