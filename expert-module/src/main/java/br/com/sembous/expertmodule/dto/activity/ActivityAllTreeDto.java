package br.com.sembous.expertmodule.dto.activity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sembous.expertmodule.dto.concept.ConceptDto;
import br.com.sembous.expertmodule.dto.content.ContentDto;
import br.com.sembous.expertmodule.model.Activity;

public class ActivityAllTreeDto {
	
	private Integer id;
	private String name;
	private String type;
	private String subtype;
	
	private ConceptDto concept;
	private ContentDto content;
	
	public ActivityAllTreeDto(Activity activity, ObjectMapper mapper) {
		this.id = activity.getId();
		this.name = activity.getName();
		this.type = activity.getType().toString();
		this.subtype = activity.getSubtype().toString();
		this.concept = new ConceptDto(activity.getConcept());
		if (activity.getContent()!=null)	this.content = new ContentDto(activity.getContent(), mapper);
	}
	
	
	public static List<ActivityAllTreeDto> convert(Collection<Activity> activities, ObjectMapper mapper) {
		return activities.stream().map(a -> new ActivityAllTreeDto(a, mapper)).collect(Collectors.toList());
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
	public ConceptDto getConcept() {
		return concept;
	}
	public ContentDto getContent() {
		return content;
	}
	public String getSubtype() {
		return subtype;
	}
}
