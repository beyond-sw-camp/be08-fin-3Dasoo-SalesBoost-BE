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
    private Long subProcessNo;

    private String subProcessName;      // 서브 프로세스 이름

    private String progressStep;        // 현재 진행 단계

    private Integer successRate;            // 성공 확률

    private String description;             // 내용

    private Integer expectedDuration;       // 예상 소요 시간

    public SubProcessResponseDto(SubProcess subProcess) {
        this.subProcessNo  = subProcess.getSubProcessNo();
        this.subProcessName = subProcess.getSubProcessName();
        this.progressStep = subProcess.getProgressStep();
        this.description = subProcess.getDescription();
        this.successRate = subProcess.getSuccessRate();
        this.expectedDuration = subProcess.getExpectedDuration();
    }
}
