package beyond.samdasoo.calendar.repository;

import beyond.samdasoo.calendar.entity.Calendar;
import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import static beyond.samdasoo.common.response.BaseResponseStatus.CALENDAR_NOT_EXIST;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    default Calendar findCalendarById(Long id) {
        return findById(id).orElseThrow(() -> new BaseException(CALENDAR_NOT_EXIST));
    }
    Optional<Calendar> findByUser(User user);
    boolean existsByUser(User user);
}