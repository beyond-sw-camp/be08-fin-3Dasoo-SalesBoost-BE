package beyond.samdasoo.todo.dto;

import beyond.samdasoo.todo.entity.Todo;
import beyond.samdasoo.todo.entity.TodoStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TodoResponseDto {
    private Long todoNo;
    private Long userNo;
    private Long planNo;
    private String title;
    private String todoCls;
    private String priority;
    private LocalDate dueDate;
    private TodoStatus status;
    private String privateYn;
    private String content;

    public TodoResponseDto(Todo todo) {
        this.todoNo = todo.getNo();
        this.userNo = todo.getUser().getId();
        this.planNo = todo.getPlan() != null ? todo.getPlan().getNo() : null;
        this.title = todo.getTitle();
        this.todoCls = todo.getTodoCls();
        this.priority = todo.getPriority();
        this.dueDate = todo.getDueDate();
        this.status = todo.getStatus();
        this.privateYn = todo.getPrivateYn();
        this.content = todo.getContent();
    }

}
