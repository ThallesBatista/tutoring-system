package br.com.sembous.teachermodule.dto;

import java.time.LocalDate;
import java.util.Set;

import br.com.sembous.teachermodule.controller.TeacherInformations;
import br.com.sembous.teachermodule.model.Clazz;

public class ClazzDto {

	private Integer id;
	private String name;
	private LocalDate createdAt;
	private TeacherDto teacher;
	private Set<Integer> studentsIds;
	private Set<ClazzLearningPlanSimpleDto> learningPlans;
	
	public ClazzDto(Clazz clazz) {
		this.id = clazz.getId();
		this.name = clazz.getName();
		this.createdAt = clazz.getCreatedAt();
		this.teacher = new TeacherDto(clazz.getTeacher(), TeacherInformations.PERSONAL_INFORMATIONS);
		this.studentsIds = clazz.getStudentsIds();
		this.learningPlans = ClazzLearningPlanSimpleDto.convert(clazz.getLearningPlans());
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
	public TeacherDto getTeacher() {
		return teacher;
	}
	public Set<Integer> getStudentsIds() {
		return studentsIds;
	}
	public Set<ClazzLearningPlanSimpleDto> getLearningPlans() {
		return learningPlans;
	}
}
