package beyond.samdasoo.user.repository;

import beyond.samdasoo.user.dto.FilterUserDto;

import java.util.List;

public interface UserRepositoryCustom {
    List<FilterUserDto> findUsersByDepartmentAndSubDepartments(Long deptNo);
}
