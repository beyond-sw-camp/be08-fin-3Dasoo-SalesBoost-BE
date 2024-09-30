package beyond.samdasoo.contract.dto;

import beyond.samdasoo.estimate.entity.Estimate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ContractRequestDto {
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
    private Estimate estimate;

}
