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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity @Table(name = "pedagogical_objectives")
public class PedagogicalObjective {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Topic topic;
	@OneToMany(mappedBy = "pedagogicalObjective", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@OrderColumn(name = "`order`")
	private List<Notion> notions = new ArrayList<>();
	
	public PedagogicalObjective() {	}
	public PedagogicalObjective(String name) {
		this.name = name;
	}
	
	
	public void addNotion(Notion notion) {
		notion.setPedagogicalObjective(this);
		this.notions.add(notion);
	}
	public void addNotion(Integer index, Notion notion) {
		notion.setPedagogicalObjective(this);
		this.notions.add(index, notion);
	}
	public List<Notion> getNotions() {
		return Collections.unmodifiableList(this.notions);
	}
	private void removeNotion (Notion notion) {
		notion.setPedagogicalObjective(null);
		this.notions.remove(notion);
	}
	public Integer getNotionOrder(Notion notion) {
		return this.notions.indexOf(notion);
	}
	public void alterNotionOrder(Integer index, Notion notion) {
		this.removeNotion(notion);
		this.addNotion(index, notion);
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Topic getTopic() {
		return topic;
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
		PedagogicalObjective other = (PedagogicalObjective) obj;
		if (this.id == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.id.equals(other.getId()))
			return false;
		return true;
	}
}