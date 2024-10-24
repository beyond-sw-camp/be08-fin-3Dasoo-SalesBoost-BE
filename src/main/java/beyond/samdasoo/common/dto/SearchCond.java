package beyond.samdasoo.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@ToString
public class SearchCond {
    private LocalDate searchDate;
    private Long userNo;
}
