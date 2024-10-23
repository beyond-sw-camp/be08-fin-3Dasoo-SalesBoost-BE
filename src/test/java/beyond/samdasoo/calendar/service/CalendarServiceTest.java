package beyond.samdasoo.calendar.service;

import beyond.samdasoo.admin.entity.Department;
import beyond.samdasoo.admin.repository.DepartmentRepository;
import beyond.samdasoo.calendar.dto.CalendarResponseDto;
import beyond.samdasoo.calendar.entity.Calendar;
import beyond.samdasoo.calendar.repository.CalendarRepository;
import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.user.dto.UserRole;
import beyond.samdasoo.user.entity.User;
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

import java.util.ArrayList;
import java.util.Optional;

import static beyond.samdasoo.common.response.BaseResponseStatus.CALENDAR_ALREADY_EXIST;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class CalendarServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(CalendarServiceTest.class);

    @Mock
    private CalendarRepository calendarRepository;

    @InjectMocks
    private CalendarService calendarService;

    private User user;
    private Calendar testCalendar;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .deptNo(1L)
                .deptCode("001")
                .deptName("인사부")
                .build();

        user = User.builder()
                .name("TEST")
                .email("test@test.com")
                .password("1234")
                .employeeId("20241023001")
                .department(department)
                .role(UserRole.USER)
                .build();

        testCalendar = new Calendar(1L, user, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    @Test
    @DisplayName("성공: 캘린더 생성")
    void testCreateCalendar() {
        // Given: 사용자 정보가 존재하고, 캘린더를 보유하지 X
        when(calendarRepository.save(any(Calendar.class))).thenReturn(testCalendar);

        // When: createCalendar 호출
        CalendarResponseDto response = calendarService.createCalendar(user);

        // Then: 캘린더가 생성되고 CalendaResDto 반환
        assertNotNull(response);
        assertEquals(1L, response.getCalendarNo());
        assertEquals(user.getName(), response.getUserName());

        logger.info("캘린더 생성 테스트, 반환된 response: {}", response);
    }

    @Test
    @DisplayName("성공: 캘린더 보유 시, 캘린더 데이터를 반환")
    void testGetCalendarDataByUser() {
        // Given: 사용자가 캘린더 보유
        when(calendarRepository.findByUser(user)).thenReturn(Optional.of(testCalendar));

        // When: getCalendarDataByUser 메서드를 호출
        CalendarResponseDto response = calendarService.getCalendarDataByUser(user);

        // Then: CalendarResponseDto 반환
        assertNotNull(response);
        assertEquals(testCalendar.getNo(), response.getCalendarNo());
        assertEquals(user.getName(), response.getUserName());

        logger.info("캘린더 생성 테스트, 반환된 response: {}", response);
    }

    @Test
    @DisplayName("실패: 캘린더 미보유 상태에서 데이터 조회")
    void testGetCalendarDataByUserNotFound() {
        // Given: 사용자가 캘린더 보유 X
        when(calendarRepository.findByUser(user)).thenReturn(Optional.empty());

        // When & Then: getCalendarDataByUser 호출, BaseException 발생
        BaseException exception = assertThrows(BaseException.class, () -> calendarService.getCalendarDataByUser(user));

        // ++ findByUser 불필요한 호출 방지 검증
        verify(calendarRepository, times(1)).findByUser(user);
        logger.info("예외 발생: {} | Msg: {}", exception.getStatus(), exception.getMessage());
    }

    @Test
    @DisplayName("실패: 캘린더 보유 상태에서 캘린더 생성 요청")
    void testCreateCalendarAlreadyCalendar() {
        // Given: 사용자가 캘린더를 보유하고 있는 경우
        when(calendarRepository.existsByUser(user)).thenReturn(true);

        // When & Then: createCalendar 호출, BaseException 발생
        BaseException exception = assertThrows(BaseException.class, () -> calendarService.createCalendar(user));
        assertEquals(CALENDAR_ALREADY_EXIST, exception.getStatus());

        // ++ save 불필요한 저장 확인
        verify(calendarRepository, never()).save(any(Calendar.class));
        logger.info("예외 발생: {} | Msg: {}", exception.getStatus(), exception.getMessage());

    }
}
