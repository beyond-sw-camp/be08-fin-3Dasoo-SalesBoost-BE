package beyond.samdasoo.admin.entity;

import beyond.samdasoo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="tb_sub_process")
public class SubProcess extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_process_no", nullable = false)
    private Long subProcessNo;      // 서브 프로세스 식별 번호

    @ManyToOne
    @JoinColumn(name = "process_no", nullable = false)
    private Process process;    // 상위 프로세스

    @Column(name = "sub_process_name", nullable = false)
    private String subProcessName;      // 서브 프로세스 이름

    @Column(name = "progress_step", nullable = false)
    private String progressStep;        // 현재 진행 단계

    @Column(name = "description")
    private String description;

    @Column(name = "success_rate")
    private Integer successRate;            // 성공 확률
    
    @Column(name = "action", nullable = true)
    private String action;              // 프로세스 변경 액션
    
    @Column(name = "expected_duration")
    private Integer expectedDuration;       // 예상 소요 시간
}
