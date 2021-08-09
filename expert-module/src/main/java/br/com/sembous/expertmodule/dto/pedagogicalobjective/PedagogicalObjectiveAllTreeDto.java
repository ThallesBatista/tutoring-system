package br.com.sembous.expertmodule.dto.pedagogicalobjective;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sembous.expertmodule.controller.UntilValue;
import br.com.sembous.expertmodule.dto.notion.NotionAllTreeDto;
import br.com.sembous.expertmodule.dto.topic.TopicDto;
import br.com.sembous.expertmodule.model.PedagogicalObjective;

public class PedagogicalObjectiveAllTreeDto {

	private Integer id;
	private String name;
	private TopicDto topic;
	private List<NotionAllTreeDto> notions;
	
	public PedagogicalObjectiveAllTreeDto(PedagogicalObjective pedagogicalObjective, ObjectMapper mapper, UntilValue until) {
		this.id = pedagogicalObjective.getId();
		this.name = pedagogicalObjective.getName();
		this.topic = new TopicDto(pedagogicalObjective.getTopic());
		
		Set<UntilValue> values = Set.of(UntilValue.TOPIC, UntilValue.PEDAGOGICAL_OBJECTIVE);
		if (values.contains(until)) this.notions = new ArrayList<>();
		else this.notions = NotionAllTreeDto.convert(pedagogicalObjective.getNotions(), mapper, until);
	}
	
	
	public static List<PedagogicalObjectiveAllTreeDto> convert(List<PedagogicalObjective> pedagogicalObjectives, ObjectMapper mapper, UntilValue until) {
		return pedagogicalObjectives.stream().map(p -> new PedagogicalObjectiveAllTreeDto(p, mapper, until)).collect(Collectors.toList());
	}
	

	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public TopicDto getTopic() {
		return topic;
	}
	public List<NotionAllTreeDto> getNotions() {
		return notions;
	}
}
