package beyond.samdasoo.sales.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class SalesRequestDto {
    private String salesCls;
    private LocalDate salesDate;
    private String taxCls;
    private String surtaxYn;
    private int productCount;
    private int supplyPrice;
    private int tax;
    private int price;
    private LocalDate expArrivalDate;
    private String busiType;
    private String busiTypeDetail;
    private String note;
    private Long contractNo;


}
