package platform.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import platform.model.Candidate;
import platform.model.Skill;
import platform.repository.CandidateRepository;
import platform.service.CandidateService;
import platform.service.SkillService;
import platform.support.CandidateDtoToCandidate;
import platform.web.dto.CandidateDto;

@Service
public class JpaCandidateService implements CandidateService{

	@Autowired CandidateRepository candidateRepository;
	@Autowired CandidateDtoToCandidate toCandidate;
	@Autowired SkillService skillService;
	
	@Override
	public Page<Candidate> search(String name, int pageNum) {
		// TODO Auto-generated method stub
		if(name != null) {
			name = "%" + name + "%";
		}
		return candidateRepository.search(name, PageRequest.of(pageNum, 5));
	}
	
	@Override
	public List<Candidate> search(String skill){
		List<Skill> skills = skillService.search(skill);
		
		List<Long> ids = new ArrayList<>();
		
		for(Skill s : skills) {
			ids.add(s.getCandidate().getId());
		}
		
		return candidateRepository.findAllById(ids);
	}

	@Override
	public Page<Candidate> all(int page) {
		// TODO Auto-generated method stub
		return candidateRepository.findAll(PageRequest.of(page, 5));
	}

	@Override
	public Optional<Candidate> one(Long id) {
		// TODO Auto-generated method stub
		return candidateRepository.findById(id);
	}

	@Override
	public Candidate save(CandidateDto candidateDto) {
		// TODO Auto-generated method stub
		Candidate candidate = toCandidate.convert(candidateDto);
		Candidate saved = candidateRepository.save(candidate);
		return saved;
	}

	@Override
	public Candidate delete(Long id) {
		// TODO Auto-generated method stub
		Optional<Candidate> opt = candidateRepository.findById(id);
		if(opt.isPresent()) {
			Candidate candidate = opt.get();
			candidateRepository.deleteById(id);
			return candidate;
		}
		return null;
	}

}
