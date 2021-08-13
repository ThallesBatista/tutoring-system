package br.com.sembous.teachermodule.dto;

import java.util.Set;
import java.util.stream.Collectors;

import br.com.sembous.teachermodule.model.ClazzLearningPlan;

public class ClazzLearningPlanSimpleDto {

	private Integer id;
	private Double generalScore;
	private Integer numberOfStudentsDoing;
	private Integer numberOfStudentsDone;
	private Integer numberOfStudentsBlocked;
	private Integer expertModuleId;
	private String name;
	
	public ClazzLearningPlanSimpleDto(ClazzLearningPlan learningPlan) {
		this.id = learningPlan.getId();
		this.generalScore = learningPlan.getGeneralScore();
		this.numberOfStudentsDoing = learningPlan.numberOfStudentsDoing();
		this.numberOfStudentsDone = learningPlan.getNumberOfStudentsDone();
		this.numberOfStudentsBlocked = learningPlan.getNumberOfStudentsBlocked();
		this.expertModuleId = learningPlan.getExpertModuleId();
		this.name = learningPlan.getName();
	}
	
	public static Set<ClazzLearningPlanSimpleDto> convert(Set<ClazzLearningPlan> learningPlans) {
		return learningPlans.stream().map(ClazzLearningPlanSimpleDto::new).collect(Collectors.toSet());
	}

	public Integer getId() {
		return id;
	}
	public Double getGeneralScore() {
		return generalScore;
	}
	public Integer getNumberOfStudentsDoing() {
		return numberOfStudentsDoing;
	}
	public Integer getNumberOfStudentsDone() {
		return numberOfStudentsDone;
	}
	public Integer getNumberOfStudentsBlocked() {
		return numberOfStudentsBlocked;
	}
	public Integer getExpertModuleId() {
		return expertModuleId;
	}
	public String getName() {
		return name;
	}
}
