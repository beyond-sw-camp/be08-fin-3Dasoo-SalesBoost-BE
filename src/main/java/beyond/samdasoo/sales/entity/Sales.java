package beyond.samdasoo.sales.entity;

import beyond.samdasoo.common.entity.BaseEntity;
import beyond.samdasoo.contract.entity.Contract;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_sales")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sales extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_no")
    private Long salesNo;

    @Column(name = "sales_cls", nullable = false)
    private String salesCls;

    @Column(name = "sales_date", nullable = false)
    private LocalDate salesDate;

    @Column(name = "tax_cls", nullable = false)
    private String taxCls;

    @Column(name = "surtax_yn", length = 1, nullable = false)
    private String surtaxYn;

    @Column(name = "prod_cnt", nullable = false)
    private int productCount;

    @Column(name = "supply_price", nullable = false)
    private int supplyPrice;

    @Column(name = "tax", nullable = false)
    private int tax;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "exp_arrival_date")
    private LocalDate expArrivalDate;

    @Column(name = "busi_type")
    private String busiType;

    @Column(name = "busi_type_detail")
    private String busiTypeDetail;

    @Column(name = "note")
    private String note;

    @JoinColumn(name = "contract_no", nullable = false)
    @OneToOne
    private Contract contract;
}