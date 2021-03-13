package platform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import platform.model.Candidate;
import platform.web.dto.CandidateDto;

public interface CandidateService {

	Page<Candidate> search(String name, int pageNum);
	List<Candidate> search(String skill);
	Page<Candidate> all(int page);
	Optional<Candidate> one(Long id);
	Candidate save(CandidateDto candidateDto);
	Candidate delete(Long id);
}
