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

@Entity @Table(name = "notions")
public class Notion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@OneToMany(mappedBy = "notion", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@OrderColumn(name = "`order`")
	private List<Concept> concepts = new ArrayList<>();
	@ManyToOne(fetch = FetchType.LAZY)
	private PedagogicalObjective pedagogicalObjective;
	
	public Notion() {}
	public Notion(String name) {
		this.name = name;
	}
	
	
	public void addConcept(Concept concept) {
		concept.setNotion(this);
		concept.setParent(null);
		this.concepts.add(concept);
	}	
	public void addConcept(Integer index, Concept concept) {
		concept.setNotion(this);
		concept.setParent(null);
		this.concepts.add(index, concept);
	}	
	private void removeConcept(Concept concept) {
		concept.setNotion(null);
		this.concepts.remove(concept);		
	}	
	public List<Concept> getConcepts() {
		return Collections.unmodifiableList(this.concepts);
	}
	public Integer getConceptOrder(Concept concept) {
		return this.concepts.indexOf(concept);
	}
	public void alterConceptOrder(Integer index, Concept concept) {
		this.removeConcept(concept);
		this.addConcept(index, concept);
	}
	
		
	protected void setPedagogicalObjective(PedagogicalObjective pedagogicalObjective) {
		this.pedagogicalObjective = pedagogicalObjective;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public PedagogicalObjective getPedagogicalObjective() {
		return pedagogicalObjective;
	}	
	public String getName() {
		return name;
	}	
	public Integer getId() {
		return id;
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
		Notion other = (Notion) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}	
}