package beyond.samdasoo.todo.service;

import beyond.samdasoo.member.entity.Member;
import beyond.samdasoo.member.repository.MemberRepository;
import beyond.samdasoo.todo.dto.TodoRequestDto;
import beyond.samdasoo.todo.dto.TodoResponseDto;
import beyond.samdasoo.todo.entity.Todo;
import beyond.samdasoo.todo.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        Todo todo = new Todo();
        todo.setTitle(todoRequestDto.getTitle());
        todo.setTodoCls(todoRequestDto.getTodoCls());
        todo.setPriority(todoRequestDto.getPriority());
        todo.setDueDate(todoRequestDto.getDueDate());
        todo.setNotiYn(todoRequestDto.getNotiYn());
        todo.setNotiDay(todoRequestDto.getNotiDay());
        todo.setStatus(todoRequestDto.getStatus());
        todo.setPrivateYn(todoRequestDto.getPrivateYn());
        todo.setContent(todoRequestDto.getContent());
        todo.setMemberNo(member);

        todo = todoRepository.save(todo);

        return new TodoResponseDto(todo);
    }

    @Transactional(readOnly = true)
    public TodoResponseDto getTodoById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("할 일을 찾을 수 없습니다: " + id));
        return new TodoResponseDto(todo);
    }


}
