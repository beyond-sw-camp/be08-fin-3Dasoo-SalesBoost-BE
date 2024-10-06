package beyond.samdasoo.calendar.dto;

import beyond.samdasoo.calendar.entity.CalendarType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CalendarRequestDto {

    @Schema(description = "사용자 번호", defaultValue = "1")
    private Long userNo;

    @Schema(description = "캘린더 종류", defaultValue = "PERSONAL")
    private CalendarType type;

}
