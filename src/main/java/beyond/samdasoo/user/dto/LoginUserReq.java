package beyond.samdasoo.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginUserReq {

    @Schema(description = "이메일", defaultValue = "test@gmail.com")
    private String email;

    @Schema(description = "비밀번호", defaultValue = "1234")
    private String password;
}
