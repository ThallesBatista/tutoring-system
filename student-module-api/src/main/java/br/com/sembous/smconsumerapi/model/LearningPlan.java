package br.com.sembous.smconsumerapi.model;

import java.beans.Transient;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

public class LearningPlan {

	private Integer id;
//	private Student student;
	private LearningPlanPiece learningPlanGraph;
	private Integer expertModuleId;
	private String name;
	private Instant updatedAt;
	private Double progress;
	private Double avgScore;
	
	public LearningPlan(Integer id, LearningPlanPiece learningPlanGraph, Integer expertModuleId, String name, Instant updatedAt) {
		this.id = id;
		this.learningPlanGraph = learningPlanGraph;
		this.expertModuleId = expertModuleId;
		this.name = name;
		this.updatedAt = updatedAt;
	}
	public LearningPlan(Integer id, Integer expertModuleId, String name, Instant updatedAt, Double progress, Double avgScore) {
		this.id = id;
		this.expertModuleId = expertModuleId;
		this.name = name;
		this.updatedAt = updatedAt;
		this.progress = progress;
		this.avgScore = avgScore;
	}
	
	@Transient
	public Optional<LearningPlanPiece> getNextActivity() {
		Set<KnowledgeType> activityTypes = KnowledgeType.getActivityTypes();
		return LearningPlanGraphUtil.learningPlanOrderListFlatter(learningPlanGraph).stream()
				.filter(c -> activityTypes.contains(c.getType()))
				.filter(c -> !c.getStatus().equals(KnowledgeStatus.DONE))
				.findFirst();
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
		if (this.learningPlanGraph==null) return this.avgScore;
		return this.learningPlanGraph.getScore();
	}
	public Double getProgress() {
		if (this.learningPlanGraph==null) return this.progress;
		return this.learningPlanGraph.getProgress();
	}
	
	
	public void serializable() {
		this.learningPlanGraph.serializable();
	}
}
