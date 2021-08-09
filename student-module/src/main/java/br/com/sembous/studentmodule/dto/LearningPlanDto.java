package br.com.sembous.studentmodule.dto;

import java.time.Instant;

import br.com.sembous.studentmodule.model.LearningPlan;

public class LearningPlanDto {

	private Integer id;
	private StudentDto student;
	private LearningPlanPieceDto learningPlanGraph;
	private Integer expertModuleId;
	private String name;
	private Instant updatedAt;
	
	public LearningPlanDto(LearningPlan learningPlan) {
		this.id = learningPlan.getId();
		this.expertModuleId = learningPlan.getExpertModuleId();
		this.name = learningPlan.getName();
		this.updatedAt = learningPlan.getUpdatedAt();
		this.student = new StudentDto(learningPlan.getStudent(), Boolean.FALSE);
		this.learningPlanGraph = new LearningPlanPieceDto(learningPlan.getLearningPlanGraph());
	}

	public Integer getId() {
		return id;
	}
	public StudentDto getStudent() {
		return student;
	}
	public LearningPlanPieceDto getLearningPlanGraph() {
		return learningPlanGraph;
	}
	public Integer getExpertModuleId() {
		return expertModuleId;
	}
	public String getName() {
		return name;
	}
	public Instant getUpdatedAt() {
		return updatedAt;
	}
}
