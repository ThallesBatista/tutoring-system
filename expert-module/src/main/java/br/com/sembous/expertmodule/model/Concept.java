package br.com.sembous.expertmodule.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name = "concepts")
public class Concept {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@ManyToOne(fetch = FetchType.LAZY)
	private Notion notion;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST) 
	@JoinTable(
			name = "concepts_dependencies", 
			joinColumns = @JoinColumn(name = "concept_id", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "dependency_concept_id", referencedColumnName = "id")
			)
	private Set<Concept> dependencies = new HashSet<>();
//	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "dependencies")
//	private Set<Concept> dependents = new HashSet<>();
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@OrderColumn(name = "`order_child`")
//	@JoinTable(
//			name = "concepts_children",
//			joinColumns = @JoinColumn(name = "concept_id", referencedColumnName = "id"), 
//			inverseJoinColumns = @JoinColumn(name = "child_concept_id", referencedColumnName = "id")
//			)
	private List<Concept> children = new ArrayList<>();
	@ManyToOne(fetch = FetchType.LAZY)
	private Concept parent;
	@OneToMany(mappedBy = "concept", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@OrderColumn(name = "`order`")
	private List<Activity> activities = new ArrayList<>();
	

	public Concept() { }
	public Concept(String name) {this.name = name;}
	
	
	public void addActivity(Activity activity) {
		activity.setConcept(this);
		this.activities.add(activity);
	}
	public void addActivity(Integer index, Activity activity) {
		activity.setConcept(this);
		this.activities.add(index, activity);
	}
	private void removeActivity(Activity activity) {
		activity.setConcept(null);
		this.activities.remove(activity);
	}
	public void alterActivityPosition(Integer index, Activity activity) {
		this.removeActivity(activity);
		this.activities.add(index, activity);
	}
	public Integer getActivityOrder(Activity activity) {
		return this.activities.indexOf(activity);
	}
	public List<Activity> getActivities() {
		return Collections.unmodifiableList(this.activities);
	}
	
	
	public void addChild(Concept concept) {
		concept.setParent(this);
		concept.setNotion(null);
		this.children.add(concept);
	}
	public void addChild(Integer index, Concept concept) {
		concept.setParent(this);
		concept.setNotion(null);
		this.children.add(index, concept);
	}
	private void removeChild(Concept concept) {
		this.children.remove(concept);
	}
	public List<Concept> getChildren() {
		return Collections.unmodifiableList(this.children);
	}
	public Integer getConceptOrder(Concept concept) {
		return this.children.indexOf(concept);
	}
	public void alterChildOrder(Integer index, Concept concept) {
		this.removeChild(concept);
		this.addChild(index, concept);
	}
	
	
	public void addDependency(Concept concept) {
		this.dependencies.add(concept);
	}	
	public Set<Concept> getDependencies() {
		return Collections.unmodifiableSet(this.dependencies);
	}	
	public void removeDependency(Concept concept) {
		this.dependencies.remove(concept);
	}	
	public void addDependencies(Iterable<Concept> dependencies) {
		dependencies.forEach(this::addDependency);
	}
	
	
	protected void setParent(Concept concept) {
		this.parent = concept;
	}
	protected void setNotion(Notion notion) {
		this.notion = notion;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public Optional<Notion> getNotion() {
		return Optional.ofNullable(this.notion);
	}	
	public String getName() {
		return name;
	}	
	public Integer getId() {
		return id;
	}
	public Optional<Concept> getParent() {
		return Optional.ofNullable(this.parent);
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
		Concept other = (Concept) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}
}
