package platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import platform.model.Skill;
@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>{
	
	@Query("SELECT s FROM Skill s WHERE" +
			"(:name = NULL OR s.name LIKE :name)")
	List<Skill> search(@Param("name") String name);

}
