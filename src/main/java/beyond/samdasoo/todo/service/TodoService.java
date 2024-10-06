package beyond.samdasoo.todo.service;

import beyond.samdasoo.calendar.entity.Calendar;
import beyond.samdasoo.calendar.repository.CalendarRepository;
import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.plan.repository.PlanRepository;
import beyond.samdasoo.todo.dto.TodoRequestDto;
import beyond.samdasoo.todo.dto.TodoResponseDto;
import beyond.samdasoo.todo.dto.TodoUpdateDto;
import beyond.samdasoo.todo.entity.Todo;
import beyond.samdasoo.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static beyond.samdasoo.common.response.BaseResponseStatus.CALENDAR_NOT_EXIST;
import static beyond.samdasoo.common.response.BaseResponseStatus.TODO_NOT_EXIST;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final CalendarRepository calendarRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository, CalendarRepository calendarRepository) {
        this.todoRepository = todoRepository;
        this.calendarRepository = calendarRepository;
    }

    private Todo findById(Long no){
        return todoRepository.findById(no)
                .orElseThrow(() -> new BaseException(TODO_NOT_EXIST));
    }

    @Transactional(readOnly = true)
    public TodoResponseDto getTodoById(Long no) { return new TodoResponseDto(findById(no)); }

    @Transactional(readOnly = true)
    public List<TodoResponseDto> getAllTodos() throws BaseException {
        return todoRepository.findAll().stream()
                .map(todo -> new TodoResponseDto(todo))
                .collect(Collectors.toList());
    }

    @Transactional
    public TodoResponseDto createTodo(TodoRequestDto todoRequestDto){


        Todo todo = Todo.builder()
                .title(todoRequestDto.getTitle())
                .todoCls(todoRequestDto.getTodoCls())
                .priority(todoRequestDto.getPriority())
                .dueDate(todoRequestDto.getDueDate())
                .status(todoRequestDto.getStatus())
                .privateYn(todoRequestDto.getPrivateYn())
                .content(todoRequestDto.getContent())
                .calendar(calendarRepository.findCalendarById(todoRequestDto.getCalendarNo()))
                .build();

        todo = todoRepository.save(todo);
        return new TodoResponseDto(todo);
    }

    @Transactional
    public void updateTodo(Long no, TodoUpdateDto todoUpdateDto) {
        Todo todo = findById(no);

        todo.setCalendar(calendarRepository.findCalendarById(todoUpdateDto.getCalendarNo()));
        Optional.ofNullable(todoUpdateDto.getTitle()).ifPresent(todo::setTitle);
        Optional.ofNullable(todoUpdateDto.getTodoCls()).ifPresent(todo::setTodoCls);
        Optional.ofNullable(todoUpdateDto.getPriority()).ifPresent(todo::setPriority);
        Optional.ofNullable(todoUpdateDto.getDueDate()).ifPresent(todo::setDueDate);
        Optional.ofNullable(todoUpdateDto.getPrivateYn()).ifPresent(todo::setPrivateYn);
        Optional.ofNullable(todoUpdateDto.getContent()).ifPresent(todo::setContent);
        Optional.ofNullable(todoUpdateDto.getStatus()).ifPresent(todo::setStatus);
    }

    public void deleteTodo(Long no) { todoRepository.delete(findById(no)); }
}
