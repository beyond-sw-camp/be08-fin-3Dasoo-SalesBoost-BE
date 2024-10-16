package beyond.samdasoo.admin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long no;

    private String name;

    private String deptCode;

    private String engName;

    private String deptHead;


    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DepartmentDto> children;

    public DepartmentDto(Long no, String name, String deptCode, String engName, String deptHead) {
        this.no = no;

        this.name = name;

        this.deptCode = deptCode;

        this.engName = engName;

        this.deptHead = deptHead;

    }
}
