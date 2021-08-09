package br.com.sembous.emconsumerapi.dto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import br.com.sembous.emconsumerapi.model.Concept;
import br.com.sembous.emconsumerapi.model.Notion;

public class ConceptDto implements Dto<Concept>{

	private Integer id;
	private String name;
	private NotionSimpleDto notion;
	private ConceptSimpleDto parent;
	private List<ConceptSimpleDto> dependencies;
	private List<ConceptDto> children;
	private List<ActivityDto> activities;
	
	@Override
	public Concept convert() {
		Notion fatherNotion;
		if (this.notion!=null) fatherNotion = this.notion.convert();
		else fatherNotion = null;
		
		Concept fatherConcept;
		if (this.parent!=null) fatherConcept = parent.convert();
		else fatherConcept = null;
		
		return new Concept(id, name, fatherNotion, fatherConcept, ConceptSimpleDto.convertList(dependencies), ConceptDto.convertList(children), ActivityDto.convertList(activities));
	}
	
	static List<Concept> convertList(Collection<ConceptDto> dtos) {
		return dtos.stream().map(ConceptDto::convert).collect(Collectors.toList());
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNotion(NotionSimpleDto notion) {
		this.notion = notion;
	}
	public void setParent(ConceptSimpleDto parent) {
		this.parent = parent;
	}
	public void setDependencies(List<ConceptSimpleDto> dependencies) {
		this.dependencies = dependencies;
	}
	public void setChildren(List<ConceptDto> children) {
		this.children = children;
	}
	public void setActivities(List<ActivityDto> activities) {
		this.activities = activities;
	}	
}

