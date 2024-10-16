package beyond.samdasoo.calendar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CalendarRequestDto {

    @Schema(description = "사용자 번호", defaultValue = "1")
    private Long userNo;

}
