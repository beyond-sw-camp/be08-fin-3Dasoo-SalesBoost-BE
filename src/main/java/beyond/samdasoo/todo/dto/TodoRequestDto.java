package beyond.samdasoo.todo.dto;

import beyond.samdasoo.todo.entity.Todo;
import beyond.samdasoo.todo.entity.TodoStatus;
import lombok.Data;

import java.util.Date;

@Data
public class TodoRequestDto {

    private String title;
    private String todoCls;
    private String priority;
    private Date dueDate;
    private String notiYn;
    private String notiDay;
    private TodoStatus status;
    private String privateYn;
    private String content;
    private Long memberNo;

}
