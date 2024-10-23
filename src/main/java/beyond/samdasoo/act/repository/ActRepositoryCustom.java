package beyond.samdasoo.act.repository;

import beyond.samdasoo.act.dto.ActStatusDto;

import java.time.LocalDate;

public interface ActRepositoryCustom {
    ActStatusDto findActStatus(LocalDate searchDate, Long userNo);
}
