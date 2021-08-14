package br.com.sembous.tutoringmodule.api.dto;

import java.util.Set;
import java.util.stream.Collectors;

import br.com.sembous.teachermoduleapi.model.ClazzLearningPlan;

public class ClazzLearningPlanSimpleDto {

	private Integer id;
	private Double generalScore;
	private Integer numberOfStudentsDoing;
	private Integer numberOfStudentsDone;
	private Integer numberOfStudentsBlocked;
	private Integer expertModuleId;
	private String name;
	
	private ClazzLearningPlanSimpleDto(ClazzLearningPlan lp) {
		this.id = lp.getId();
		this.generalScore = lp.getGeneralScore();
		this.numberOfStudentsDoing = lp.getNumberOfStudentsDoing();
		this.numberOfStudentsDone = lp.getNumberOfStudentsDone();
		this.numberOfStudentsBlocked = lp.getNumberOfStudentsBlocked();
		this.expertModuleId = lp.getExpertModuleId();
		this.name = lp.getName();
	}
	
	public static Set<ClazzLearningPlanSimpleDto> convertSet(Set<ClazzLearningPlan> learningPlans) {
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
