package platform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import platform.model.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
	
	@Query("SELECT c FROM Candidate c WHERE" +
			"(:name = NULL OR c.name LIKE :name)")
	Page<Candidate> search(@Param("name")String name, Pageable pageable);

}
