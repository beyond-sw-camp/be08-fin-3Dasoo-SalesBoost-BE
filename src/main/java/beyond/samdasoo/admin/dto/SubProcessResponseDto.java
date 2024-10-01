package beyond.samdasoo.admin.dto;

import beyond.samdasoo.admin.entity.SubProcess;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubProcessResponseDto {
    private String subProcessName;      // 서브 프로세스 이름

    private String progressStep;        // 현재 진행 단계

    private Integer successRate;            // 성공 확률

    private String action;              // 프로세스 변경 액션

    private Integer expectedDuration;       // 예상 소요 시간

    public SubProcessResponseDto(SubProcess subProcess) {
        this.subProcessName = subProcess.getSubProcessName();
        this.progressStep = subProcess.getProgressStep();
        this.successRate = subProcess.getSuccessRate();
        this.action = subProcess.getAction();
        this.expectedDuration = subProcess.getExpectedDuration();
    }
}
