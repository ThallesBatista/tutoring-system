package br.com.sembous.expertmodule.dto.topic;

import java.util.List;

import br.com.sembous.expertmodule.dto.pedagogicalobjective.PedagogicalObjectiveDto;
import br.com.sembous.expertmodule.model.Topic;

public class TopicDetailedDto {

	private Integer id;
	private String name;
	private List<PedagogicalObjectiveDto> pedagogicalObjectives;
	
	public TopicDetailedDto(Topic topic) {
		this.id = topic.getId();
		this.name = topic.getName();
		this.pedagogicalObjectives = PedagogicalObjectiveDto.convert(topic.getPedagogicalObjectives());
	}
	

	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public List<PedagogicalObjectiveDto> getPedagogicalObjectives() {
		return pedagogicalObjectives;
	}
}