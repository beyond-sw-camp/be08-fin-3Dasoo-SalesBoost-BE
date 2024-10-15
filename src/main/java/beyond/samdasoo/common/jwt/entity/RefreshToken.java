package beyond.samdasoo.common.jwt.entity;

import beyond.samdasoo.common.utils.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@AllArgsConstructor
@RedisHash(value = "refreshToken", timeToLive = JwtUtil.refreshTokenExpireDuration)
public class RefreshToken {

    @Id
    private String userEmail;

    private String refreshToken;

}

