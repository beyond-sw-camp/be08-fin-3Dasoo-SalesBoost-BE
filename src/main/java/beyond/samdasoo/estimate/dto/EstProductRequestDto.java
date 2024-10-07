package beyond.samdasoo.estimate.dto;

import lombok.Data;

@Data
public class EstProductRequestDto {
    private int unitAmt;
    private int discount;
    private int unitPropAmt;
    private int qty;
    private int supplyPrice;
    private int tax;
    private int totalAmt;
    private Long estNo;
    private Long prodNo;
}
