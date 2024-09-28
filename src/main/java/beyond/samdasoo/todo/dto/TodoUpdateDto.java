package beyond.samdasoo.todo.dto;

import beyond.samdasoo.todo.entity.TodoStatus;
import lombok.Data;

import java.util.Date;

@Data
public class TodoUpdateDto {
    private String title;
    private String todoCls;
    private String priority;
    private Date dueDate;
    private String privateYn;
    private String content;
    private TodoStatus status;
}
