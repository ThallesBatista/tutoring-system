package br.com.sembous.teachermoduleapi.model;

import java.time.LocalDate;
import java.util.Optional;

public class Clazz {

	private Integer id;
	private Teacher teacher;
//	private Set<Integer> studentsIds;
//	private Set<ClazzLearningPlan> learningPlans;
	private String name;
	private LocalDate createdAt;
	private Integer numberOfLearningPlans;
	private Integer numberOfStudents;
	
	
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
}
