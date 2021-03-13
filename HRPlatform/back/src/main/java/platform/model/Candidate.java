package platform.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String date;
	
	@Column
	private String number;
	
	@Column
	private String mail;
	
	@OneToMany(mappedBy="candidate", cascade=CascadeType.ALL, fetch=FetchType.LAZY )
	private List<Skill> skills = new ArrayList<>();

	public Candidate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Candidate(Long id, String name, String date, String number, String mail, List<Skill> skills) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.number = number;
		this.mail = mail;
		this.skills = skills;
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

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	
	public void addSkill(Skill skill) {
		if(skill.getCandidate() != this) {
			skill.setCandidate(this);
		}
		skills.add(skill);
	}
	
}
