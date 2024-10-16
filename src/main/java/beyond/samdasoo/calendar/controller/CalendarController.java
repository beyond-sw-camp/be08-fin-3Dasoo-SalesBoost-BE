package beyond.samdasoo.calendar.controller;

import beyond.samdasoo.calendar.dto.CalendarRequestDto;
import beyond.samdasoo.calendar.dto.CalendarResponseDto;
import beyond.samdasoo.calendar.service.CalendarService;
import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.common.response.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static beyond.samdasoo.common.response.BaseResponseStatus.CALENDAR_ALREADY_EXIST;

@RestController
@RequestMapping("/api/calendars")
@Tag(name="Calendars APIs", description = "캘린더 API")
public class CalendarController {
    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PostMapping
    @Operation(summary = "캘린더 등록", description = "새로운 캘린더 등록")
    public ResponseEntity<BaseResponse<CalendarResponseDto>> createCalendar(@RequestBody CalendarRequestDto requestDto) {
        boolean calendarExists = calendarService.existsByUserId(requestDto.getUserNo());

        if (calendarExists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new BaseResponse<>(CALENDAR_ALREADY_EXIST));
        }
        CalendarResponseDto responseDto = calendarService.createCalendar(requestDto);
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

    @GetMapping
    @Operation(summary = "전체 캘린더 조회", description = "모든 캘린더 조회")
    public ResponseEntity<BaseResponse<List<CalendarResponseDto>>> getAllCalendars() {
        try{
            List<CalendarResponseDto> calendars = calendarService.getAllCalendars();
            return ResponseEntity.ok(new BaseResponse<>(calendars));
        }catch (BaseException ex) {
            BaseResponseStatus status = ex.getStatus();
            return new ResponseEntity<>(new BaseResponse<>(status), HttpStatus.valueOf(status.getCode()));
        }
    }
//    @PatchMapping("/{no}")
//    @Operation(summary = "캘린더 수정", description = "특정 캘린더를 수정합니다.")
//    public ResponseEntity<BaseResponse<String>> updateCalendar(@PathVariable Long no, @RequestBody CalendarRequestDto calendarRequestDto) {
//        try {
//            calendarService.updateCalendar(no, calendarRequestDto);
//            return ResponseEntity.ok(new BaseResponse<>("캘린더를 수정하였습니다"));
//        } catch (BaseException ex) {
//            BaseResponseStatus status = ex.getStatus();
//            return new ResponseEntity<>(new BaseResponse<>(status), HttpStatus.valueOf(status.getCode()));
//        }
//    }

    @GetMapping("/user/{userId}/exists")
    @Operation(summary = "캘린더 존재 여부 확인", description = "캘린더 보유 여부 조회")
    public ResponseEntity<BaseResponse<Boolean>> checkCalendarExists(@PathVariable Long userId) {
        boolean exists = calendarService.existsByUserId(userId);
        return ResponseEntity.ok(new BaseResponse<>(exists));
    }

}
