package beyond.samdasoo.user.entity;

import beyond.samdasoo.common.entity.BaseEntity;
import beyond.samdasoo.user.dto.UserRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_USER")
@Entity
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,name="employee_id")
    private String employeeId; // 사번

    @Column(nullable = false)
    private String dept; //  부서 -> todo : 추후 부서 테이블과 연결

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @CreationTimestamp
    @Column(name = "join_date",updatable = false)
    private LocalDate joinDate; // 가입일


    public void RoleChangeToAdmin(){
        this.role = UserRole.ADMIN;
    }

    public void RoleChangeToUser(){
        this.role = UserRole.USER;
    }

    @Builder
    public User(String name, String email, String password,String employeeId,String dept, UserRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.employeeId = employeeId;
        this.dept = dept;
        this.role = role;
    }
}
