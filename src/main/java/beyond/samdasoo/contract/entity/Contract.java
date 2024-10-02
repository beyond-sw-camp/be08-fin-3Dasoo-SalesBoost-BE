package beyond.samdasoo.contract.entity;

import beyond.samdasoo.common.entity.BaseEntity;
import beyond.samdasoo.estimate.entity.Estimate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_contract")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contract extends BaseEntity {

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
    private Integer productCount;

    @Column(name = "supply_price")
    private Integer supplyPrice;

    @Column(name = "tax")
    private Integer tax;

    @Column(name = "price")
    private Integer totalPrice;

    @Column(name = "payment_terms")
    private String paymentTerms;

    @Column(name = "warrenty")
    private Integer warrenty;

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

    @JoinColumn(name = "est_no", nullable = false)
    @OneToOne
    private Estimate estimate;
}
