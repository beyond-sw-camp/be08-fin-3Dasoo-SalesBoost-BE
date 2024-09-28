package beyond.samdasoo.contract.dto;

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
    private int productCount;
    private int supplyPrice;
    private int tax;
    private int totalPrice;
    private String paymentTerms;
    private int warrenty;
    private String contractCls;
    private LocalDate expectedArrivalDate;
    private String arrivalNotificationYn;
    private String arrivalNotificationDay;
    private String renewalNotificationYn;
    private String renewalNotificationDay;
    private String note;
    private Long estimateNo;

}
