package beyond.samdasoo.estimate.entity;

import beyond.samdasoo.admin.entity.Product;
import beyond.samdasoo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_est_prod")
public class EstProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "est_prod_no", nullable = false)
    private Long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "est_no", nullable = false)
    private Estimate estimate;  // 견적번호 (FK)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_no", nullable = false)
    private Product product;  // 제품번호 (FK)

    @Column(name = "unit_amt", nullable = false)
    private int unitAmt;  // 단가

    @Column(name = "discount", nullable = false)
    private int discount;  // 할인율

    @Column(name = "unit_prop_amt", nullable = false)
    private int unitPropAmt;  // 판매단가

    @Column(name = "qty", nullable = false)
    private int qty;  // 수량

    @Column(name = "supply_price", nullable = false)
    private int supplyPrice;  // 공급가액

    @Column(name = "tax", nullable = false)
    private int tax;  // 세액

    @Column(name = "total_amt", nullable = false)
    private int totalAmt;  // 합계금액

}
