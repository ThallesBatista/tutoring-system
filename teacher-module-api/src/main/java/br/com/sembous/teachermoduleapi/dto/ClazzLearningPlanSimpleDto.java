package br.com.sembous.teachermoduleapi.dto;

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
	
	public ClazzLearningPlan convert() {
		return new ClazzLearningPlan(id, generalScore, numberOfStudentsDoing, numberOfStudentsDone, numberOfStudentsBlocked, expertModuleId, name);
	}
	
	public static Set<ClazzLearningPlan> convertSet(Set<ClazzLearningPlanSimpleDto> learningPlans) {
		return learningPlans.stream().map(ClazzLearningPlanSimpleDto::convert).collect(Collectors.toSet());
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public void setGeneralScore(Double generalScore) {
		this.generalScore = generalScore;
	}
	public void setNumberOfStudentsDoing(Integer numberOfStudentsDoing) {
		this.numberOfStudentsDoing = numberOfStudentsDoing;
	}
	public void setNumberOfStudentsDone(Integer numberOfStudentsDone) {
		this.numberOfStudentsDone = numberOfStudentsDone;
	}
	public void setNumberOfStudentsBlocked(Integer numberOfStudentsBlocked) {
		this.numberOfStudentsBlocked = numberOfStudentsBlocked;
	}
	public void setExpertModuleId(Integer expertModuleId) {
		this.expertModuleId = expertModuleId;
	}
	public void setName(String name) {
		this.name = name;
	}
}
