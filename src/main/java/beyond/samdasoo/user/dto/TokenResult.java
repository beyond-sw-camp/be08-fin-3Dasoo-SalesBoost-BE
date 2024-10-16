package beyond.samdasoo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenResult {
    private String accessToken;
    private String refreshToken;
    private String name;
    private String email;
    private UserRole role;
    private String dept;
}
