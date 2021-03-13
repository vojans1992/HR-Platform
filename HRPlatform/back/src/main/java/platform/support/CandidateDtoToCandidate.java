package platform.support;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import platform.model.Candidate;
import platform.service.CandidateService;
import platform.web.dto.CandidateDto;
@Component
public class CandidateDtoToCandidate implements Converter<CandidateDto, Candidate> {

	@Autowired SkillDtoToSkill toSkill;
	@Autowired CandidateService candidateService;
	
	@Override
	public Candidate convert(CandidateDto source) {
		// TODO Auto-generated method stub
		Long id = source.getId();
		Candidate candidate = id == null ? new Candidate() : candidateService.one(id).get();
		if(candidate != null) {
			candidate.setId(source.getId());
			candidate.setDate(source.getDate());
			candidate.setMail(source.getMail());
			candidate.setName(source.getName());
			candidate.setNumber(source.getNumber());
			
		}
		return candidate;
	}

}
