package beyond.samdasoo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginUserReq {

    private String email;
    private String password;
}
