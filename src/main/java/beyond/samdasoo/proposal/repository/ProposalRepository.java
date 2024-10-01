package beyond.samdasoo.proposal.repository;

import beyond.samdasoo.proposal.entity.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalRepository extends JpaRepository<Proposal, Long>{
    boolean existsById(Long propNo);
}
