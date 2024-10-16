package beyond.samdasoo.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@EnableRedisRepositories
@RequiredArgsConstructor
@Configuration
public class RedisConfig {

    // Redis 서버와의 연결 정보를 저장하는 객체
    private final RedisProperties redisProperties;

    // RedisProperties 로 yml 에 저장한 Host, Port 연결
    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        return new LettuceConnectionFactory(redisProperties.getHost(),redisProperties.getPort());
    }

    // Serializer 설정으로 redis cli 를 통해 직접 데이터 조회
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory conn){
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(conn);

        // 일반적인 key:value
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());

        // Hash 사용
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());

        // 기타
        template.setDefaultSerializer(new StringRedisSerializer());

        return template;
    }
}
