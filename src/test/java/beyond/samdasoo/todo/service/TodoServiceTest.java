package beyond.samdasoo.todo.service;

import beyond.samdasoo.calendar.entity.Calendar;
import beyond.samdasoo.calendar.repository.CalendarRepository;
import beyond.samdasoo.calendar.service.CalendarServiceTest;
import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.todo.dto.TodoRequestDto;
import beyond.samdasoo.todo.dto.TodoResponseDto;
import beyond.samdasoo.todo.dto.TodoUpdateDto;
import beyond.samdasoo.todo.entity.Todo;
import beyond.samdasoo.todo.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static beyond.samdasoo.common.response.BaseResponseStatus.TODO_NOT_EXIST;
import static beyond.samdasoo.todo.entity.TodoStatus.DONE;
import static beyond.samdasoo.todo.entity.TodoStatus.TODO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(CalendarServiceTest.class);

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private CalendarRepository calendarRepository;

    @InjectMocks
    private TodoService todoService;

    private Calendar calendar;

    @BeforeEach
    void setUp() {
        calendar = new Calendar();
        calendar.setNo(1L);
    }

    @Test
    @DisplayName("성공: 할 일 생성")
    public void testCreateTodo() {
        TodoRequestDto todoRequestDto = new TodoRequestDto(
                1L, "새로운 할 일", "TODO", "HIGH", LocalDate.now(),
                TODO, "Y", "내용", 1L);

        given(calendarRepository.findCalendarById(1L)).willReturn(calendar);
        given(todoRepository.save(any(Todo.class))).willAnswer(invocation -> invocation.getArgument(0));

        TodoResponseDto responseDto = todoService.createTodo(todoRequestDto);

        assertNotNull(responseDto);
        assertEquals("새로운 할 일", responseDto.getTitle());

        logger.info("할 일 생성 테스트, 반환된 response: {}", responseDto);
    }

    @Test
    @DisplayName("성공: 할 일 수정")
    public void testUpdateTodo() {
        TodoUpdateDto updateDto = new TodoUpdateDto(
                1L, "수정된 할 일", "DONE", "HIGH", LocalDate.now(),
                DONE, "Y", "수정된 내용");
        Todo todo = new Todo();
        todo.setNo(1L);
        todo.setTitle("기존 할 일");

        Calendar calendar = new Calendar();
        calendar.setNo(1L);

        given(todoRepository.findById(1L)).willReturn(Optional.of(todo));
        given(calendarRepository.findCalendarById(1L)).willReturn(calendar);
        given(todoRepository.save(any(Todo.class))).willAnswer(invocation -> invocation.getArgument(0));//todo 반환

        TodoResponseDto responseDto = todoService.updateTodo(1L, updateDto);

        assertNotNull(responseDto);
        assertEquals("수정된 할 일", responseDto.getTitle());
        assertEquals(DONE, responseDto.getStatus());
        logger.info("할 일 수정 테스트, 반환된 response: {}", responseDto);
    }

    @Test
    @DisplayName("성공: 할 일 삭제")
    public void testDeleteTodo() {

        Todo todo = new Todo();
        todo.setNo(1L);
        given(todoRepository.findById(1L)).willReturn(Optional.of(todo));
        doNothing().when(todoRepository).delete(todo);

        todoService.deleteTodo(1L);

        verify(todoRepository, times(1)).delete(todo);
    }


    @Test
    @DisplayName("실패: 존재하지 않는 할 일 삭제")
    public void testDeleteTodoNotExist(){
        given(todoRepository.findById(99L)).willReturn(Optional.empty());

        BaseException exception = assertThrows(BaseException.class, () -> {
            todoService.deleteTodo(99L);
        });
        assertEquals(TODO_NOT_EXIST, exception.getStatus());

        logger.info("예외 발생: {} | Msg: {}", exception.getStatus(), exception.getMessage());
    }
}
