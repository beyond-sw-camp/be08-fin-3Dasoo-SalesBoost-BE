package beyond.samdasoo.lead.repository;

import beyond.samdasoo.lead.dto.LeadStatusDto;

import java.time.LocalDate;
import java.util.List;

public interface LeadRepositoryCustom {
    List<LeadStatusDto> findLeadStatusGroupedByStatus(LocalDate searchDate, Long userNo);
}
