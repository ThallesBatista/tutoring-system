package br.com.sembous.emconsumerapi.model;

import java.util.ArrayList;
import java.util.List;

public class PedagogicalObjective implements ExpertModuleEntity{
	
	private Integer id;
	private String name;
	private Topic topic;
	private List<Notion> notions = new ArrayList<>();
	
	public PedagogicalObjective(Integer id, String name, Topic topic, List<Notion> notions) {
		this.id = id;
		this.name = name;
		this.topic = topic;
		this.addNotions(notions);
	}	
	public PedagogicalObjective(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	private void addNotions(List<Notion> notions) {
		notions.forEach(n -> {
			this.notions.add(n);
			n.setPedagogicalObjective(this);
		});
	}
	
	void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	@Override
	public String getName() {
		return name;
	}
	public Topic getTopic() {
		return topic;
	}
	public List<Notion> getNotions() {
		return notions;
	}
}
