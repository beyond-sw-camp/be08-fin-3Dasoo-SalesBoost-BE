package beyond.samdasoo.lead.dto;

import beyond.samdasoo.lead.Entity.Step;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StepResponseDto {
    private Long stepNo;
    private int level;
    private String completeYn;
    private LocalDate completeDate;
    private String subProcessName;

    public StepResponseDto(Step step) {
        this.stepNo = step.getNo();
        this.level = step.getLevel();
        this.completeYn = step.getCompleteYn();
        this.completeDate = step.getCompleteDate();
        this.subProcessName = step.getSubProcess().getSubProcessName();
    }
}
