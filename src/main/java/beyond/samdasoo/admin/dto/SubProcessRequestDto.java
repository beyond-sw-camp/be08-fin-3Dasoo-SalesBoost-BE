package beyond.samdasoo.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubProcessRequestDto {

    private Long processNo;         // 상위 프로세스 식별 번호

    private String subProcessName;      // 서브 프로세스 이름

    private String progressStep;        // 현재 진행 단계

    private Integer successRate;            // 성공 확률

    private String description;             // 내용

    private Integer expectedDuration;       // 예상 소요 시간
}
