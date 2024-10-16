package beyond.samdasoo.lead.Entity;

import beyond.samdasoo.common.entity.BaseEntity;
import beyond.samdasoo.customer.entity.Customer;
import beyond.samdasoo.proposal.entity.Proposal;
import beyond.samdasoo.lead.Entity.LeadStatus;
import beyond.samdasoo.lead.Entity.AwarePath;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "tb_lead")
public class Lead extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lead_no", nullable = false)
    private Long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_no", nullable = false)
    private Customer customer;

    @Column(name = "name", nullable = false)
    private String name;    // 이름

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private LeadStatus status;  // 진행상태

    @Column(name = "exp_sales")
    private int expSales;   // 예상 매출

    @Column(name = "exp_margin")
    private int expMargin;  // 예상 이익률

    @Column(name = "exp_profit")
    private int expProfit;  // 예상 이익금액

    @Column(name = "process", nullable = false)
    private int process;    // 프로세스

    @Column(name = "sub_process", nullable = false)
    private int subProcess; // 단계

    @Column(name = "sucess_per")
    private int sucessPer;  // 성공확률

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "aware_path")
    @Enumerated(EnumType.STRING)
    private AwarePath awarePath;

    @Column(name = "note")
    private String note;

    @OneToMany(mappedBy = "lead", cascade = CascadeType.ALL)
    private List<Proposal> proposals;
}
