package br.com.sembous.expertmodule.dto.pedagogicalobjective;

import java.util.List;

import br.com.sembous.expertmodule.dto.notion.NotionDto;
import br.com.sembous.expertmodule.dto.topic.TopicDto;
import br.com.sembous.expertmodule.model.PedagogicalObjective;

public class PedagogicalObjectiveDetailedDto {

	private Integer id;
	private String name;
	private TopicDto topic;
	private List<NotionDto> notions;

	public PedagogicalObjectiveDetailedDto(PedagogicalObjective pedagogicalObjective) {
			this.id = pedagogicalObjective.getId();
			this.name = pedagogicalObjective.getName();
			this.topic = new TopicDto(pedagogicalObjective.getTopic());
			this.notions = NotionDto.convert(pedagogicalObjective.getNotions());
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
	public List<NotionDto> getNotions() {
		return notions;
	}
}
