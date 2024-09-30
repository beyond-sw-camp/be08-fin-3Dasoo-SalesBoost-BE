package beyond.samdasoo.todo.controller;

import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.todo.dto.TodoRequestDto;
import beyond.samdasoo.todo.dto.TodoResponseDto;
import beyond.samdasoo.todo.dto.TodoUpdateDto;
import beyond.samdasoo.todo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="todo", description = "할 일 API")
@RestController
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    @Operation(summary = "할일 등록", description = "새로운 할 일 등록")
    public BaseResponse<TodoResponseDto> createTodo(@RequestBody TodoRequestDto todoRequestDto) {
        TodoResponseDto responseDto = todoService.createTodo(todoRequestDto);
        return new BaseResponse<>(responseDto);
    }

    @GetMapping("/{no}")
    @Operation(summary = "할일 조회", description = "특정 할 일 조회")
    public ResponseEntity<TodoResponseDto> getTodoById(@PathVariable("no") Long no) {
        TodoResponseDto responseDto = todoService.getTodoById(no);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/{no}")
    @Operation(summary = "할일 수정", description = "특정 할 일 수정")
    public ResponseEntity<Void> patchUpdateTodo(@PathVariable("no") Long no, @RequestBody TodoUpdateDto todoUpdateDto) {
        todoService.updateTodo(no, todoUpdateDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{no}")
    @Operation(summary = "할일 삭제", description = "특정 할 일 삭제")
    public ResponseEntity<Void> deleteTodo(@PathVariable("no") Long no) {
        todoService.deleteTodo(no);
        return ResponseEntity.noContent().build();
    }
}
