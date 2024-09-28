package beyond.samdasoo.contract.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_contract")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_no")
    private Long contractNo;

    @Column(name = "name")
    private String name;

    @Column(name = "cont_date")
    private LocalDate contractDate;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "tax_cls")
    private String taxCls;

    @Column(name = "surtax_yn", length = 1)
    private String surtaxYn;

    @Column(name = "prod_cnt")
    private int productCount;

    @Column(name = "supply_price")
    private int supplyPrice;

    @Column(name = "tax")
    private int tax;

    @Column(name = "price")
    private int totalPrice;

    @Column(name = "payment_terms")
    private String paymentTerms;

    @Column(name = "warrenty")
    private int warrenty;

    @Column(name = "cls")
    private String contractCls;

    @Column(name = "exp_arrival_date")
    private LocalDate expectedArrivalDate;

    @Column(name = "arrival_noti_yn", length = 1)
    private String arrivalNotificationYn;

    @Column(name = "arrival_noti_day")
    private String arrivalNotificationDay;

    @Column(name = "renewal_noti_yn", length = 1)
    private String renewalNotificationYn;

    @Column(name = "renewal_noti_day")
    private String renewalNotificationDay;

    @Column(name = "note")
    private String note;

    @Column(name = "est_no")
    private Long estimateNo;


    public Contract(String name, LocalDate contractDate, LocalDate startDate, LocalDate endDate, String taxCls, String surtaxYn,
                    int productCount, int supplyPrice, int tax, int totalPrice, String paymentTerms, int warrenty, String contractCls,
                    LocalDate expectedArrivalDate, String arrivalNotificationYn, String arrivalNotificationDay, String renewalNotificationYn,
                    String renewalNotificationDay, String note, Long estimateNo) {
        this.name = name;
        this.contractDate = contractDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.taxCls = taxCls;
        this.surtaxYn = surtaxYn;
        this.productCount = productCount;
        this.supplyPrice = supplyPrice;
        this.tax = tax;
        this.totalPrice = totalPrice;
        this.paymentTerms = paymentTerms;
        this.warrenty = warrenty;
        this.contractCls = contractCls;
        this.expectedArrivalDate = expectedArrivalDate;
        this.arrivalNotificationYn = arrivalNotificationYn;
        this.arrivalNotificationDay = arrivalNotificationDay;
        this.renewalNotificationYn = renewalNotificationYn;
        this.renewalNotificationDay = renewalNotificationDay;
        this.note = note;
        this.estimateNo = estimateNo;
    }
}
