package br.com.sembous.smconsumerapi.dto;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import br.com.sembous.smconsumerapi.model.Interaction;
import br.com.sembous.smconsumerapi.model.InteractionType;

public class InteractionSimpleDto {

	private Long id;
	private Instant createdAt;
	private String type;
	
	public Interaction convert() {
		return new Interaction(id, createdAt, InteractionType.valueOf(type));
	}
	
	public static List<Interaction> converList(List<InteractionSimpleDto> dtos) {
		return dtos.stream().map(InteractionSimpleDto::convert).collect(Collectors.toList());
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
}
