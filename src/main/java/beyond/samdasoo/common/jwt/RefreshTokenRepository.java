package beyond.samdasoo.common.jwt;


import beyond.samdasoo.common.jwt.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {

    Optional<RefreshToken> findByEmailAndToken(String email,String token);

    List<RefreshToken> findByEmail(String email);
}
