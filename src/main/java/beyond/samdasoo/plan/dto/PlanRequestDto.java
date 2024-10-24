package beyond.samdasoo.plan.dto;

import beyond.samdasoo.plan.entity.PlanStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanRequestDto {

    @Schema(description = "개인일정 여부(N:전사 일정)", defaultValue = "Y")
    private String personalYn;

    @Schema(description = "캘린더 번호", defaultValue = "1")
    private Long calendarNo;

    @Schema(description = "일정 분류", defaultValue = "PERSONAL")
    private PlanStatus planCls;

    @Schema(description = "일자", defaultValue = "2024-09-28")
    private LocalDate planDate;

    @Schema(description = "일정명", defaultValue = "일정명")
    private String title;

    @Schema(description = "시작 시간", defaultValue = "10:30:00")
    private String startTime;

    @Schema(description = "종료 시간", defaultValue = "11:30:00")
    private String endTime;

    @Schema(description = "일정명", defaultValue = "일정 내용")
    private String content;
}
