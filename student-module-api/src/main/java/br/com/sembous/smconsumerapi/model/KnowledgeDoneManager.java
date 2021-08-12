package br.com.sembous.smconsumerapi.model;

import java.util.Set;

public final class KnowledgeDoneManager {

	private Set<KnowledgePiece> knowledgeDone;
	private Double generalAvgScore;
	private Integer learningPlansConcludedQuantity;
	
	public KnowledgeDoneManager(Double generalAvgScore, Integer learningPlansConcludedQuantity) {
		this.generalAvgScore = generalAvgScore;
		this.learningPlansConcludedQuantity = learningPlansConcludedQuantity;
	}
	public KnowledgeDoneManager(Set<KnowledgePiece> knowledgeDone) {
		this.knowledgeDone = knowledgeDone;
	}
	
//	public void setKnowledgeDone(Set<KnowledgePiece> knowledgeDone) {
//		this.knowledgeDone = knowledgeDone;
//	}

	public Double getGeneralAvgScore() {
		return generalAvgScore;
	}
	public Integer getLearningPlansConcludedQuantity() {
		return learningPlansConcludedQuantity;
	}	
	public Set<KnowledgePiece> getKnowledgeDone() {
		return knowledgeDone;
	}
	
	
//	public Double generalAvgScore() {
//		Set<KnowledgeType> evaluationActivityTypes = KnowledgeType.getEvaluationActivityTypes();
//		return this.knowledgeDone.stream()
//				.filter(kp -> evaluationActivityTypes.contains(kp.getType()))
//				.mapToDouble(KnowledgePiece::getScore)
//				.average()
//				.orElse(0);
//	}
//	
//	public Integer learningPlansConcludedQuantity() {
//		return Integer.valueOf(Long.valueOf(
//				this.knowledgeDone.stream()
//				.filter(kp -> kp.getType().equals(KnowledgeType.PEDAGOGICAL_OBJECTIVE))
//				.count())
//				.toString());
//	}
}
