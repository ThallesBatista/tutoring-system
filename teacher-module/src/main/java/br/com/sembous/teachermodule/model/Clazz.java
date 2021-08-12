package br.com.sembous.teachermodule.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "classes")
public class Clazz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Teacher teacher;
	
	@ElementCollection
	@CollectionTable(name = "class_students_ids", joinColumns=@JoinColumn(name = "class_id", referencedColumnName = "id"))
	@Column(name = "student_id")
	private Set<Integer> studentsIds;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clazz", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<ClazzLearningPlan> learningPlans;
	
	
	
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
		Clazz other = (Clazz) obj;
		
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		
		return true;
	}
}
