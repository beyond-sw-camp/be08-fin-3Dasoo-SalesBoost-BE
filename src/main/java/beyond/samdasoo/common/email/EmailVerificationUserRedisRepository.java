package beyond.samdasoo.common.email;

import beyond.samdasoo.common.email.dto.EmailVerificationUser;
import org.springframework.data.repository.CrudRepository;

public interface EmailVerificationUserRedisRepository extends CrudRepository<EmailVerificationUser, String> {


}
