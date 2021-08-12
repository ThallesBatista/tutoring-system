package br.com.sembous.teachermodule.dto;

import java.time.LocalDate;

import br.com.sembous.teachermodule.model.Clazz;

public class ClazzSimpleDto {

	private Integer id;
	private String name;
	private Integer numberOfLearningPlans;
	private Integer numberOfStudents;
	private LocalDate createdAt;
	
	public ClazzSimpleDto(Clazz clazz) {
		this.id = clazz.getId();
		this.name = clazz.getName();
		this.numberOfLearningPlans = clazz.numberOfLearningPlans();
		this.numberOfStudents = clazz.numberOfStudents();
		this.createdAt = clazz.getCreatedAt();
	}

	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Integer getNumberOfLearningPlans() {
		return numberOfLearningPlans;
	}
	public Integer getNumberOfStudents() {
		return numberOfStudents;
	}
	public LocalDate getCreatedAt() {
		return createdAt;
	}
}
