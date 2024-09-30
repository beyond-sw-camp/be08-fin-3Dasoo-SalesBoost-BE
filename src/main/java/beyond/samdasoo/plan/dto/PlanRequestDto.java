package beyond.samdasoo.plan.dto;

import beyond.samdasoo.plan.entity.PlanStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PlanRequestDto {
    private String personalYn;
    private Long userNo;
    private PlanStatus planCls;
    private LocalDate planDate;
    private String title;
    private String startTime;
    private String endTime;
    private String content;
}
