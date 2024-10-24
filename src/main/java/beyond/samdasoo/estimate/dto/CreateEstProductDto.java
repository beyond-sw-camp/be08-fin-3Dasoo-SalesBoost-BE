package beyond.samdasoo.estimate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateEstProductDto {

        private int unitAmt;
        private int discount;
        private int unitPropAmt;
        private int qty;
        private int supplyPrice;
        private int tax;
        private int totalAmt;
        private Long prodNo; //제품 FK
    }

