package br.com.sembous.emconsumerapi.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.sembous.emconsumerapi.model.PedagogicalObjective;

public class PedagogicalObjectiveDto implements Dto<PedagogicalObjective>{

	private Integer id;
	private String name;
	private TopicSimpleDto topic;
	private List<NotionDto> notions = new ArrayList<>();
	
	@Override
	public PedagogicalObjective convert() {
		return new PedagogicalObjective(id, name, this.topic.convert(), NotionDto.convertList(notions)); //ainda falta arrumar a conversao dos de cima 
	}
	
	static List<PedagogicalObjective> convertList(List<PedagogicalObjectiveDto> dtos) {
		return dtos.stream().map(PedagogicalObjectiveDto::convert).collect(Collectors.toList());
	}
	

	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTopic(TopicSimpleDto topic) {
		this.topic = topic;
	}
	public void setNotions(List<NotionDto> notions) {
		this.notions = notions;
	}
	
}

