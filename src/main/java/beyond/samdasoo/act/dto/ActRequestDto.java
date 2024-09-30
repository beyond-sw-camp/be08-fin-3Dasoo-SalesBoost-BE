package beyond.samdasoo.act.dto;

import beyond.samdasoo.act.entity.ActStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ActRequestDto {

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

}
