package beyond.samdasoo.calendar.entity;

import beyond.samdasoo.act.entity.Act;
import beyond.samdasoo.plan.entity.Plan;
import beyond.samdasoo.todo.entity.Todo;
import beyond.samdasoo.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tb_calendar")
public class Calendar {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long no;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="user_no", nullable=false)
        private User user;

        @OneToMany(mappedBy = "calendar")
        private List<Plan> plans;

        @OneToMany(mappedBy = "calendar")
        private List<Todo> todos;

        @OneToMany(mappedBy = "calendar")
        private List<Act> acts;
}

