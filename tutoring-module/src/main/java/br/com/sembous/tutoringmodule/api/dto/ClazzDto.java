package br.com.sembous.tutoringmodule.api.dto;

import java.time.LocalDate;
import java.util.Set;

import br.com.sembous.teachermoduleapi.model.Clazz;

public class ClazzDto {

	private Integer id;
	private String name;
	private LocalDate createdAt;
//	private TeacherDto teacher;
//	private Set<Integer> studentsIds;
	private Set<ClazzLearningPlanSimpleDto> learningPlans;
	private Integer numberOfLearningPlans;
	private Integer numberOfStudents;
	
	
	public ClazzDto(Clazz clazz) {
		this.id = clazz.getId();
		this.name = clazz.getName();
		this.createdAt = clazz.getCreatedAt();
		this.numberOfLearningPlans = clazz.getNumberOfLearningPlans();
		this.numberOfStudents = clazz.getNumberOfStudents();
		this.learningPlans = ClazzLearningPlanSimpleDto.convertSet(clazz.getLearningPlans());
	}


	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public Set<ClazzLearningPlanSimpleDto> getLearningPlans() {
		return learningPlans;
	}
	public Integer getNumberOfLearningPlans() {
		return numberOfLearningPlans;
	}
	public Integer getNumberOfStudents() {
		return numberOfStudents;
	}
}
