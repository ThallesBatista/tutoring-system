package br.com.sembous.emconsumerapi.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Topic implements ExpertModuleEntity{
	
	private Integer id;
	private String name;
	private List<PedagogicalObjective> pedagogicalObjectives = new ArrayList<>();
	
	public Topic(Integer id, String name, List<PedagogicalObjective> pedagogicalObjectives) {
		this.id = id;
		this.name = name;
		this.addPedagogicalObjectives(pedagogicalObjectives);
	}
	public Topic(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	private void addPedagogicalObjectives(List<PedagogicalObjective> pos) {
		pos.forEach(o -> {
			this.pedagogicalObjectives.add(o);
			o.setTopic(this);
		});
	}

	@Override
	public Integer getId() {
		return id;
	}
	@Override
	public String getName() {
		return name;
	}
	public List<PedagogicalObjective> getPedagogicalObjectives() {
		return Collections.unmodifiableList(pedagogicalObjectives);
	}	
}
