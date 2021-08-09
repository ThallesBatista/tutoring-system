package br.com.sembous.emconsumerapi.model;

import java.util.ArrayList;
import java.util.List;

public class Notion implements ExpertModuleEntity{

	private Integer id;
	private String name;
	private PedagogicalObjective pedagogicalObjective;
	private List<Concept> concepts = new ArrayList<>();
	
	public Notion(Integer id, String name, PedagogicalObjective pedagogicalObjective, List<Concept> concepts) {
		this.id = id;
		this.name = name;
		this.pedagogicalObjective = pedagogicalObjective;
		this.addConcepts(concepts);
	}	
	public Notion(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	private void addConcepts(List<Concept> concepts) {
		concepts.forEach(n -> {
			this.concepts.add(n);
			n.setNotion(this);
		});
	}
	
	void setPedagogicalObjective(PedagogicalObjective pedagogicalObjective) {
		this.pedagogicalObjective = pedagogicalObjective;
	}
	

	@Override
	public Integer getId() {
		return id;
	}
	@Override
	public String getName() {
		return name;
	}
	public PedagogicalObjective getPedagogicalObjective() {
		return pedagogicalObjective;
	}
	public List<Concept> getConcepts() {
		return concepts;
	}
}

