package platform.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Candidate candidate;

	public Skill() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Skill(Long id, String name, Candidate candidate) {
		super();
		this.id = id;
		this.name = name;
		this.candidate = candidate;
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

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
		if(!candidate.getSkills().contains(this)) {
			candidate.getSkills().add(this);
		}
	}

	
	
	
}
