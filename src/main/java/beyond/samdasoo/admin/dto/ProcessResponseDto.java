package beyond.samdasoo.admin.dto;

import beyond.samdasoo.admin.entity.Process;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessResponseDto {

    private String processName;     // 프로세스 이름

    private Boolean isDefault;      // 기본 프로세스 지정여부

    private Integer expectedDuration;   // 예상 소요 시간

    private String description;   // 내용

    public ProcessResponseDto(Process process) {
        this.processName = process.getProcessName();
        this.isDefault = process.getIsDefault();
        this.expectedDuration = process.getExpectedDuration();
        this.description = process.getDescription();
    }
}
