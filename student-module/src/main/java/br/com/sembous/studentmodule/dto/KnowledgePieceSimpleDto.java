package br.com.sembous.studentmodule.dto;

import java.time.Instant;

import br.com.sembous.studentmodule.model.KnowledgePiece;

public class KnowledgePieceSimpleDto {

	private Integer expertModuleId;
	private String type;
	private Instant doneAt;
	private Double score;
	private String status;
	
	
	public KnowledgePieceSimpleDto(KnowledgePiece knowledgePiece) {
		this.expertModuleId = knowledgePiece.getExpertModuleId();
		this.type = knowledgePiece.getType().toString();
		this.doneAt = knowledgePiece.getDoneAt();
		this.score = knowledgePiece.getScore();
		this.status = knowledgePiece.getStatus().toString();
	}


	public Integer getExpertModuleId() {
		return expertModuleId;
	}
	public String getType() {
		return type;
	}
	public Instant getDoneAt() {
		return doneAt;
	}
	public Double getScore() {
		return score;
	}
	public String getStatus() {
		return status;
	}
}
