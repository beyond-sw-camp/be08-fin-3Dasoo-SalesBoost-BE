package beyond.samdasoo.common.jwt;


import beyond.samdasoo.common.jwt.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
