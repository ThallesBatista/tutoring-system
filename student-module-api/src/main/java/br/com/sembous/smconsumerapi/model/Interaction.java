package br.com.sembous.smconsumerapi.model;

import java.time.Instant;

public final class Interaction {

	private Long id;
//	private Student student;
	private Instant createdAt;
	private InteractionType type;
//	private KnowledgePiece knowledgePiece;
	
	public Interaction(Long id, Instant createdAt, InteractionType type) {
		this.createdAt = createdAt;
		this.type = type;
		this.id = id;
//		this.student = student;
	}
//	public Interaction(Long id, Student student, Instant createdAt, InteractionType type, KnowledgePiece knowledgePiece) {
//		this.createdAt = createdAt;
//		this.type = type;
//		this.id = id;
//		this.student = student;
//		this.knowledgePiece = knowledgePiece;
//	}
	
	public Long getId() {
		return id;
	}
//	public Student getStudent() {
//		return student;
//	}
	public Instant getCreatedAt() {
		return createdAt;
	}
	public InteractionType getType() {
		return type;
	}	
//	public Optional<KnowledgePiece> getKnowledgePiece(){
//		return Optional.ofNullable(this.knowledgePiece);
//	}
}
