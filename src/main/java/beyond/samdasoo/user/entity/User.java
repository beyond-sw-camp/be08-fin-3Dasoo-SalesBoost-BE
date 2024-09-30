package beyond.samdasoo.user.entity;

import beyond.samdasoo.common.entity.BaseEntity;
import beyond.samdasoo.user.dto.UserRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_NO")
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;


    public void RoleChangeToAdmin(){
        this.role = UserRole.ADMIN;
    }

    public void RoleChangeToUser(){
        this.role = UserRole.USER;
    }

    @Builder
    public User(String name, String email, String password, UserRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
