package br.com.sembous.emconsumerapi.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import br.com.sembous.emconsumerapi.model.Notion;

public class NotionDto implements Dto<Notion>{

	private Integer id;
	private String name;
	private PedagogicalObjectiveSimpleDto pedagogicalObjective;
	private List<ConceptDto> concepts = new ArrayList<>();
	
	@Override
	public Notion convert() {
		return new Notion(id, name, pedagogicalObjective.convert(), ConceptDto.convertList(concepts));
	}
	
	static List<Notion> convertList(Collection<NotionDto> dtos) {
		return dtos.stream().map(NotionDto::convert).collect(Collectors.toList());
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPedagogicalObjective(PedagogicalObjectiveSimpleDto pedagogicalObjective) {
		this.pedagogicalObjective = pedagogicalObjective;
	}
	public void setConcepts(List<ConceptDto> concepts) {
		this.concepts = concepts;
	}	
}

