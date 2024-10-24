package beyond.samdasoo.plan.service;

import beyond.samdasoo.calendar.entity.Calendar;
import beyond.samdasoo.calendar.repository.CalendarRepository;
import beyond.samdasoo.calendar.service.CalendarServiceTest;
import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.plan.dto.PlanRequestDto;
import beyond.samdasoo.plan.dto.PlanResponseDto;
import beyond.samdasoo.plan.dto.PlanUpdateDto;
import beyond.samdasoo.plan.entity.Plan;
import beyond.samdasoo.plan.repository.PlanRepository;
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

import static beyond.samdasoo.common.response.BaseResponseStatus.CALENDAR_NOT_EXIST;
import static beyond.samdasoo.common.response.BaseResponseStatus.PLAN_NOT_EXIST;
import static beyond.samdasoo.plan.entity.PlanStatus.COMPANY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class PlanServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(CalendarServiceTest.class);

    @Mock
    private CalendarRepository calendarRepository;

    @Mock
    private PlanRepository planRepository;


    @InjectMocks
    private PlanService planService;
    private Calendar calendar;

    @BeforeEach
    void setUp() {
        calendar = new Calendar();
        calendar.setNo(1L);
    }

    @Test
    @DisplayName("성공: 일정 등록")
    public void testCreatePlan() {
        PlanRequestDto planRequestDto = new PlanRequestDto(
                "Y",1L, COMPANY,
                LocalDate.now(), "새로운 일정", "09:30:00", "10:30:00", "내용");
        
        given(calendarRepository.findCalendarById(1L)).willReturn(calendar);
        given(planRepository.save(any(Plan.class))).willAnswer(invocation -> invocation.getArgument(0));

        PlanResponseDto responseDto = planService.createPlan(planRequestDto);

        assertNotNull(responseDto);
        assertEquals("새로운 일정", responseDto.getTitle());

        logger.info("일정 생성 테스트, 반환된 response: {}", responseDto);
    }

    @Test
    @DisplayName("성공: 일정 수정")
    public void testUpdatePlan() {
        PlanUpdateDto updateDto = new PlanUpdateDto("Y",1L, COMPANY,
                LocalDate.now(), "수정된 일정", "09:30:00", "10:30:00", "수정된 내용");
        Plan plan = new Plan();
        plan.setNo(1L);
        plan.setTitle("기존 일정");

        Calendar calendar = new Calendar();
        calendar.setNo(1L);

        given(planRepository.findById(1L)).willReturn(Optional.of(plan));
        given(calendarRepository.findCalendarById(1L)).willReturn(calendar);
        given(planRepository.save(any(Plan.class))).willAnswer(invocation -> invocation.getArgument(0));

        PlanResponseDto responseDto = planService.updatePlan(1L, updateDto);

        assertNotNull(responseDto);
        assertEquals("수정된 일정", responseDto.getTitle());
        assertEquals(COMPANY, responseDto.getPlanCls());
        logger.info("일정 수정 테스트, 반환된 response: {}", responseDto);
    }

    @Test
    @DisplayName("성공: 일정 삭제")
    public void testDeletePlan() {

        Plan plan = new Plan();
        plan.setNo(1L);
        given(planRepository.findById(1L)).willReturn(Optional.of(plan));
        doNothing().when(planRepository).delete(plan);

        planService.deletePlan(1L);

        verify(planRepository, times(1)).delete(plan);
    }

    @Test
    @DisplayName("실패: 존재하지 않는 일정 삭제")
    public void testDeletePlanNotExist(){
        given(planRepository.findById(99L)).willReturn(Optional.empty());

        BaseException exception = assertThrows(BaseException.class, () -> {
            planService.deletePlan(99L);
        });
        assertEquals(PLAN_NOT_EXIST, exception.getStatus());

        logger.info("예외 발생: {} | Msg: {}", exception.getStatus(), exception.getMessage());
    }
}