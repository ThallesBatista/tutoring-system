package br.com.sembous.expertmodule.dto.notion;

import java.util.List;

import br.com.sembous.expertmodule.dto.concept.ConceptDto;
import br.com.sembous.expertmodule.dto.pedagogicalobjective.PedagogicalObjectiveDto;
import br.com.sembous.expertmodule.model.Notion;

public class NotionDetailedDto {

	private Integer id;
	private String name;
	private PedagogicalObjectiveDto pedagogicalObjective;
	private List<ConceptDto> concepts;

	public NotionDetailedDto(Notion notion) {
		this.id = notion.getId();
		this.name = notion.getName();
		this.pedagogicalObjective = new PedagogicalObjectiveDto(notion.getPedagogicalObjective());
		this.concepts = ConceptDto.convert(notion.getConcepts());
	}
	

	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public PedagogicalObjectiveDto getPedagogicalObjective() {
		return this.pedagogicalObjective;
	}
	public List<ConceptDto> getConcepts() {
		return this.concepts;
	}
}
