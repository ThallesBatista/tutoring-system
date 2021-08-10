package br.com.sembous.smconsumerapi.dto;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import br.com.sembous.smconsumerapi.model.Interaction;
import br.com.sembous.smconsumerapi.model.InteractionType;

public class InteractionDto {

	private Long id;
	private Instant createdAt;
	private String type;
//	private KnowledgePieceSimpleDto knowledgePiece;
	
	public Interaction convert() {
		return new Interaction(id, createdAt, InteractionType.valueOf(type));
	}
	
	public static List<Interaction> convertAsList(List<InteractionDto> dtos) {
		return dtos.stream().map(InteractionDto::convert).collect(Collectors.toList());
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
	public void setType(String type) {
		this.type = type;
	}
//	public void setKnowledgePiece(KnowledgePieceSimpleDto knowledgePiece) {
//		this.knowledgePiece = knowledgePiece;
//	}
}
