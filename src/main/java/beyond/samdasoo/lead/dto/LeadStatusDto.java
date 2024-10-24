package beyond.samdasoo.lead.dto;

import beyond.samdasoo.lead.Entity.LeadStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LeadStatusDto {
    private LeadStatus status;
    private long count;
}
