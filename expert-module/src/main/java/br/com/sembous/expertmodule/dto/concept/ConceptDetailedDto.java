package br.com.sembous.expertmodule.dto.concept;

import java.util.List;

import br.com.sembous.expertmodule.dto.activity.ActivityDto;
import br.com.sembous.expertmodule.dto.notion.NotionDto;
import br.com.sembous.expertmodule.model.Concept;

public class ConceptDetailedDto {

	private Integer id;
	private String name;
	private NotionDto notion;
	private ConceptDto parent;
	private List<ConceptDto> dependencies;
	private List<ConceptDto> children;
	private List<ActivityDto> activities;

	public ConceptDetailedDto(Concept concept) {
		this.id = concept.getId();
		this.name = concept.getName();
		concept.getParent().ifPresent(p -> this.parent = new ConceptDto(p));
		concept.getNotion().ifPresent(n -> this.notion = new NotionDto(n));
		this.dependencies = ConceptDto.convert(concept.getDependencies());
		this.children = ConceptDto.convert(concept.getChildren());
		this.activities = ActivityDto.convert(concept.getActivities());
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
	public List<ConceptDto> getDependencies() {
		return dependencies;
	}
	public List<ConceptDto> getChildren() {
		return children;
	}
	public List<ActivityDto> getActivities() {
		return activities;
	}	
}
