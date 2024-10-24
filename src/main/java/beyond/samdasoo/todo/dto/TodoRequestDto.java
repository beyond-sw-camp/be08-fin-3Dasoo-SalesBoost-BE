package beyond.samdasoo.todo.dto;

import beyond.samdasoo.todo.entity.TodoStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequestDto {

    @Schema(description = "캘린더 번호", defaultValue = "1")
    private Long calendarNo;

    @Schema(description = "할 일 제목", defaultValue = "제목")
    private String title;

    @Schema(description = "할 일 종류", defaultValue = "회의")
    private String todoCls;

    @Schema(description = "우선순위", defaultValue = "보통")
    private String priority;

    @Schema(description = "기한일", defaultValue = "2024-11-07")
    private LocalDate dueDate;

    @Schema(description = "할 일 상태", defaultValue = "TODO")
    private TodoStatus status;

    @Schema(description = "나만보기 여부", defaultValue = "N")
    private String privateYn;

    @Schema(description = "내용", defaultValue = "할 일 내용")
    private String content;

    @Schema(description = "일정 번호", defaultValue = "1")
    private Long planNo;
}
