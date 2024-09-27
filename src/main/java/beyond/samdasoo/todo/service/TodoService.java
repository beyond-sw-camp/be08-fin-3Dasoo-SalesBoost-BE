package beyond.samdasoo.todo.service;

import beyond.samdasoo.member.entity.Member;
import beyond.samdasoo.member.repository.MemberRepository;
import beyond.samdasoo.todo.dto.TodoRequestDto;
import beyond.samdasoo.todo.dto.TodoResponseDto;
import beyond.samdasoo.todo.dto.TodoUpdateDto;
import beyond.samdasoo.todo.entity.Todo;
import beyond.samdasoo.todo.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    private final MemberRepository memberRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository, MemberRepository memberRepository) {
        this.todoRepository = todoRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public TodoResponseDto createTodo(TodoRequestDto todoRequestDto){

        Member member = memberRepository.findById(todoRequestDto.getMemberNo())
                .orElseThrow(() -> new EntityNotFoundException("회원 ID 조회 불가: " + todoRequestDto.getMemberNo()));

        Todo todo = Todo.builder()
                .title(todoRequestDto.getTitle())
                .todoCls(todoRequestDto.getTodoCls())
                .priority(todoRequestDto.getPriority())
                .dueDate(todoRequestDto.getDueDate())
                .status(todoRequestDto.getStatus())
                .privateYn(todoRequestDto.getPrivateYn())
                .content(todoRequestDto.getContent())
                .memberNo(member)
                .build();

        todo = todoRepository.save(todo);

        return new TodoResponseDto(todo);
    }

    @Transactional(readOnly = true)
    public TodoResponseDto getTodoById(Long no) {
        Todo todo = todoRepository.findById(no)
                .orElseThrow(() -> new RuntimeException("할 일을 찾을 수 없습니다: " + no));
        return new TodoResponseDto(todo);
    }

    @Transactional
    public void updateTodo(Long no, TodoUpdateDto todoUpdateDto) {
        Todo todo = todoRepository.findById(no)
                .orElseThrow(() -> new EntityNotFoundException("할 일을 찾을 수 없습니다: " + no));

        Optional.ofNullable(todoUpdateDto.getTitle()).ifPresent(todo::setTitle);
        Optional.ofNullable(todoUpdateDto.getTodoCls()).ifPresent(todo::setTodoCls);
        Optional.ofNullable(todoUpdateDto.getPriority()).ifPresent(todo::setPriority);
        Optional.ofNullable(todoUpdateDto.getDueDate()).ifPresent(todo::setDueDate);
        Optional.ofNullable(todoUpdateDto.getPrivateYn()).ifPresent(todo::setPrivateYn);
        Optional.ofNullable(todoUpdateDto.getContent()).ifPresent(todo::setContent);
        Optional.ofNullable(todoUpdateDto.getStatus()).ifPresent(todo::setStatus);
    }

    public void deleteTodo(Long no) {
        Todo todo = todoRepository.findById(no)
                .orElseThrow(() -> new RuntimeException("할 일을 찾을 수 없습니다: " + no));

        todoRepository.delete(todo);
    }
}
