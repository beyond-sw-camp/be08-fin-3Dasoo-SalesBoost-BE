package beyond.samdasoo.lead.dto;

import beyond.samdasoo.lead.Entity.LeadStatus;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class LeadSearchCond {
    private LeadStatus status;
    private Long process;
    private Long subProcess;
    private LocalDate startDate;
    private LocalDate endDate;
}
