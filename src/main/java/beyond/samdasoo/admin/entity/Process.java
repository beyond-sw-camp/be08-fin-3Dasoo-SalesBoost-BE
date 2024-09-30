package beyond.samdasoo.admin.entity;

import beyond.samdasoo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="tb_process")
public class Process  extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "process_no", nullable = false)
    private Long processNo;         // 프로세스 식별 번호

    @Column(name = "process_name", nullable = false)
    private String processName;     // 프로세스 이름

    @Column(name = "is_default", nullable = false)
    private Boolean isDefault;      // 기본 프로세스 지정여부

    @Column(name = "expected_duration")
    private Integer expectedDuration;   // 예상 소요 시간

    @Column(name = "description")
    private String description;   // 내용

    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL)
    private List<SubProcess> subProcesses;      // 하위 프로세스
}
