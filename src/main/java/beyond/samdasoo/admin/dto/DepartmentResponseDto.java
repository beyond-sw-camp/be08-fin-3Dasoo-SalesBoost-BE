package beyond.samdasoo.admin.dto;

import beyond.samdasoo.admin.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentResponseDto {

    private String upperDeptName;        // 상위 부서명

    private String deptCode;    // 부서 코드

    private String deptName;    // 부서 이름

    private String engName;     // 부서 영문 이름

    private String deptHead;    // 부서장

    public DepartmentResponseDto(Department department) {
        if(department.getParent() != null){
            this.upperDeptName = department.getParent().getDeptName();
        }
        this.deptCode = department.getDeptCode();
        this.deptName = department.getDeptName();
        this.engName = department.getEngName();
        this.deptHead = department.getDeptHead();
    }
}
