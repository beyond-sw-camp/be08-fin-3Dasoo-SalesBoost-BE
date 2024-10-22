package beyond.samdasoo.common.jwt.entity;

import beyond.samdasoo.common.utils.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.sql.Ref;
import java.util.UUID;

@Getter
@NoArgsConstructor
@RedisHash(value = "refreshToken", timeToLive = JwtUtil.refreshTokenExpireDuration)
public class RefreshToken implements Serializable {

    @Id
    private String id; // pk
    @Indexed
    private String email;
    private String token;

    public RefreshToken(String email, String token){
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.token = token;
    }

}

