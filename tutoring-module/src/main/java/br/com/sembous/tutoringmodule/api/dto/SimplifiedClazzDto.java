package br.com.sembous.tutoringmodule.api.dto;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.sembous.teachermoduleapi.model.Clazz;

public class SimplifiedClazzDto {

	private Integer id;
	private String name;
	private LocalDate createdAt;
	private Integer numberOfLearningPlans;
	private Integer numberOfStudents;
	
	private SimplifiedClazzDto(Clazz clazz) {
		this.id = clazz.getId();
		this.name = clazz.getName();
		this.createdAt = clazz.getCreatedAt();
		this.numberOfLearningPlans = clazz.getNumberOfLearningPlans();
		this.numberOfStudents = clazz.getNumberOfStudents();
	}
	
	public static Set<SimplifiedClazzDto> convert(Set<Clazz> classes) {
		return classes.stream().map(SimplifiedClazzDto::new).collect(Collectors.toSet());
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
	public Integer getNumberOfLearningPlans() {
		return numberOfLearningPlans;
	}
	public Integer getNumberOfStudents() {
		return numberOfStudents;
	}
}
