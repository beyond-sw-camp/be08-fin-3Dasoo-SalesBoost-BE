package beyond.samdasoo.todo.entity;

import beyond.samdasoo.common.entity.BaseEntity;
import beyond.samdasoo.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name="tb_todo")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class Todo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable=false)
    private Member memberNo;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "todo_cls", nullable = false)
    private String todoCls;

    @Column(name = "priority", nullable = false)
    private String priority;

    @Column(name = "due_date")
    private Date dueDate;

//    TODO: 알람기능 필요 시 추가하도록
//    @Column(name = "noti_yn", length = 1)
//    private String notiYn;
//
//    @Column(name = "noti_day")
//    private String notiDay;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TodoStatus status;

    @Column(name = "private_yn", length = 1)
    private String privateYn;

    @Column(name = "content")
    private String content;

}
