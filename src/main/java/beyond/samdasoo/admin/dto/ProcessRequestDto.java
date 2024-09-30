package beyond.samdasoo.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessRequestDto {
    private String processName;     // 프로세스 이름

    private Boolean isDefault;      // 기본 프로세스 지정여부

    private Integer expectedDuration;   // 예상 소요 시간

    private String description;   // 내용
}
