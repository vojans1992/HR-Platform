package platform.web.dto;

import java.util.List;


public class CandidateDto {

	private Long id;
	private String name;
	private String date;
	private String number;
	private String mail;
	private List<SkillDto> skillsDto;
	public CandidateDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	public List<SkillDto> getSkillsDto() {
		return skillsDto;
	}

	public void setSkillsDto(List<SkillDto> skillsDto) {
		this.skillsDto = skillsDto;
	}

	
	
	
}
