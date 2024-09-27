package beyond.samdasoo.proposal.entity;

import beyond.samdasoo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Table(name="tb_proposal")
@Entity
@Data
public class Proposal extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propNo; //제안번호 (PK)

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="lead_No", nullable = false);
//    private Lead leadNo;

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


}
