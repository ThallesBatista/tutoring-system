package br.com.sembous.studentmodule.dto;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.sembous.studentmodule.model.Interaction;
import br.com.sembous.studentmodule.model.KnowledgePiece;

public class InteractionDto {

	private Long id;
	private Instant createdAt;
	private String type;
	private KnowledgePieceSimpleDto knowledgePiece;
	
	public InteractionDto(Interaction interaction) {
		this.id = interaction.getId();
		this.createdAt = interaction.getCreatedAt();
		this.type = interaction.getType().toString();
		Optional<KnowledgePiece> optional = interaction.getKnowledgePiece();
		if (optional.isPresent()) this.knowledgePiece = new KnowledgePieceSimpleDto(optional.get()); 
		else this.knowledgePiece = null;
	}
	
	public static List<InteractionDto> convertList(List<Interaction> interactions) {
		return interactions.stream().map(InteractionDto::new).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}
	public Instant getCreatedAt() {
		return createdAt;
	}
	public String getType() {
		return type;
	}
	public KnowledgePieceSimpleDto getKnowledgePiece() {
		return knowledgePiece;
	}
}
