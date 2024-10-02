package beyond.samdasoo.admin.repository;

import beyond.samdasoo.admin.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByDeptName(String deptName);

    List<Department> findByParentIsNull();
}
