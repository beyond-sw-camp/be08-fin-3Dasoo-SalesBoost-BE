package beyond.samdasoo.admin.entity;

import beyond.samdasoo.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tb_department")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Department extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_no", nullable = false)
    private Long deptNo;        // 부서 식별 번호

    @Column(name = "dept_code", nullable = false)
    private String deptCode;    // 부서 코드

    @Column(name = "dept_name", nullable = false)
    private String deptName;    // 부서 이름

    @Column(name = "eng_name")
    private String engName;     // 부서 영문 이름

    @Column(name = "dept_head")
    private String deptHead;    // 부서장

    // 상위 부서
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Department parent;

    // 하위 부서
    @JsonManagedReference
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Department> children = new ArrayList<>();

}
