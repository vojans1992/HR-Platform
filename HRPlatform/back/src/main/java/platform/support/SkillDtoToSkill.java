package platform.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import platform.model.Candidate;
import platform.model.Skill;
import platform.service.CandidateService;
import platform.service.SkillService;
import platform.web.dto.SkillDto;

@Component
public class SkillDtoToSkill implements Converter<SkillDto,Skill> {

	@Autowired SkillService skillService;
	@Autowired CandidateService candidateService;
	
	@Override
	public Skill convert(SkillDto source) {
		// TODO Auto-generated method stub
		Candidate candidate = null;
		if(source.getCandidateId() != null) {
			candidate = candidateService.one(source.getCandidateId()).get();
		}
		if(candidate != null) {
			Long id = source.getId();
			Skill skill = id == null ? new Skill() : skillService.one(id).get();
			if(skill != null) {
				skill.setId(source.getId());
				skill.setName(source.getName());
				skill.setCandidate(candidate);
				
			}
			
			return skill;
		}else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}
	
	public List<Skill> convert(List<SkillDto> list){
		List<Skill> skills = new ArrayList<>();
		for(SkillDto s : list) {
			skills.add(convert(s));
		}
		return skills;
	}

}
