package beyond.samdasoo.plan.dto;

import beyond.samdasoo.act.dto.ActResponseDto;
import beyond.samdasoo.plan.entity.Plan;
import beyond.samdasoo.plan.entity.PlanStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PlanResponseDto {

    private Long planNo;
    private Long calendarNo;
    private String personalYn;
    private PlanStatus planCls;
    private LocalDate planDate;
    private String title;
    private String startTime;
    private String endTime;
    private String content;

    public PlanResponseDto(Plan plan) {
        this.planNo = plan.getNo();
        this.calendarNo = plan.getCalendar().getNo();
        this.personalYn = plan.getPersonalYn();
        this.planCls = plan.getPlanCls();
        this.planDate = plan.getPlanDate();
        this.title = plan.getTitle();
        this.startTime = plan.getStartTime();
        this.endTime = plan.getEndTime();
        this.content = plan.getContent();
    }
}
