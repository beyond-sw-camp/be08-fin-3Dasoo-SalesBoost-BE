package beyond.samdasoo.admin.controller;


import beyond.samdasoo.admin.dto.DepartmentDto;
import beyond.samdasoo.admin.dto.DepartmentRequestDto;
import beyond.samdasoo.admin.dto.DepartmentResponseDto;
import beyond.samdasoo.admin.service.DepartmentService;
import beyond.samdasoo.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Department APIs",description = "부서 관련 API")
@RestController
@RequestMapping("/api/admin/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    @Operation(summary = "모든 부서 조회", description = "관리자 계정에 등록된 모든 부서를 조회")
    public BaseResponse<List<DepartmentDto>> getAllDepartments(){
        List<DepartmentDto> departments = departmentService.getAllDepartments();

        return new BaseResponse<>(departments);
    }

    @PostMapping
    @Operation(summary = "부서 추가", description = "관리자 계정에 부서를 추가")
    public BaseResponse<String> addDepartment(@RequestBody DepartmentRequestDto request){

        departmentService.addDepartment(request);

        return new BaseResponse<>("부서 등록을 완료했습니다.");
    }
    
    @GetMapping("/{no}")
    @Operation(summary = "특정 부서 조회", description = "관리자 계정에서 부서번호로 특정 부서를 조회")
    public BaseResponse<DepartmentResponseDto> getDepartmentByNo(@PathVariable("no") Long deptNo){
        DepartmentResponseDto department = departmentService.getDepartmentByNo(deptNo);

        return new BaseResponse<>(department);
    }

    @DeleteMapping("/{no}")
    @Operation(summary = "특정 부서 삭제", description = "관리자 계정에서 부서번호로 등록된 부서를 삭제")
    public BaseResponse<String> deleteDepartmentByNo(@PathVariable("no") Long deptNo){
        departmentService.deleteDepartmentByNo(deptNo);
        return new BaseResponse<>("부서 삭제를 완료했습니다.");
    }
    
    @PatchMapping("/{no}")
    @Operation(summary = "특정 부서 수정", description = "관리자 계정에서 부서번호로 등록된 부서의 정보 수정")
    public BaseResponse<String> updateDepartmentByNo(@PathVariable("no") Long deptNo, DepartmentRequestDto request){
        departmentService.updateDepartmentByNo(deptNo, request);

        return new BaseResponse<>("부서 수정을 완료했습니다.");
    }
}
