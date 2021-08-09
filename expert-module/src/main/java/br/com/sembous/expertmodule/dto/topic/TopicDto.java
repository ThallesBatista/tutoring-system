package br.com.sembous.expertmodule.dto.topic;

import java.util.List;
import java.util.stream.Collectors;

import br.com.sembous.expertmodule.model.Topic;

public class TopicDto {

	private Integer id;
	private String name;
	
	public TopicDto(Topic topic) {
		this.id = topic.getId();
		this.name = topic.getName();
	}	
	
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	public static List<TopicDto> convert(List<Topic> topics) {
		return topics.stream().map(TopicDto::new).collect(Collectors.toList());
	}
}
