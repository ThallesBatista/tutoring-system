package br.com.sembous.smconsumerapi.dto;

import java.time.Instant;

import br.com.sembous.smconsumerapi.model.LearningPlan;

public class LearningPlanSimpleDto {

	private Integer id;
	private Integer expertModuleId;	
	private String name;
	private Instant updatedAt;
	
	public LearningPlan convert() {
		return new LearningPlan(id, expertModuleId, name, updatedAt);
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public void setExpertModuleId(Integer expertModuleId) {
		this.expertModuleId = expertModuleId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}
}
