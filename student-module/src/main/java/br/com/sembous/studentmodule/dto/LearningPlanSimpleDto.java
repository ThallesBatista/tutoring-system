package br.com.sembous.studentmodule.dto;

import java.time.Instant;

import br.com.sembous.studentmodule.model.LearningPlan;

public class LearningPlanSimpleDto {

	private Integer id;
	private Integer expertModuleId;	
	private String name;
	private Instant updatedAt;
	
	public LearningPlanSimpleDto(LearningPlan learningPlan) {
		this.id = learningPlan.getId();
		this.expertModuleId = learningPlan.getExpertModuleId();
		this.name = learningPlan.getName();
		this.updatedAt = learningPlan.getUpdatedAt();
	}
	
	public Integer getId() {
		return id;
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
