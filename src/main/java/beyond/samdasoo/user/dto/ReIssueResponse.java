package beyond.samdasoo.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReIssueResponse {
    private String accessToken;
    private String email;
    private String nickname;
    private String profileUrl;

    @Builder
    public ReIssueResponse(String accessToken, String email, String nickname, String profileUrl){
        this.accessToken = accessToken;
        this.email = email;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
    }
}
