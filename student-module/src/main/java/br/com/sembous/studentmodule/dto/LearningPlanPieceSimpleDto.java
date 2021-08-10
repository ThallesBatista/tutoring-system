package br.com.sembous.studentmodule.dto;

import br.com.sembous.studentmodule.model.LearningPlanPiece;

public class LearningPlanPieceSimpleDto {

	private Long id;
	private String type;
	private Integer expertModuleId;
	private String category; 
	private String status;
	private Double score;
	private String name;
	
	public LearningPlanPieceSimpleDto(LearningPlanPiece piece) {
		this.id = piece.getId();
		this.type = piece.getType().toString();
		this.expertModuleId = piece.getExpertModuleId();
		this.category = piece.getCategory().toString();
		this.status = piece.getStatus().toString();
		this.score = piece.getScore();
		this.name = piece.getName();
	}

	public Long getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public Integer getExpertModuleId() {
		return expertModuleId;
	}
	public String getCategory() {
		return category;
	}
	public String getStatus() {
		return status;
	}
	public Double getScore() {
		return score;
	}
	public String getName() {
		return name;
	}
}
