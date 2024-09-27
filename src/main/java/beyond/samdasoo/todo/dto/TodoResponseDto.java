package beyond.samdasoo.todo.dto;

import beyond.samdasoo.todo.entity.Todo;
import beyond.samdasoo.todo.entity.TodoStatus;
import lombok.Data;

import java.util.Date;

@Data
public class TodoResponseDto {

    private Long no;
    private String title;
    private String todoCls;
    private String priority;
    private Date dueDate;
    private TodoStatus status;
    private String privateYn;
    private String content;
    private Long memberNo;

    public TodoResponseDto(Todo todo) {
        this.no = todo.getNo();
        this.title = todo.getTitle();
        this.todoCls = todo.getTodoCls();
        this.priority = todo.getPriority();
        this.dueDate = todo.getDueDate();
        this.status = todo.getStatus();
        this.privateYn = todo.getPrivateYn();
        this.content = todo.getContent();
        this.memberNo = todo.getMemberNo().getId();
    }

}
