package br.com.sembous.expertmodule.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity @Table(name = "activities")
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@Enumerated(EnumType.STRING)
	private ActivityType type;
	@Enumerated(EnumType.STRING)
	private ActivitySubtype subtype;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "activity")
	private Content content;
	@ManyToOne(fetch = FetchType.LAZY)
	private Concept concept;
	
	public Activity() {	}
	
	public Activity(String name, ActivityType type) {	
		this.name = name;
		this.type = type;
	}
	
	public void setSubtype(ActivitySubtype subtype) {
		this.subtype = subtype;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setType(ActivityType type) {
		this.type = type;
	}
	public void updateContent(Content content) {
		if (this.content != null) {
			this.content.update(content);
		} else {
			content.setActivity(this);
			this.content = content;
		}
	}
	
	protected void setConcept(Concept concept) {
		this.concept = concept;
	}
	
	
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public ActivityType getType() {
		return type;
	}
	public Content getContent() {
		return content;
	}
	public Concept getConcept() {
		return concept;
	}
	public ActivitySubtype getSubtype() {
		return subtype;
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
		Activity other = (Activity) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}
}
