package br.com.sembous.expertmodule.dto.topic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sembous.expertmodule.controller.UntilValue;
import br.com.sembous.expertmodule.dto.pedagogicalobjective.PedagogicalObjectiveAllTreeDto;
import br.com.sembous.expertmodule.model.Topic;

public class TopicAllTreeDto {
	
	private Integer id;
	private String name;
	private List<PedagogicalObjectiveAllTreeDto> pedagogicalObjectives;

	public TopicAllTreeDto(Topic topic, ObjectMapper mapper, UntilValue until) {
		this.id = topic.getId();
		this.name = topic.getName();
		if (until.equals(UntilValue.TOPIC)) this.pedagogicalObjectives = new ArrayList<>();
		else this.pedagogicalObjectives = PedagogicalObjectiveAllTreeDto.convert(topic.getPedagogicalObjectives(), mapper, until);
	}

	
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public List<PedagogicalObjectiveAllTreeDto> getPedagogicalObjectives() {
		return pedagogicalObjectives;
	}

	
	public static List<TopicAllTreeDto> convert(List<Topic> topics, ObjectMapper mapper, UntilValue until) {
		return topics.stream().map(t -> new TopicAllTreeDto(t, mapper, until)).collect(Collectors.toList());
	}
}