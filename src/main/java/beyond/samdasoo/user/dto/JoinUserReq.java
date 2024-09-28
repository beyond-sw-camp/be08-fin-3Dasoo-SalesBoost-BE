package beyond.samdasoo.user.dto;

import beyond.samdasoo.user.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JoinUserReq {

    @NotNull(message = "email cannot be null")
    private String username;

    @NotNull(message = "email cannot be null")
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 4,message = "Password must be greater than 4 characters")
    private String password;

    public User toUser(String encryptedPassword) {
        return User
                .builder()
                .name(this.username)
                .email(this.email)
                .password(encryptedPassword)
                .role(UserRole.USER)
                .build();
    }
}
