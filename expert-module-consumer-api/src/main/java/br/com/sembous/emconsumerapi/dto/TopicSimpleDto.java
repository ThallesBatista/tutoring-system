package br.com.sembous.emconsumerapi.dto;

import br.com.sembous.emconsumerapi.model.Topic;

public class TopicSimpleDto implements SimpleDto<Topic> {

	private Integer id;
	private String name;
	@Override
	public Topic convert() {
		return new Topic(id, name);
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
}
