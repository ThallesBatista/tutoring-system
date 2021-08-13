package br.com.sembous.teachermoduleapi.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Clazz {

	private Integer id;
	private Teacher teacher;
	private Set<Integer> studentsIds;
	private Set<ClazzLearningPlan> learningPlans = new HashSet<>();
	private String name;
	private LocalDate createdAt;
	private Integer numberOfLearningPlans;
	private Integer numberOfStudents;
	
	public Clazz(Integer id, String name, LocalDate createdAt, Teacher teacher, Set<Integer> studentsIds, Set<ClazzLearningPlan> lps) {
		this.id = id;
		this.name = name;
		this.createdAt = createdAt;
		this.studentsIds = studentsIds;
		this.teacher = teacher;
		lps.forEach(this::addLearningPlan);
		this.numberOfLearningPlans = lps.size();
		this.numberOfStudents = studentsIds.size();
	}
	public Clazz(Integer id, String name, LocalDate createdAt, Integer numberOfLearningPlans, Integer numberOfStudents) {
		this.id = id;
		this.name = name;
		this.createdAt = createdAt;
		this.numberOfLearningPlans = numberOfLearningPlans;
		this.numberOfStudents = numberOfStudents;
	}
	public Clazz(String name) {
		this.name = name;
		this.createdAt = LocalDate.now();
	}
	
	
	public Integer getId() {
		return id;
	}
	public Optional<Teacher> getTeacher() {
		return Optional.ofNullable(teacher);
	}
	public String getName() {
		return name;
	}
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public Integer getNumberOfLearningPlans() {
		return numberOfLearningPlans;
	}
	public Integer getNumberOfStudents() {
		return numberOfStudents;
	}
	public Set<Integer> getStudentsIds() {
		return studentsIds;
	}
	public Set<ClazzLearningPlan> getLearningPlans() {
		return learningPlans;
	}
	
	
	private void addLearningPlan(ClazzLearningPlan learningPlan) {
		learningPlan.setClazz(this);
		this.learningPlans.add(learningPlan);
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
		Clazz other = (Clazz) obj;
		
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		
		return true;
	}
}
