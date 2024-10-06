package beyond.samdasoo.user.dto;

import beyond.samdasoo.user.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JoinUserReq {

    @NotNull(message = "name cannot be null")
    private String name;

    @NotNull(message = "email cannot be null")
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 4,message = "Password must be greater than 4 characters")
    private String password;

    @NotNull(message = "Dept cannot be null")
    private String dept;

    public User toUser(String encryptedPassword, String employeeId) {
        return User
                .builder()
                .name(this.name)
                .email(this.email)
                .password(encryptedPassword)
                .employeeId(employeeId)
                .dept(this.dept)
                .role(UserRole.USER)
                .build();
    }
}
