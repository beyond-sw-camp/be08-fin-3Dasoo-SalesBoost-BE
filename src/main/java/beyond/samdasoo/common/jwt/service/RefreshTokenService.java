package beyond.samdasoo.common.jwt.service;

import beyond.samdasoo.common.jwt.RefreshTokenRepository;
import beyond.samdasoo.common.jwt.entity.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {

    private final RedisTemplate<String,String> redisTemplate;
    private final RefreshTokenRepository refreshTokenRepository;


//    public void saveToken(String email,String token){
//        ListOperations<String,String> listOps = redisTemplate.opsForList();
//        listOps.rightPush(email,token);
//    }

        public void saveToken(String email,String token){
            RefreshToken refreshToken = new RefreshToken(email,token);
            refreshTokenRepository.save(refreshToken);
        }

    public void deleteByEmailAndToken(String email, String token){
        List<RefreshToken> tokens = refreshTokenRepository.findByEmail(email);
        tokens.stream()
                .filter(t ->t.getToken().equals(token))
                .forEach(t-> refreshTokenRepository.deleteById(t.getId()));
    }

    public boolean existsByEmailAndToken(String email, String token) {
        List<RefreshToken> tokens = refreshTokenRepository.findByEmail(email);
        // 해당 이메일에 대해 토큰이 존재하는지 여부 확인
        return tokens.stream()
                .anyMatch(rt -> rt.getToken().equals(token));
    }

}
