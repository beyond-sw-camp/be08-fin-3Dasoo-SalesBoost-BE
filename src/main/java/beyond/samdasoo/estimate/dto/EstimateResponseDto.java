package beyond.samdasoo.estimate.dto;

import beyond.samdasoo.estimate.entity.Estimate;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EstimateResponseDto {

    private Long estNo;  // 견적번호 (PK)
    private String name;  // 견적명
    private LocalDate estDate;  // 견적일
    private String taxCls;  // 과세구분
    private String surtaxYn;  // 단가구분
    private int prodCnt;  // 수량
    private int supplyPrice;  // 공급가액
    private int tax;  // 세액
    private int totalPrice;  // 합계금액
    private String note;  // 비고 (optional)
    private Long prodNo;  // 제품번호 (FK)
    private Long propNo;  // 제안번호 (FK)

    public EstimateResponseDto(Estimate estimate) {
        this.estNo = estimate.getEstNo();
        this.name = estimate.getName();
        this.estDate = estimate.getEstDate();
        this.taxCls = estimate.getTaxCls();
        this.surtaxYn = estimate.getSurtaxYn();
        this.prodCnt = estimate.getProdCnt();
        this.supplyPrice = estimate.getSupplyPrice();
        this.tax = estimate.getTax();
        this.totalPrice = estimate.getTotalPrice();
        this.note = estimate.getNote();
        this.prodNo = estimate.getProduct().getProdNo();
        this.propNo = estimate.getProposal().getPropNo();
    }
}
