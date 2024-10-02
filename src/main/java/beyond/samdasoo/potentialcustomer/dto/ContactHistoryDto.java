package beyond.samdasoo.potentialcustomer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class ContactHistoryDto {

    private LocalDate contactDate;

    private int cls;

    private String content;

}
