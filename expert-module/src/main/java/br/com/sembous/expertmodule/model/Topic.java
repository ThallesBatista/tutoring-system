package br.com.sembous.expertmodule.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name = "topics")
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@OneToMany(mappedBy = "topic", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@OrderColumn(name = "`order`")
	private List<PedagogicalObjective> pedagogicalObjectives = new ArrayList<>();
	
	public Topic() {}	
	public Topic(String name) {
		this.name = name;		
	}
	
	public void addPedagogicalObjective(PedagogicalObjective objective) {
		objective.setTopic(this);;
		this.pedagogicalObjectives.add(objective);
	}
	public void addPedagogicalObjective(Integer index, PedagogicalObjective objective) {
		objective.setTopic(this);
		this.pedagogicalObjectives.add(index, objective);
	}
	private void removePedagogicalObjective (PedagogicalObjective objective) {
		objective.setTopic(null);
		this.pedagogicalObjectives.remove(objective);
	}
	public Integer getPedagogicalObjectiveOrder(PedagogicalObjective objective) {
		return this.pedagogicalObjectives.indexOf(objective);
	}
	public void alterPedagogicalObjectiveOrder(Integer index, PedagogicalObjective objective) {
		this.removePedagogicalObjective(objective);
		this.addPedagogicalObjective(index, objective);
	}
	

	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public List<PedagogicalObjective> getPedagogicalObjectives() {
		return Collections.unmodifiableList(this.pedagogicalObjectives);
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topic other = (Topic) obj;
		if (this.id == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.id.equals(other.getId()))
			return false;
		return true;
	}
}
