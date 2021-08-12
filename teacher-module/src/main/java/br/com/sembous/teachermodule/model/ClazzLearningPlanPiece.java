package br.com.sembous.teachermodule.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name = "class_learning_plan_pieces")
public class ClazzLearningPlanPiece {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private ClazzLearningPlanPiece father = null;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "father", orphanRemoval = true)
	@OrderColumn(name = "`order`")
	private List<ClazzLearningPlanPiece> childLPP = new ArrayList<>();
	
	
	@Enumerated(EnumType.STRING)
	private KnowledgeType type;
	
	@Enumerated(EnumType.STRING)
	private KnowledgeCategory category;
	
	private String name;
	
	
	@Enumerated(EnumType.STRING)
	private Applicability applicability;
	
	@ElementCollection
	@CollectionTable(name = "class_lpp_applicable_students_ids", joinColumns=@JoinColumn(name = "class_lpp_id", referencedColumnName = "id"))
	@Column(name = "student_id")
	private Set<Integer> applicableStudents = new HashSet<>();
	
	
	private Double generalScore = Double.valueOf(0);
	private Integer numberOfStudentsDone = Integer.valueOf(0);
	private Integer numberOfStudentsBlocked = Integer.valueOf(0);
	
	
	
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
		ClazzLearningPlanPiece other = (ClazzLearningPlanPiece) obj;
		
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		
		return true;
	}
}
