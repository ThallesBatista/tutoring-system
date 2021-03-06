package br.com.sembous.smconsumerapi.dto;

import java.time.Instant;

import br.com.sembous.smconsumerapi.model.LearningPlan;

public class LearningPlanDto {
	
	private Integer id;
//	private StudentDto student;
	private LearningPlanPieceDto learningPlanGraph;
	private Integer expertModuleId;
	private String name;
	private Instant updatedAt;
	
	public LearningPlan convert() {
		return new LearningPlan(id, learningPlanGraph.convert(), expertModuleId, name, updatedAt);
	}

	public void setId(Integer id) {
		this.id = id;
	}
//	public void setStudent(StudentDto student) {
//		this.student = student;
//	}
	public void setLearningPlanGraph(LearningPlanPieceDto learningPlanGraph) {
		this.learningPlanGraph = learningPlanGraph;
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
