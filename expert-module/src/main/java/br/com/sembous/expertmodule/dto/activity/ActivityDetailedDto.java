package br.com.sembous.expertmodule.dto.activity;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sembous.expertmodule.dto.concept.ConceptDto;
import br.com.sembous.expertmodule.dto.content.ContentDto;
import br.com.sembous.expertmodule.model.Activity;

public class ActivityDetailedDto {

	private Integer id;
	private String name;
	private String type;
	private String subtype;
	private ConceptDto concept;
	private ContentDto content;

	public ActivityDetailedDto(Activity activity, ObjectMapper mapper) {
		this.id = activity.getId();
		this.name = activity.getName();
		this.type = activity.getType().toString();
		this.subtype = activity.getSubtype().toString();
		this.concept = new ConceptDto(activity.getConcept());
		this.content = new ContentDto(activity.getContent(), mapper);
	}
	

	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public String getSubtype() {
		return subtype;
	}
	public ConceptDto getConcept() {
		return concept;
	}
	public ContentDto getContent() {
		return content;
	}
}
