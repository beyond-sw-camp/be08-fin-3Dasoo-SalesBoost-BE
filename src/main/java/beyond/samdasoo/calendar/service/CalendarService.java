package beyond.samdasoo.calendar.service;

import beyond.samdasoo.act.entity.Act;
import beyond.samdasoo.calendar.dto.CalendarResponseDto;
import beyond.samdasoo.calendar.entity.Calendar;
import beyond.samdasoo.calendar.repository.CalendarRepository;
import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.plan.entity.Plan;
import beyond.samdasoo.todo.entity.Todo;
import beyond.samdasoo.user.entity.User;
import beyond.samdasoo.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static beyond.samdasoo.common.response.BaseResponseStatus.CALENDAR_ALREADY_EXIST;
import static beyond.samdasoo.common.response.BaseResponseStatus.CALENDAR_NOT_EXIST;

@Service
public class CalendarService {
    private final CalendarRepository cRepo;
    private final UserRepository userRepository;

    public CalendarService(CalendarRepository calendarRepository, UserRepository userRepository) {
        this.cRepo = calendarRepository;
        this.userRepository = userRepository;
    }

    public CalendarResponseDto createCalendar(User user) {
        if (cRepo.existsByUser(user)) {
            throw new BaseException(CALENDAR_ALREADY_EXIST);
        }
        Calendar calendar = Calendar.builder()
                .user(user)
                .plans(new ArrayList<>())
                .todos(new ArrayList<>())
                .acts(new ArrayList<>())
                .build();

        calendar = cRepo.save(calendar);
        return new CalendarResponseDto(calendar);
    }

    public CalendarResponseDto getCalendarByNo(Long no) {
        Calendar calendar = cRepo.findCalendarById(no);
        return new CalendarResponseDto(calendar);
    }

    // 사용자 관련 모든 데이터
    public CalendarResponseDto getCalendarDataByUser(User user) {
        Calendar calendar = cRepo.findByUser(user)
                .orElseThrow(() -> new BaseException(CALENDAR_NOT_EXIST));

        List<Todo> todos = calendar.getTodos();
        List<Plan> plans = calendar.getPlans();
        List<Act> acts = calendar.getActs();

        return new CalendarResponseDto(calendar, todos, plans, acts);
    }

    public List<CalendarResponseDto> getAllCalendars() {
        return cRepo.findAll().stream()
                .map(CalendarResponseDto::new)
                .collect(Collectors.toList());
    }

    public boolean existsByUser(User user) {
        return cRepo.existsByUser(user);
    }

}
