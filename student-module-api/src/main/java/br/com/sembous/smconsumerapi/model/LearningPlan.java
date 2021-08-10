package br.com.sembous.smconsumerapi.model;

import java.time.Instant;

public class LearningPlan {

	private Integer id;
//	private Student student;
	private LearningPlanPiece learningPlanGraph;
	private Integer expertModuleId;
	private String name;
	private Instant updatedAt;
	
	public LearningPlan(Integer id, LearningPlanPiece learningPlanGraph, Integer expertModuleId, String name, Instant updatedAt) {
		this.id = id;
		this.learningPlanGraph = learningPlanGraph;
		this.expertModuleId = expertModuleId;
		this.name = name;
		this.updatedAt = updatedAt;
	}
	public LearningPlan(Integer id, Integer expertModuleId, String name, Instant updatedAt) {
		this.id = id;
		this.expertModuleId = expertModuleId;
		this.name = name;
		this.updatedAt = updatedAt;
	}
	
	public Integer getId() {
		return id;
	}
	public LearningPlanPiece getLearningPlanGraph() {
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
	public Double getAvgScore() {
		return this.learningPlanGraph.getScore();
	}
	public Double getProgress() {
		return this.learningPlanGraph.getProgress();
	}
}
