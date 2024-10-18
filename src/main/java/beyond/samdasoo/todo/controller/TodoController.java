package beyond.samdasoo.todo.controller;

import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.common.response.BaseResponseStatus;
import beyond.samdasoo.plan.dto.PlanResponseDto;
import beyond.samdasoo.todo.dto.TodoRequestDto;
import beyond.samdasoo.todo.dto.TodoResponseDto;
import beyond.samdasoo.todo.dto.TodoUpdateDto;
import beyond.samdasoo.todo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<BaseResponse<TodoResponseDto>> createTodo(@RequestBody TodoRequestDto todoRequestDto) {
        TodoResponseDto responseDto = todoService.createTodo(todoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse<>(responseDto));
    }

    @GetMapping("/{no}")
    @Operation(summary = "할 일 조회", description = "특정 할 일 조회")
    public ResponseEntity<BaseResponse<TodoResponseDto>> getTodoById(@PathVariable("no") Long no) {
        try{
            TodoResponseDto responseDto = todoService.getTodoById(no);
            return ResponseEntity.ok(new BaseResponse<>(responseDto));
        }catch (BaseException ex) {
            BaseResponseStatus status = ex.getStatus();
            return new ResponseEntity<>(new BaseResponse<>(status), HttpStatus.valueOf(status.getCode()));
        }
    }

    @GetMapping
    @Operation(summary = "전체 할 일 조회", description = "모든 할 일 조회")
    public ResponseEntity<BaseResponse<List<TodoResponseDto>>> getAllTodos() {
        try {
            List<TodoResponseDto> todos = todoService.getAllTodos();
            return ResponseEntity.ok(new BaseResponse<>(todos));
        } catch (BaseException ex) {
            BaseResponseStatus status = ex.getStatus();
            return new ResponseEntity<>(new BaseResponse<>(status), HttpStatus.valueOf(status.getCode()));
        }
    }

    @PatchMapping("/{no}")
    @Operation(summary = "할 일 수정", description = "특정 할 일 수정")
    public ResponseEntity<BaseResponse<TodoResponseDto>> patchUpdateTodo(@PathVariable("no") Long no, @RequestBody TodoUpdateDto todoUpdateDto) {
        try {
            TodoResponseDto updatedTodo = todoService.updateTodo(no, todoUpdateDto);
            return ResponseEntity.ok(new BaseResponse<>(updatedTodo));
        } catch (BaseException ex) {
            BaseResponseStatus status = ex.getStatus();
            return new ResponseEntity<>(new BaseResponse<>(status), HttpStatus.valueOf(status.getCode()));
        }
    }

    @DeleteMapping("/{no}")
    @Operation(summary = "할일 삭제", description = "특정 할 일 삭제")
    public ResponseEntity<BaseResponse<String>> deleteTodo(@PathVariable("no") Long no) {
        try{
            todoService.deleteTodo(no);
            return ResponseEntity.ok(new BaseResponse<>("할 일 삭제에 성공했습니다."));
        } catch (BaseException ex) {
            BaseResponseStatus status = ex.getStatus();
            return new ResponseEntity<>(new BaseResponse<>(status), HttpStatus.valueOf(status.getCode()));
        }
    }
}
