package br.com.sembous.teachermoduleapi.dto;

import java.time.LocalDate;
import java.util.Set;

import br.com.sembous.teachermoduleapi.model.Clazz;

public class ClazzDto {

	private Integer id;
	private String name;
	private LocalDate createdAt;
	private TeacherDto teacher;
	private Set<Integer> studentsIds;
	private Set<ClazzLearningPlanSimpleDto> learningPlans;
	
	public Clazz convert() {
		return new Clazz(id, name, createdAt, teacher.convert(), studentsIds, ClazzLearningPlanSimpleDto.convertSet(learningPlans));
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	public void setTeacher(TeacherDto teacher) {
		this.teacher = teacher;
	}
	public void setStudentsIds(Set<Integer> studentsIds) {
		this.studentsIds = studentsIds;
	}
	public void setLearningPlans(Set<ClazzLearningPlanSimpleDto> learningPlans) {
		this.learningPlans = learningPlans;
	}
}
