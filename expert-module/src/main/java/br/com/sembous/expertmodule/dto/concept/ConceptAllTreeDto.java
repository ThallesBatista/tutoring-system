package br.com.sembous.expertmodule.dto.concept;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sembous.expertmodule.controller.UntilValue;
import br.com.sembous.expertmodule.dto.activity.ActivityAllTreeDto;
import br.com.sembous.expertmodule.dto.notion.NotionDto;
import br.com.sembous.expertmodule.model.Concept;

public class ConceptAllTreeDto {

	private Integer id;
	private String name;
	private NotionDto notion;
	private ConceptDto parent;
	private List<ConceptAllTreeDto> dependencies;
	private List<ConceptAllTreeDto> children;
	private List<ActivityAllTreeDto> activities;

	public ConceptAllTreeDto(Concept concept, ObjectMapper mapper, UntilValue until) {
		this.id = concept.getId();
		this.name = concept.getName();
		concept.getParent().ifPresent(p -> this.parent = new ConceptDto(p));
		concept.getNotion().ifPresent(n -> this.notion = new NotionDto(n));
		this.dependencies = ConceptAllTreeDto.convert(concept.getDependencies(), mapper, until);
		this.children = ConceptAllTreeDto.convert(concept.getChildren(), mapper, until);
		
		Set<UntilValue> values = Set.of(UntilValue.TOPIC, UntilValue.PEDAGOGICAL_OBJECTIVE, UntilValue.NOTION, UntilValue.CONCEPT);
		if (values.contains(until)) this.activities = new ArrayList<>();
		else this.activities = ActivityAllTreeDto.convert(concept.getActivities(), mapper);
	}
	
	
	public static List<ConceptAllTreeDto> convert(Collection<Concept> concepts, ObjectMapper mapper, UntilValue until) {
		return concepts.stream().map(c -> new ConceptAllTreeDto(c, mapper, until)).collect(Collectors.toList());
	}
	

	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public NotionDto getNotion() {
		return notion;
	}
	public ConceptDto getParent() {
		return parent;
	}
	public List<ConceptAllTreeDto> getDependencies() {
		return dependencies;
	}
	public List<ConceptAllTreeDto> getChildren() {
		return children;
	}
	public List<ActivityAllTreeDto> getActivities() {
		return activities;
	}
}
