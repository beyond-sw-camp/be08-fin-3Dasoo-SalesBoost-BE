package beyond.samdasoo.todo.dto;

import beyond.samdasoo.todo.entity.Todo;
import beyond.samdasoo.todo.entity.TodoStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TodoResponseDto {

    private Long no;
    private String title;
    private String todoCls;
    private String priority;
    private LocalDate dueDate;
    private TodoStatus status;
    private String privateYn;
    private String content;
    private Long userNo;

    public TodoResponseDto(Todo todo) {
        this.no = todo.getNo();
        this.title = todo.getTitle();
        this.todoCls = todo.getTodoCls();
        this.priority = todo.getPriority();
        this.dueDate = todo.getDueDate();
        this.status = todo.getStatus();
        this.privateYn = todo.getPrivateYn();
        this.content = todo.getContent();
        this.userNo = todo.getUserNo().getId();
    }

}
