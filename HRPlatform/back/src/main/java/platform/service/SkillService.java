package platform.service;

import java.util.List;
import java.util.Optional;


import platform.model.Skill;
import platform.web.dto.SkillDto;

public interface SkillService {

	List<Skill> search(String name);
	List<Skill> all();
	Optional<Skill> one(Long id);
	Skill save(SkillDto skillDto);
	Skill delete(Long id);
}
