package beyond.samdasoo.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoginUserReq {

    @Schema(description = "로그인 타입(email,employeeId)",defaultValue = "email")
    private String loginType;

    @Schema(description = "이메일", defaultValue = "test@gmail.com")
    private String email;

    @Schema(description = "사번", defaultValue = "20241007001")
    private String employeeId;

    @Schema(description = "비밀번호", defaultValue = "1234")
    private String password;
}
