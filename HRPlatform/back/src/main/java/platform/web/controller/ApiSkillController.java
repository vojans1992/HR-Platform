package platform.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import platform.model.Skill;
import platform.service.SkillService;
import platform.support.SkillToSkillDto;
import platform.web.dto.SkillDto;


@RestController
@RequestMapping("api/skills")
public class ApiSkillController {
	
	@Autowired SkillService skillService;
	@Autowired SkillToSkillDto toDto;
	

	@GetMapping
	public ResponseEntity<List<SkillDto>> get(){
		List<Skill> skills = null;
		
		skills = skillService.all();
		
		return new ResponseEntity<>(toDto.convert(skills), HttpStatus.OK);
	}
	

	@PostMapping(consumes = "application/json")
	public ResponseEntity<SkillDto> add(@Validated @RequestBody SkillDto dto){
		Skill saved = skillService.save(dto);
		
		return new ResponseEntity<>(toDto.convert(saved),HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SkillDto> delete(@PathVariable Long id){
		Skill deleted = skillService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toDto.convert(deleted), HttpStatus.OK);
	}

}
