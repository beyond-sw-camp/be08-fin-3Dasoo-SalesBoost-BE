package beyond.samdasoo.act.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActStatusDto {
    private long planCount;
    private long completeCount;
    private long completePercent;
}
