package beyond.samdasoo.todo.controller;

import beyond.samdasoo.todo.dto.TodoRequestDto;
import beyond.samdasoo.todo.dto.TodoResponseDto;
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

}