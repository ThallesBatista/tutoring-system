package br.com.sembous.smconsumerapi.form;

import br.com.sembous.smconsumerapi.model.LearningPlanPiece;

public class LearningPlanPieceDoneForm {

	private String type;
	private Integer expertModuleId;
	private Double score = Double.valueOf(0);
	private String status;
	
	public LearningPlanPieceDoneForm(LearningPlanPiece piece) {
		this.type = piece.getType().toString();
		this.expertModuleId = piece.getExpertModuleId();
		this.score = piece.getScore();
		this.status = piece.getStatus().toString();
	}

	public String getType() {
		return type;
	}
	public Integer getExpertModuleId() {
		return expertModuleId;
	}
	public Double getScore() {
		return score;
	}
	public String getStatus() {
		return status;
	}
}
