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

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DepartmentDto> children;

    public DepartmentDto(Long no, String name) {
        this.no = no;
        this.name = name;
    }
}
