package platform.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import platform.model.Skill;
import platform.web.dto.SkillDto;
@Component
public class SkillToSkillDto implements Converter<Skill, SkillDto>{

	@Override
	public SkillDto convert(Skill source) {
		// TODO Auto-generated method stub
		SkillDto dto = new SkillDto();
		dto.setId(source.getId());
		dto.setName(source.getName());
		
		return dto;
	}
	
	public List<SkillDto> convert(List<Skill>skills){
		List<SkillDto> skillsDto = new ArrayList<>();
		for(Skill s : skills) {
			skillsDto.add(convert(s));
		}
		return skillsDto;
	}

}
