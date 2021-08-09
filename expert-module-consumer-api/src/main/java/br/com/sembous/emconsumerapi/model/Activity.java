package br.com.sembous.emconsumerapi.model;

import com.fasterxml.jackson.databind.JsonNode;

public class Activity implements ExpertModuleEntity{

	private Integer id;
	private String name;
	private ActivityType type;
	private ActivitySubtype subtype;
	private Concept concept;
	private JsonNode content;
	
	
	public Activity(Integer id, String name, ActivityType type, ActivitySubtype subtype, Concept concept, JsonNode content) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.subtype = subtype;
		this.concept = concept;
		this.content = content;
	}
	public Activity(Integer id, String name, ActivityType type, ActivitySubtype subtype, JsonNode content) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.subtype = subtype;
		this.content = content;
	}
	
	
	void setConcept(Concept concept) {
		this.concept = concept;
	}
	
	
	@Override
	public Integer getId() {
		return id;
	}
	@Override
	public String getName() {
		return name;
	}
	public ActivityType getType() {
		return type;
	}
	public ActivitySubtype getSubtype() {
		return subtype;
	}
	public Concept getConcept() {
		return concept;
	}
	public JsonNode getContent() {
		return content;
	}
	
	
}
