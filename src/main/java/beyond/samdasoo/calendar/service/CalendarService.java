package beyond.samdasoo.calendar.service;

import beyond.samdasoo.calendar.dto.CalendarRequestDto;
import beyond.samdasoo.calendar.dto.CalendarResponseDto;
import beyond.samdasoo.calendar.entity.Calendar;
import beyond.samdasoo.calendar.repository.CalendarRepository;
import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.user.entity.User;
import beyond.samdasoo.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static beyond.samdasoo.common.response.BaseResponseStatus.USER_NOT_EXIST;

@Service
public class CalendarService {
    private final CalendarRepository cRepo;
    private final UserRepository userRepository;

    public CalendarService(CalendarRepository calendarRepository, UserRepository userRepository) {
        this.cRepo = calendarRepository;
        this.userRepository = userRepository;
    }

    public CalendarResponseDto createCalendar(CalendarRequestDto requestDto) {

        User user = userRepository.findById(requestDto.getUserNo())
                .orElseThrow(() -> new BaseException(USER_NOT_EXIST));

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

    public List<CalendarResponseDto> getAllCalendars() {
        return cRepo.findAll().stream()
                .map(CalendarResponseDto::new)
                .collect(Collectors.toList());
    }

//    @Transactional
//    public void updateCalendar(Long no, CalendarRequestDto calendarRequestDto) {
//        Calendar calendar = cRepo.findCalendarById(no);
//        User user = userRepository.findById(calendarRequestDto.getUserNo())
//                .orElseThrow(() -> new BaseException(USER_NOT_EXIST));
//
//        calendar.setUser(user);
//
//        cRepo.save(calendar);
//    }

    public boolean existsByUserId(Long userId) {
        return cRepo.existsByUser_Id(userId);
    }

}
