package beyond.samdasoo.admin.service;

import beyond.samdasoo.admin.dto.DepartmentDto;
import beyond.samdasoo.admin.dto.DepartmentRequestDto;
import beyond.samdasoo.admin.dto.DepartmentResponseDto;

import java.util.List;

public interface DepartmentService {
    void addDepartment(DepartmentRequestDto request);

    List<DepartmentDto> getAllDepartments();

    DepartmentResponseDto getDepartmentByNo(Long deptNo);

    void deleteDepartmentByNo(Long deptNo);

    void updateDepartmentByNo(Long deptNo, DepartmentRequestDto request);
}
