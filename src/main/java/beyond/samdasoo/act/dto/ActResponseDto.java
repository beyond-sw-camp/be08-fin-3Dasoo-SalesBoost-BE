package beyond.samdasoo.act.dto;

import beyond.samdasoo.act.entity.Act;
import beyond.samdasoo.act.entity.ActStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ActResponseDto {

    private Long no;
    private String name;
    private Long leadNo;
    private ActStatus cls;
    private String purpose;
    private LocalDate actDate;
    private String startTime;
    private String endTime;
    private String completeYn;
    private String planContent;
    private String actContent;

    public ActResponseDto(Act act) {
        this.no = act.getNo();
        this.name = act.getName();
//        TODO: 영업기회 엔티티 완성 후 주석 해제 예정-ActResponseDto
//        this.leadNo = act.getLeadNo().getNo();
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
