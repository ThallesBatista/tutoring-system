package br.com.sembous.studentmodule.dto;

import br.com.sembous.studentmodule.model.KnowledgeDoneManager;

public class KnowledgeDoneManagerDto {

	private Double generalAvgScore;
	private Integer learningPlansConcludedQuantity;
	
	public KnowledgeDoneManagerDto(KnowledgeDoneManager knowledgeManager) {
		this.generalAvgScore = knowledgeManager.generalAvgScore();
		this.learningPlansConcludedQuantity = knowledgeManager.learningPlansConcludedQuantity();
	}

	public Double getGeneralAvgScore() {
		return generalAvgScore;
	}
	public Integer getLearningPlansConcludedQuantity() {
		return learningPlansConcludedQuantity;
	}
}
