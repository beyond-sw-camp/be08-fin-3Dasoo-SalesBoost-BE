package beyond.samdasoo.calendar.controller;

import beyond.samdasoo.calendar.dto.CalendarResponseDto;
import beyond.samdasoo.calendar.service.CalendarService;
import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.common.response.BaseResponseStatus;
import beyond.samdasoo.user.entity.User;
import beyond.samdasoo.user.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static beyond.samdasoo.common.response.BaseResponseStatus.CALENDAR_ALREADY_EXIST;
import static beyond.samdasoo.common.response.BaseResponseStatus.USER_NOT_EXIST;

@RestController
@RequestMapping("/api/calendars")
@Tag(name = "Calendars APIs", description = "캘린더 API")
public class CalendarController {
    private final CalendarService calendarService;
    private final UserRepository userRepository;

    public CalendarController(CalendarService calendarService, UserRepository userRepository) {
        this.calendarService = calendarService;
        this.userRepository = userRepository;
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email;
        // 일단 이메일만
        if (authentication.getPrincipal() instanceof String) {
            email = (String) authentication.getPrincipal();
        } else {
            throw new BaseException(USER_NOT_EXIST);
        }

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(USER_NOT_EXIST));
    }

    @PostMapping
    @Operation(summary = "캘린더 등록", description = "새로운 캘린더 등록")
    public ResponseEntity<BaseResponse<CalendarResponseDto>> createCalendar() {
        User user = getAuthenticatedUser();
        boolean calendarExists = calendarService.existsByUser(user);

        if (calendarExists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new BaseResponse<>(CALENDAR_ALREADY_EXIST));
        }
        CalendarResponseDto responseDto = calendarService.createCalendar(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse<>(responseDto));
    }

    @GetMapping("/{no}")
    @Operation(summary = "캘린더 조회", description = "특정 캘린더 조회")
    public ResponseEntity<BaseResponse<CalendarResponseDto>> getCalendar(@PathVariable Long no) {
        try {
            CalendarResponseDto responseDto = calendarService.getCalendarByNo(no);
            return ResponseEntity.ok(new BaseResponse<>(responseDto));
        } catch (BaseException ex) {
            BaseResponseStatus status = ex.getStatus();
            return new ResponseEntity<>(new BaseResponse<>(status), HttpStatus.valueOf(status.getCode()));
        }
    }

    @GetMapping("/user/exists")
    @Operation(summary = "캘린더 존재 여부 확인", description = "로그인한 사용자의 캘린더 보유 여부 조회")
    public ResponseEntity<BaseResponse<Boolean>> checkCalendarExists() {
        User user = getAuthenticatedUser();
        boolean exists = calendarService.existsByUser(user);
        return ResponseEntity.ok(new BaseResponse<>(exists));
    }

    @GetMapping("/user/data")
    @Operation(summary = "사용자의 캘린더 조회", description = "로그인한 사용자의 캘린더를 조회")
    public ResponseEntity<BaseResponse<CalendarResponseDto>> getUserCalendarData() {
        User user = getAuthenticatedUser();
        try {
            CalendarResponseDto responseDto = calendarService.getCalendarDataByUser(user);
            return ResponseEntity.ok(new BaseResponse<>(responseDto));
        } catch (BaseException ex) {
            BaseResponseStatus status = ex.getStatus();
            return new ResponseEntity<>(new BaseResponse<>(status), HttpStatus.valueOf(status.getCode()));
        }
    }

    @GetMapping
    @Operation(summary = "전체 캘린더 조회", description = "모든 캘린더 조회")
    public ResponseEntity<BaseResponse<List<CalendarResponseDto>>> getAllCalendars() {
        try {
            List<CalendarResponseDto> calendars = calendarService.getAllCalendars();
            return ResponseEntity.ok(new BaseResponse<>(calendars));
        } catch (BaseException ex) {
            BaseResponseStatus status = ex.getStatus();
            return new ResponseEntity<>(new BaseResponse<>(status), HttpStatus.valueOf(status.getCode()));
        }
    }
}
