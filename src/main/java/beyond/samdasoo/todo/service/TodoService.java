package beyond.samdasoo.todo.service;

import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.plan.entity.Plan;
import beyond.samdasoo.plan.repository.PlanRepository;
import beyond.samdasoo.todo.dto.TodoRequestDto;
import beyond.samdasoo.todo.dto.TodoResponseDto;
import beyond.samdasoo.todo.dto.TodoUpdateDto;
import beyond.samdasoo.todo.entity.Todo;
import beyond.samdasoo.todo.repository.TodoRepository;
import beyond.samdasoo.user.entity.User;
import beyond.samdasoo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static beyond.samdasoo.common.response.BaseResponseStatus.*;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final PlanRepository planRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository, UserRepository userRepository, PlanRepository planRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
        this.planRepository = planRepository;
    }

    private Todo findById(Long no){
        return todoRepository.findById(no)
                .orElseThrow(() -> new BaseException(TODO_NOT_EXIST));
    }

    @Transactional
    public TodoResponseDto createTodo(TodoRequestDto todoRequestDto){

        User user = userRepository.findById(todoRequestDto.getUserNo())
                .orElseThrow(() -> new BaseException(USER_NOT_EXIST));

        Plan plan = todoRequestDto.getPlanNo() != null ? planRepository.findById(todoRequestDto.getPlanNo())
                .orElseThrow(() -> new BaseException(PLAN_NOT_EXIST)) : null;

        Todo todo = Todo.builder()
                .title(todoRequestDto.getTitle())
                .todoCls(todoRequestDto.getTodoCls())
                .priority(todoRequestDto.getPriority())
                .dueDate(todoRequestDto.getDueDate())
                .status(todoRequestDto.getStatus())
                .privateYn(todoRequestDto.getPrivateYn())
                .content(todoRequestDto.getContent())
                .user(user)
                .plan(plan)
                .build();

        todo = todoRepository.save(todo);

        return new TodoResponseDto(todo);
    }

    @Transactional(readOnly = true)
    public TodoResponseDto getTodoById(Long no) { return new TodoResponseDto(findById(no)); }

    @Transactional
    public void updateTodo(Long no, TodoUpdateDto todoUpdateDto) {
        Todo todo = findById(no);

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
