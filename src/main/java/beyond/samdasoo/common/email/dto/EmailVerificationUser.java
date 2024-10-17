package beyond.samdasoo.common.email.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@AllArgsConstructor
@RedisHash(value = "isVerified", timeToLive = 600)  // 메일인증 성공여부 ttl 10분
public class EmailVerificationUser {
    @Id
    private String email;

    private Boolean isVerified;
}

