package beyond.samdasoo.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TargetSaleRequestDto {

    private String userName;

    private String prodName;

    private int sum;

    private int year;

    private List<Integer> monthTargets;

}

