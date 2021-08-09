package br.com.sembous.expertmodule.dto.notion;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sembous.expertmodule.controller.UntilValue;
import br.com.sembous.expertmodule.dto.concept.ConceptAllTreeDto;
import br.com.sembous.expertmodule.dto.pedagogicalobjective.PedagogicalObjectiveDto;
import br.com.sembous.expertmodule.model.Notion;

public class NotionAllTreeDto {

	private Integer id;
	private String name;
	private PedagogicalObjectiveDto pedagogicalObjective;
	private List<ConceptAllTreeDto> concepts;
	
	public NotionAllTreeDto(Notion notion, ObjectMapper mapper, UntilValue until) {
		this.id = notion.getId();
		this.name = notion.getName();
		this.pedagogicalObjective = new PedagogicalObjectiveDto(notion.getPedagogicalObjective());
		
		Set<UntilValue> values = Set.of(UntilValue.TOPIC, UntilValue.PEDAGOGICAL_OBJECTIVE, UntilValue.NOTION);
		if (values.contains(until)) this.concepts = new ArrayList<>();
		else this.concepts = ConceptAllTreeDto.convert(notion.getConcepts(), mapper, until);
	}
	
	
	public static List<NotionAllTreeDto> convert(List<Notion> notions, ObjectMapper mapper, UntilValue until) {
		return notions.stream().map(n -> new NotionAllTreeDto(n, mapper, until)).collect(Collectors.toList());
	}


	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public PedagogicalObjectiveDto getPedagogicalObjective() {
		return pedagogicalObjective;
	}
	public List<ConceptAllTreeDto> getConcepts() {
		return concepts;
	}
}
