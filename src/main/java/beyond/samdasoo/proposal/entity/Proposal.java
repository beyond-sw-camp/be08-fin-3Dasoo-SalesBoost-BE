package beyond.samdasoo.proposal.entity;

import beyond.samdasoo.common.entity.BaseEntity;
import beyond.samdasoo.estimate.entity.Estimate;
import beyond.samdasoo.lead.Entity.Lead;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Table(name = "tb_proposal")
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Proposal extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propNo; //제안번호 (PK)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_no", nullable = false)
    private Lead lead;

    @Column(name = "name", nullable = false)
    private String name;  // 제안명

    @Column(name = "cont", length = 2000)
    private String cont;  // 내용

    @Column(name = "req_date")
    private LocalDate reqDate;  // 요청일

    @Column(name = "start_date")
    private LocalDate startDate;  // 제안시작일

    @Column(name = "end_date")
    private LocalDate endDate;  // 제안종료일

    @Column(name = "submit_date")
    private LocalDate submitDate;  // 제출일

    @Column(name = "pr_date")
    private LocalDate prDate;  // 발표일

    @Column(name = "note")
    private String note;  // 비고

    @OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL)
    private List<Estimate> estimates;
}
