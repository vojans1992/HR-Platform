package platform.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import platform.model.Candidate;
import platform.model.Skill;
import platform.repository.SkillRepository;
import platform.service.CandidateService;
import platform.service.SkillService;
import platform.support.SkillDtoToSkill;
import platform.web.dto.SkillDto;
@Service
public class JpaSkillService implements SkillService {
	
	@Autowired SkillRepository skillRepository;
	@Autowired SkillDtoToSkill toSkill;
	@Autowired CandidateService candidateService;
	
	
	
	@Override
	public List<Skill> all() {
		// TODO Auto-generated method stub
		return skillRepository.findAll();
	}

	

	@Override
	public Optional<Skill> one(Long id) {
		// TODO Auto-generated method stub
		return skillRepository.findById(id);
	}

	@Override
	public Skill save(SkillDto skillDto) {
		// TODO Auto-generated method stub
		Candidate candidate = candidateService.one(skillDto.getCandidateId()).get();
		Skill skill = toSkill.convert(skillDto);
		Skill saved = skillRepository.save(skill);
		candidate.getSkills().add(skill);
		return saved;
	}


	@Override
	public Skill delete(Long id) {
		// TODO Auto-generated method stub
		Optional<Skill> opt = skillRepository.findById(id);
		if(opt.isPresent()) {
			Skill skill = opt.get();
			skillRepository.deleteById(id);
			return skill;
		}
		return null;
	}



	@Override
	public List<Skill> search(String name) {
		// TODO Auto-generated method stub
		return skillRepository.search(name);
	}

}
