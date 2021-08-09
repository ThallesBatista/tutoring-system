package br.com.sembous.emconsumerapi.dto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.sembous.emconsumerapi.model.Activity;
import br.com.sembous.emconsumerapi.model.ActivitySubtype;
import br.com.sembous.emconsumerapi.model.ActivityType;

public class ActivityDto implements Dto<Activity>{

	private Integer id;
	private String name;
	private String type;
	private String subtype;
	private ConceptSimpleDto concept;
	private JsonNode content;
	
	@Override
	public Activity convert() {
		return new Activity(id, name, ActivityType.valueOf(type), ActivitySubtype.valueOf(subtype), concept.convert(), content);
	}
	
	static List<Activity> convertList(Collection<ActivityDto> dtos) {
		return dtos.stream().map(ActivityDto::convert).collect(Collectors.toList());
	}

	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
	public void setConcept(ConceptSimpleDto concept) {
		this.concept = concept;
	}
	public void setContent(JsonNode content) {
		this.content = content;
	}	
}

