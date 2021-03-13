package platform.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import platform.model.Candidate;
import platform.service.CandidateService;
import platform.support.CandidateToCandidateDto;
import platform.web.dto.CandidateDto;

@RestController
@RequestMapping("api/candidates")
public class ApiCandidateController {
	
	@Autowired CandidateService candidateService;
	@Autowired CandidateToCandidateDto toDto;
	
	@GetMapping
	public ResponseEntity<List<CandidateDto>> get(@RequestParam(value="candidateName", required = false) String candidateName,
			@RequestParam(value="skillName", required = false) String skillName,
			@RequestParam(value="pageNum", defaultValue="0")int pageNum){
		Page<Candidate> page = null;
		List<Candidate> list = null;
		
		if(candidateName != null) {
			page = candidateService.search(candidateName, pageNum);
		}else if(skillName != null) {
			list = candidateService.search(skillName);
		}else {
			page = candidateService.all(pageNum);
		}
		
		HttpHeaders headers = new HttpHeaders();
		if(page != null) {
			headers.add("Total-Pages", Integer.toString(page.getTotalPages()));
		}

		if(list != null) {
			return new ResponseEntity<>(toDto.convert(list), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(toDto.convert(page.getContent()), HttpStatus.OK);
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<CandidateDto> add(@Validated @RequestBody CandidateDto dto){
		Candidate saved = candidateService.save(dto);

		return new ResponseEntity<>(toDto.convert(saved), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CandidateDto> delete(@PathVariable Long id){
		Candidate deleted = candidateService.delete(id);
		
		if(deleted == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toDto.convert(deleted), HttpStatus.OK);
	}

}
