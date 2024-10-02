package beyond.samdasoo.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentRequestDto {

    private Long upperDeptNo;        // 상위 부서 코드

    private String deptCode;    // 부서 코드

    private String deptName;    // 부서 이름

    private String engName;     // 부서 영문 이름

    private String deptHead;    // 부서장

}
