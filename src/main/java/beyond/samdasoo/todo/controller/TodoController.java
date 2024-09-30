package beyond.samdasoo.todo.controller;

import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.todo.dto.TodoRequestDto;
import beyond.samdasoo.todo.dto.TodoResponseDto;
import beyond.samdasoo.todo.dto.TodoUpdateDto;
import beyond.samdasoo.todo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static beyond.samdasoo.common.response.BaseResponseStatus.TODO_NOT_EXIST;

@RestController
@RequestMapping("/api/todos")
@Tag(name="Todo APIs", description = "할 일 API")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    @Operation(summary = "할 일 등록", description = "새로운 할 일 등록")
    public BaseResponse<TodoResponseDto> createTodo(@RequestBody TodoRequestDto todoRequestDto) {
        TodoResponseDto responseDto = todoService.createTodo(todoRequestDto);
        return new BaseResponse<>(responseDto);
    }

    @GetMapping("/{no}")
    @Operation(summary = "할 일 조회", description = "특정 할 일 조회")
    public BaseResponse<TodoResponseDto> getTodoById(@PathVariable("no") Long no) {
        TodoResponseDto responseDto = todoService.getTodoById(no);
        return new BaseResponse<>(responseDto);
    }

    @PatchMapping("/{no}")
    @Operation(summary = "할 일 수정", description = "특정 할 일 수정")
    public BaseResponse<String> patchUpdateTodo(@PathVariable("no") Long no, @RequestBody TodoUpdateDto todoUpdateDto) {
        try{
            todoService.updateTodo(no, todoUpdateDto);
            return new BaseResponse<>("할 일을 수정하였습니다.");
        }catch (Exception e) {
            return new BaseResponse<>(TODO_NOT_EXIST);
        }
    }

    @DeleteMapping("/{no}")
    @Operation(summary = "할일 삭제", description = "특정 할 일 삭제")
    public BaseResponse<String> deleteTodo(@PathVariable("no") Long no) {
        try{
            todoService.deleteTodo(no);
            return new BaseResponse<>("할 일 삭제에 성공했습니다.");
        }catch (Exception e) {
            return new BaseResponse<>(TODO_NOT_EXIST);
        }
    }
}
