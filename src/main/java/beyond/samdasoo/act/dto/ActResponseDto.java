package beyond.samdasoo.act.dto;

import beyond.samdasoo.act.entity.Act;
import beyond.samdasoo.act.entity.ActStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ActResponseDto {

    private Long actNo;
    private Long leadNo;
    private String leadName;
    private Long calendarNo;
    private String name;
    private ActStatus cls;
    private String purpose;
    private LocalDate actDate;
    private String startTime;
    private String endTime;
    private String completeYn;
    private String planContent;
    private String actContent;

    public ActResponseDto(Act act) {
        this.actNo = act.getNo();
        this.leadNo = act.getLead().getNo();
        this.leadName = act.getLead().getName();
        this.calendarNo = act.getCalendar().getNo();
        this.name = act.getName();
        this.cls = act.getCls();
        this.purpose = act.getPurpose();
        this.actDate = act.getActDate();
        this.startTime = act.getStartTime();
        this.endTime = act.getEndTime();
        this.completeYn = act.getCompleteYn();
        this.planContent = act.getPlanCont();
        this.actContent = act.getActCont();
    }
}
