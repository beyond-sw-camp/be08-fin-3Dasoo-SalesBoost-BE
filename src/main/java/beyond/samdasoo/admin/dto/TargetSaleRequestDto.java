package beyond.samdasoo.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TargetSaleRequestDto {

    private Long userId;

    private Long productNo;

    private int sum;

    private int month;

    private int monthTarget;

    private int year;


}

