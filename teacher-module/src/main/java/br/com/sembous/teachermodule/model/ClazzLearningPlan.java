package br.com.sembous.teachermodule.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "class_learning_plans")
public class ClazzLearningPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "class_id")
	private Clazz clazz;
	
	@OneToOne(orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
	private ClazzLearningPlanPiece learningPlanGraph;
	
	@ElementCollection
	@CollectionTable(name = "class_learning_plan_students_ids", joinColumns=@JoinColumn(name = "class_learning_plan_id", referencedColumnName = "id"))
	@Column(name = "student_id")
	private Set<Integer> studentsIds;
	
	
	private Double generalScore = Double.valueOf(0);
//	private Integer numberOfStudentsDoing = Integer.valueOf(0);
	private Integer numberOfStudentsDone = Integer.valueOf(0);
	private Integer numberOfStudentsBlocked = Integer.valueOf(0);
	
	@Column(name = "expert_module_id")
	private Integer expertModuleId;
	private String name;
	
	public ClazzLearningPlan(ClazzLearningPlanPiece clazzLPP, Clazz clazz) {
		this.clazz = clazz;
		this.learningPlanGraph = clazzLPP;
		this.expertModuleId = clazzLPP.getExpertModuleId();
		this.name = clazzLPP.getName();
	}
	
	public Integer numberOfStudentsDoing() {
		return this.studentsIds.size();
	}
	
	public Integer getId() {
		return id;
	}
	public Double getGeneralScore() {
		return generalScore;
	}
	public Integer getNumberOfStudentsDone() {
		return numberOfStudentsDone;
	}
	public Integer getNumberOfStudentsBlocked() {
		return numberOfStudentsBlocked;
	}
	public Integer getExpertModuleId() {
		return expertModuleId;
	}
	public String getName() {
		return name;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClazzLearningPlan other = (ClazzLearningPlan) obj;
		
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		
		return true;
	}
}
