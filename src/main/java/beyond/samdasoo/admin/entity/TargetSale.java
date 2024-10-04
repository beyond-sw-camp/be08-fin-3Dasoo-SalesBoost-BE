package beyond.samdasoo.admin.entity;

import beyond.samdasoo.common.entity.BaseEntity;
import beyond.samdasoo.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tb_target_sale")
public class TargetSale extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "target_sale_no", nullable = false)
    private Long targetSaleNo;      // 목표 매출 식별 번호

    @Column(name = "month", nullable = false)
    private int month;      // 월

    @Column(name = "year", nullable = false)
    private int year;       // 년도

    @Column(name = "month_target", nullable = false)
    private int monthTarget;  // 월별 목표

    @ManyToOne
    @JoinColumn(name = "product_no", nullable = false)
    private Product product;    // 제품

    @ManyToOne
    @JoinColumn(name = "user_no", nullable = false)
    private User user;    // 사원

}
