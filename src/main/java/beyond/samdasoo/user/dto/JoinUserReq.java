package beyond.samdasoo.user.dto;

import beyond.samdasoo.admin.entity.Department;
import beyond.samdasoo.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JoinUserReq {

    @NotNull
    @Schema(description = "이름", defaultValue = "김은경")
    private String name;

    @NotNull
    @Schema(description = "이메일", defaultValue = "test@gmail.com")
    private String email;

    @NotNull(message = "Password cannot be null")
    @Schema(description = "비밀번호", defaultValue = "1234")
    private String password;

    @NotNull(message = "Dept cannot be null")
    @Schema(description = "부서코드", defaultValue = "001")
    private String deptCode;   // 부서코드

    public User toUser(String encryptedPassword, String employeeId, Department department) {
        return User
                .builder()
                .name(this.name)
                .email(this.email)
                .password(encryptedPassword)
                .employeeId(employeeId)
                .department(department)
                .role(UserRole.USER)
                .build();
    }
}
