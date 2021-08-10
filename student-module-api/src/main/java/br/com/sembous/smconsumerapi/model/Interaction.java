package br.com.sembous.smconsumerapi.model;

import java.time.Instant;

public final class Interaction {

	private Long id;
	private Instant createdAt;
	private InteractionType type;
	
	public Interaction(Long id, Instant createdAt, InteractionType type) {
		this.createdAt = createdAt;
		this.type = type;
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	public Instant getCreatedAt() {
		return createdAt;
	}
	public InteractionType getType() {
		return type;
	}
}
