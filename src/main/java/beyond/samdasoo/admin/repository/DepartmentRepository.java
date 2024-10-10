package beyond.samdasoo.admin.repository;

import beyond.samdasoo.admin.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByDeptName(String deptName);

    List<Department> findByParentIsNull();

    Optional<Department> findByDeptCode(String deptCode);

    Optional<Department> findByDeptName(String deptName);
}
