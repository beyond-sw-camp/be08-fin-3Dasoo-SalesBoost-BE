package beyond.samdasoo.member.repository;

import beyond.samdasoo.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
