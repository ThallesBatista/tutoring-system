package br.com.sembous.emconsumerapi.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.sembous.emconsumerapi.model.Topic;

public class TopicDto implements Dto<Topic>{

	private Integer id;
	private String name;
	private List<PedagogicalObjectiveDto> pedagogicalObjectives = new ArrayList<>();
	
	@Override
	public Topic convert() {
		return new Topic(id, name, PedagogicalObjectiveDto.convertList(pedagogicalObjectives));
	}	
	
	static List<Topic> convertList(List<TopicDto> dtos) {
		return dtos.stream().map(TopicDto::convert).collect(Collectors.toList());
	}
	

	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPedagogicalObjectives(List<PedagogicalObjectiveDto> pedagogicalObjectives) {
		this.pedagogicalObjectives = pedagogicalObjectives;
	}		
}
