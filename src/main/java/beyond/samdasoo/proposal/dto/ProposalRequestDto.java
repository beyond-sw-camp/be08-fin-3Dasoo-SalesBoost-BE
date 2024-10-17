package beyond.samdasoo.proposal.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProposalRequestDto {

    private String name;
    private String cont;
    private LocalDate reqDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate submitDate;
    private LocalDate prDate;
    private String note;
    private Long leadNo;

}
