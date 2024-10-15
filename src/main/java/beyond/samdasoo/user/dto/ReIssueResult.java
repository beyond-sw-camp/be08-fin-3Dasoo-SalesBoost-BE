package beyond.samdasoo.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ReIssueResult {

    private String accessToken;
    private String refreshToken;

    private String email;

    private String nickname;

    private String profileUrl;

    @Builder
    public ReIssueResult(String accessToken, String refreshToken, String email, String nickname, String profileUrl){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.email = email;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
    }

    public ReIssueResponse toReIssueResponse(){
        ReIssueResponse reIssueResponse = ReIssueResponse.builder()
                .accessToken(this.accessToken)
                .email(this.email)
                .nickname(this.nickname)
                .profileUrl(this.profileUrl)
                .build();
        return reIssueResponse;
    }
}
