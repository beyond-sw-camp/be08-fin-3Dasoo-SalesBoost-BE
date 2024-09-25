package beyond.samdasoo.common.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;

@Component
public class JwtUtil { // Jwt 생성

    private final SecretKey secretKey;
    private final long accessTokenValidMs;
    private final long refreshTokenValidMs;

    public JwtUtil(@Value("${jwt.secret") String secret, @Value("${jwt.token-valid-in-sec}") long tokenValidInSec){
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
        this.accessTokenValidMs = tokenValidInSec*1000/24; // 1
        this.refreshTokenValidMs = tokenValidInSec*1000/60;

    }

    // 검증 진행 메서드
    public String getEmail(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("email", String.class);
    }

    public String getRole(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    public Boolean isExpired(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public Date getExpiredDate(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration();
    }


    public String createToken(String email,String role,String type){
        long now = (new Date()).getTime();
        if(Objects.equals(type, "ACCESS")){
            now +=this.accessTokenValidMs;
            Date validity = new Date(now);
            return Jwts.builder()
                    .claim("email",email)
                    .claim("role",role)
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(validity)
                    .signWith(secretKey)
                    .compact();

        }
        if(Objects.equals(type, "REFRESH")){
            now +=this.refreshTokenValidMs;
            Date validity = new Date(now);
            return Jwts.builder()
                    .claim("email",email)
                    .claim("role",role)
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(validity)
                    .signWith(secretKey)
                    .compact();
        }
        return null;
    }
}
