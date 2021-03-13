package platform.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import platform.model.Candidate;
import platform.service.CandidateService;
import platform.service.SkillService;
import platform.web.dto.CandidateDto;
@Component
public class CandidateToCandidateDto implements Converter<Candidate, CandidateDto> {

	@Autowired SkillToSkillDto toSkillDto;
	@Autowired SkillService skillService;
	@Autowired CandidateService candidateService;
	
	@Override
	public CandidateDto convert(Candidate source) {
		// TODO Auto-generated method stub
		CandidateDto dto = new CandidateDto();
		dto.setId(source.getId());
		dto.setName(source.getName());
		dto.setDate(source.getDate());
		dto.setMail(source.getMail());
		dto.setNumber(source.getNumber());
		dto.setSkillsDto(toSkillDto.convert(source.getSkills()));
		
		return dto;
	}
	
	public List<CandidateDto> convert(List<Candidate> candidates){
		List<CandidateDto> dto = new ArrayList<>();
		for(Candidate c : candidates) {
			dto.add(convert(c));
		}
		return dto;
	}

}
