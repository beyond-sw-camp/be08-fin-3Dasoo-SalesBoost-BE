package beyond.samdasoo.act.dto;

import beyond.samdasoo.act.entity.ActStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ActRequestDto {

    @Schema(description = "영업활동 제목", defaultValue = "영업활동명")
    private String name;

    @Schema(description = "영업기회 번호", defaultValue = "1")
    private Long leadNo;

    @Schema(description = "캘린더 번호", defaultValue = "1")
    private Long calendarNo;

    @Schema(description = "영업활동 제목", defaultValue = "PRODUCT_INTRO")
    private ActStatus cls;

    @Schema(description = "영업활동 목적", defaultValue = "제품 소개")
    private String purpose;

    @Schema(description = "영업활동 일자", defaultValue = "2024-11-06")
    private LocalDate actDate;

    @Schema(description = "시작일", defaultValue = "2024-11-05")
    private String startTime;

    @Schema(description = "종료일", defaultValue = "2024-11-07")
    private String endTime;

    @Schema(description = "완료여부", defaultValue = "N")
    private String completeYn;

    @Schema(description = "영업활동 계획 내용", defaultValue = "제품 소개")
    private String planContent;

    @Schema(description = "영업활동 내용", defaultValue = "제품 소개 및 시연")
    private String actContent;

}
