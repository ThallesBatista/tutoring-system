package br.com.sembous.smconsumerapi.dto;

import br.com.sembous.smconsumerapi.model.KnowledgeDoneManager;

public class KnowledgeDoneManagerDto {

	private Double generalAvgScore;
	private Integer learningPlansConcludedQuantity;
	
	
	public KnowledgeDoneManager convert() {
		return new KnowledgeDoneManager(this.generalAvgScore, this.learningPlansConcludedQuantity);
	}

	public void setGeneralAvgScore(Double generalAvgScore) {
		this.generalAvgScore = generalAvgScore;
	}
	public void setLearningPlansConcludedQuantity(Integer learningPlansConcludedQuantity) {
		this.learningPlansConcludedQuantity = learningPlansConcludedQuantity;
	}
}
