package br.com.sembous.emconsumerapi.model;

import java.util.ArrayList;
import java.util.List;

public class Concept implements ExpertModuleEntity{

	private Integer id;
	private String name;
	private Notion notion;
	private Concept parent;
	private List<Concept> dependencies = new ArrayList<>();
	private List<Concept> children  = new ArrayList<>();
	private List<Activity> activities  = new ArrayList<>();
	
	public Concept(Integer id, String name, Notion notion, Concept parent, List<Concept> dependencies, List<Concept> children, List<Activity> activities) {
		this.id = id;
		this.name = name;
		this.notion = notion;
		this.parent = parent;
		this.addDependencies(dependencies);
		this.addChildren(children);
		this.addActivities(activities);
	}
	public Concept(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	private void addChildren(List<Concept> children) {
		children.forEach(c -> {
			this.children.add(c);
			c.setParent(this);
		});
	}
	private void addDependencies(List<Concept> dependencies) {
		dependencies.forEach(c -> {
			this.dependencies.add(c);
		});
	}
	private void addActivities(List<Activity> activities) {
		activities.forEach(a -> {
			this.activities.add(a);
			a.setConcept(this);
		});
	}
	
	
	void setParent(Concept parent) {
		this.parent = parent;
	}
	void setNotion(Notion notion) {
		this.notion = notion;
	}

	@Override
	public Integer getId() {
		return id;
	}
	@Override
	public String getName() {
		return name;
	}
	public Notion getNotion() {
		return notion;
	}
	public Concept getParent() {
		return parent;
	}
	public List<Concept> getDependencies() {
		return dependencies;
	}
	public List<Concept> getChildren() {
		return children;
	}
	public List<Activity> getActivities() {
		return activities;
	}
	
}
