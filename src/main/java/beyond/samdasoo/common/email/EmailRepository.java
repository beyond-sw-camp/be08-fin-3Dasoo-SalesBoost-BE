package beyond.samdasoo.common.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Repository
public class EmailRepository {

    private final RedisTemplate<String,String> redisTemplate;
    private final int EMAIL_TTL = 60 * 5; // 유효 시간 5분
    private final int EMAIL_COUNT_LIMIT = 3; // 시도 횟수 3

    public void saveEmailCode(String email, String number){
        redisTemplate.opsForValue().set(email,number);
        redisTemplate.expire(email,EMAIL_TTL, TimeUnit.SECONDS); // ttl 설정
    }

    public void deleteEmailCode(String email){
        redisTemplate.delete(email);
    }

    public boolean checkEmailCertificationNumber(String email, String number){
        String getNumber = redisTemplate.opsForValue().get(email);
        if(getNumber==null)
            return false;
        if(!getNumber.equals(number))
            return false;
        return true;
    }

}
