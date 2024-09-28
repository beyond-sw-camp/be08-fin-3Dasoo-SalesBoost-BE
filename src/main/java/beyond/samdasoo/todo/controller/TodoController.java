package beyond.samdasoo.todo.controller;

import beyond.samdasoo.todo.dto.TodoRequestDto;
import beyond.samdasoo.todo.dto.TodoResponseDto;
import beyond.samdasoo.todo.dto.TodoUpdateDto;
import beyond.samdasoo.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(@RequestBody TodoRequestDto todoRequestDto) {
        TodoResponseDto responseDto = todoService.createTodo(todoRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{no}")
    public ResponseEntity<TodoResponseDto> getTodoById(@PathVariable("no") Long no) {
        TodoResponseDto responseDto = todoService.getTodoById(no);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/{no}")
    public ResponseEntity<Void> patchUpdateTodo(@PathVariable("no") Long no, @RequestBody TodoUpdateDto todoUpdateDto) {
        todoService.updateTodo(no, todoUpdateDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{no}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("no") Long no) {
        todoService.deleteTodo(no);
        return ResponseEntity.noContent().build();
    }
}
