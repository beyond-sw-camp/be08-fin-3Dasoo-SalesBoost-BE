package beyond.samdasoo.admin.entity;

import beyond.samdasoo.common.entity.BaseEntity;
import beyond.samdasoo.estimate.entity.EstProduct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tb_product")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prodNo;        // 제품 번호

    @Column(name = "prod_code", nullable = false)
    private String prodCode;        // 코드

    @Column(name = "name", nullable = false)
    private String name;            // 제품명

    @Column(name = "eng_name")
    private String engName;         // 영문 제품명

    @Column(name = "abbr_name")
    private String abbrName;        // 제품 약명

    @Column(name = "upp_group")
    private String uppGroup;        // 상위 제품 그룹

    @Column(name = "release_date")
    private LocalDate releaseDate;       // 출시일

    @Column(name = "dept")
    private String dept;            // 부서

    @Column(nullable = false)
    private int quantity;           // 포장 수량

    @Column(name = "unit")
    private String unit;            // 포장 단위

    @Column(name = "field")
    private String field;           // 규격

    @Column(name = "supply_price", nullable = false)
    private int supplyPrice;        // 원가

    @Column(name = "tax_rate")
    private Integer taxRate;            // 세율

    @Column(nullable = false)
    private int price;              // 가격

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<EstProduct> estProducts;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<TargetSale> targetSales;      // 목표 매출
}
