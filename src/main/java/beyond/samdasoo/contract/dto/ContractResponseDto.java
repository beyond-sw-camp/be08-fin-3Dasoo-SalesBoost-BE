package beyond.samdasoo.contract.dto;

import beyond.samdasoo.contract.entity.Contract;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ContractResponseDto {
    private Long contractNo;
    private String name;
    private LocalDate contractDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private String taxCls;
    private String surtaxYn;
    private Integer productCount;
    private Integer supplyPrice;
    private Integer tax;
    private Integer totalPrice;
    private String paymentTerms;
    private Integer warrenty;
    private String contractCls;
    private LocalDate expectedArrivalDate;
    private String arrivalNotificationYn;
    private String arrivalNotificationDay;
    private String renewalNotificationYn;
    private String renewalNotificationDay;
    private String note;
    private Long estimateNo;

    public ContractResponseDto(Contract contract) {
        this.contractNo = contract.getContractNo();
        this.name = contract.getName();
        this.contractDate = contract.getContractDate();
        this.startDate = contract.getStartDate();
        this.endDate = contract.getEndDate();
        this.taxCls = contract.getTaxCls();
        this.surtaxYn = contract.getSurtaxYn();
        this.productCount = contract.getProductCount();
        this.supplyPrice = contract.getSupplyPrice();
        this.tax = contract.getTax();
        this.totalPrice = contract.getTotalPrice();
        this.paymentTerms = contract.getPaymentTerms();
        this.warrenty = contract.getWarrenty();
        this.contractCls = contract.getContractCls();
        this.expectedArrivalDate = contract.getExpectedArrivalDate();
        this.arrivalNotificationYn = contract.getArrivalNotificationYn();
        this.arrivalNotificationDay = contract.getArrivalNotificationDay();
        this.renewalNotificationYn = contract.getRenewalNotificationYn();
        this.renewalNotificationDay = contract.getRenewalNotificationDay();
        this.note = contract.getNote();
        this.estimateNo = contract.getEstimate().getEstNo();
    }
}
