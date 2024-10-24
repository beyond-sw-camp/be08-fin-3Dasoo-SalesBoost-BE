package beyond.samdasoo.estimate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class CreateEstimateDto {

        // 견적
        private String name;
        private LocalDate estDate;
        private String taxCls;
        private String surtaxYn;
        private int prodCnt;
        private int supplyPrice;
        private int tax;
        private int totalPrice;
        private String note;
        private Long propNo;
        private List<CreateEstProductDto> estProducts;

    }

