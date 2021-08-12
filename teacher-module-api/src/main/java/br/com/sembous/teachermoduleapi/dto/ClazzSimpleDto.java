package br.com.sembous.teachermoduleapi.dto;

import java.time.LocalDate;

import br.com.sembous.teachermoduleapi.model.Clazz;

public class ClazzSimpleDto {

	private Integer id;
	private String name;
	private Integer numberOfLearningPlans;
	private Integer numberOfStudents;
	private LocalDate createdAt;
	
	public Clazz convert() {
		return new Clazz(id, name, createdAt, numberOfLearningPlans, numberOfStudents);
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNumberOfLearningPlans(Integer numberOfLearningPlans) {
		this.numberOfLearningPlans = numberOfLearningPlans;
	}
	public void setNumberOfStudents(Integer numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}	
}
