package beyond.samdasoo.act.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tb_act")
public class Act {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actNo;

//    TODO: 영업기회 엔티티 완성 후 주석 해제 예정-ENTITY
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "lead_no", nullable = false)
//    private Lead lead;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cls", nullable = false)
    @Enumerated(EnumType.STRING)
    private ActStatus cls;

    @Column(name = "purpose", nullable = false)
    private String purpose;

    @Column(name = "act_date", nullable = false)
    private LocalDate actDate;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "complete_yn")
    private String completeYn;

    @Column(name = "plan_cont")
    private String planCont;

    @Column(name = "act_cont")
    private String actCont;

}
