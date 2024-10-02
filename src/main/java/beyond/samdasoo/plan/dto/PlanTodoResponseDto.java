package beyond.samdasoo.plan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class PlanTodoResponseDto {

    private Long planNo;
    private LocalDate planDate;
    private List<String> todoTitles;
}