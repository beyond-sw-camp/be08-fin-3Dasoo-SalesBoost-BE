package beyond.samdasoo.estimate.dto;

import beyond.samdasoo.estimate.entity.EstProduct;
import lombok.Data;

@Data
public class EstProductResponseDto {

    private Long estProdNo;  // 견적번호 (PK)
    private int unitAmt;    // 단가
    private int discount;   // 할인율
    private int unitPropAmt;    // 판매단가
    private int qty;  // 수량
    private int supplyPrice;  // 공급가액
    private int tax;  // 세액
    private int totalAmt;  // 합계금액
    private Long prodNo;  // 제품번호 (FK)
    private Long estNo;  // 견적번호 (FK)

    public EstProductResponseDto(EstProduct estProduct) {
        this.estProdNo = estProduct.getNo();
        this.unitAmt = estProduct.getUnitAmt();
        this.discount = estProduct.getDiscount();
        this.unitPropAmt = estProduct.getUnitPropAmt();
        this.qty = estProduct.getQty();
        this.supplyPrice = estProduct.getSupplyPrice();
        this.tax = estProduct.getTax();
        this.totalAmt = estProduct.getTotalAmt();
        this.prodNo = estProduct.getProduct().getProdNo();
        this.estNo = estProduct.getEstimate().getEstNo();
    }
}
