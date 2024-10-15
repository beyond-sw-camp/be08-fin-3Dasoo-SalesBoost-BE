package beyond.samdasoo.common.jwt;

import beyond.samdasoo.common.response.BaseResponseStatus;
import beyond.samdasoo.common.utils.JwtUtil;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Component
public class JwtTokenProvider { // Jwt 생성

    private final SecretKey secretKey;
    private final long accessTokenValidMs;
    private final long refreshTokenValidMs;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret, @Value("${jwt.token-valid-in-sec}") long tokenValidInSec){
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
        this.accessTokenValidMs = tokenValidInSec; // 1
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
       //     now +=this.accessTokenValidMs;
            now += JwtUtil.accessTokenExpireDuration;
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
       //     now +=this.refreshTokenValidMs;
            now+=JwtUtil.refreshTokenExpireDuration;
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

//    public boolean validateToken(String token){
//        try {
//            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
//            return true;
//        } catch(SecurityException | MalformedJwtException e){
//            log.info("잘못된 JWT 서명입니다.");
//        } catch(UnsupportedJwtException e){
//            log.info("지원되지 않는 JWT 토큰입니다.");
//        } catch(IllegalArgumentException e){
//            log.info("JWT 토큰이 잘못되었습니다.");
//        }
//        return false;
//    }
public boolean validateToken(String token, HttpServletRequest request){
    try{
        Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
        return true;
    } catch (SecurityException | MalformedJwtException e){
        log.info("invalid JWT Token", e);
        request.setAttribute("exception", BaseResponseStatus.JWT_INVALID_ACCESS_TOKEN);
    } catch (ExpiredJwtException e){
        log.info("Expired JWT Token");
        request.setAttribute("exception",BaseResponseStatus.JWT_EXPIRED_ACCESS_TOKEN);
    } catch (UnsupportedJwtException e){
        log.info("Unsupported JWT Token");
        request.setAttribute("exception",BaseResponseStatus.JWT_INVALID_ACCESS_TOKEN);
    } catch (IllegalArgumentException e){
        log.info("JWT claims string is empty");
        request.setAttribute("exception",BaseResponseStatus.JWT_INVALID_ACCESS_TOKEN);
    } catch (JwtException e){
        log.info("General JwtException");
        request.setAttribute("exception",BaseResponseStatus.JWT_INVALID_ACCESS_TOKEN);
    }
    return false;
}

    public boolean validateRefreshToken(String token, HttpServletRequest request){
        try{
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;
        } catch (SecurityException | MalformedJwtException e){
            log.info("invalid JWT Token", e);
            request.setAttribute("exception", BaseResponseStatus.JWT_INVALID_REFRESH_TOKEN);
        } catch (ExpiredJwtException e){
            log.info("Expired JWT Token");
            request.setAttribute("exception",BaseResponseStatus.JWT_EXPIRED_ACCESS_TOKEN);
        } catch (UnsupportedJwtException e){
            log.info("Unsupported JWT Token");
            request.setAttribute("exception",BaseResponseStatus.JWT_INVALID_REFRESH_TOKEN);
        } catch (IllegalArgumentException e){
            log.info("JWT claims string is empty");
            request.setAttribute("exception",BaseResponseStatus.JWT_INVALID_REFRESH_TOKEN);
        } catch (JwtException e){
            log.info("General JwtException");
            request.setAttribute("exception",BaseResponseStatus.JWT_INVALID_REFRESH_TOKEN);
        }
        return false;
    }

    public boolean isExpiredToken(String token){
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
        }catch (ExpiredJwtException e){
            return true;
        }
        return false;
    }


}
