package beyond.samdasoo.lead.Entity;

import beyond.samdasoo.admin.entity.SubProcess;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "tb_step")
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "step_no", nullable = false)
    private Long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_no", nullable = false)
    private Lead lead;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_process_no", nullable = false)
    private SubProcess subProcess;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "complete_yn", nullable = false)
    @Pattern(regexp = "^[ynYN]$", message = "complete_yn 값은 'Y' 또는 'N'이어야 합니다.")
    private String completeYn;

    @Column(name = "complete_date")
    private LocalDate completeDate;
}
