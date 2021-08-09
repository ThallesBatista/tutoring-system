package br.com.sembous.studentmodule.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "knowledge_pieces")
public final class KnowledgePiece {

	@EmbeddedId
	private KnowledgePieceId id = new KnowledgePieceId();
	private Instant doneAt;
	private Double score = null;
	@Enumerated(EnumType.STRING)
	private KnowledgeStatus status;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "knowledgePiece")
	private List<Interaction> interactions = new ArrayList<>();
	
	
	public KnowledgePiece() { }
	public KnowledgePiece(Instant doneAt, Integer expertModuleId, KnowledgeType type, Double score, KnowledgeStatus status,
			Student student) { 
		this.id.setExpertModuleId(expertModuleId);
		this.id.setType(type);
		this.doneAt = doneAt;
		this.score = score;
		this.status = status;
		this.id.setStudent(student);
	}
	public KnowledgePiece(Instant doneAt, Integer expertModuleId, KnowledgeType type, Double score, KnowledgeStatus status,
			Student student, Interaction interaction) { 
		this.id.setExpertModuleId(expertModuleId);
		this.id.setType(type);
		this.doneAt = doneAt;
		this.score = score;
		this.status = status;
		this.id.setStudent(student);
		this.addInteraction(interaction);
	}
	
	
	void update(Double score, KnowledgeStatus status, Interaction interaction) {
		this.score = score;
		this.status = status;
		this.addInteraction(interaction);
	}
	void update(Double score, KnowledgeStatus status) { //para uso no LPP Manager
		this.score = score;
		this.status = status;
	}
	private void addInteraction(Interaction interaction) {
		this.interactions.add(interaction);
		interaction.setKnowledgePiece(this);
	}
	
	
	public Student getStudent() {
		return this.id.getStudent();
	}
	public Instant getDoneAt() {
		return doneAt;
	}
	public Integer getExpertModuleId() {
		return this.id.getExpertModuleId();
	}
	public KnowledgeType getType() {
		return this.id.getType();
	}
	public Double getScore() {
		return this.score;
	}
	public KnowledgeStatus getStatus() {
		return status;
	}
	public List<Interaction> getInteractions() {
		this.interactions.sort(Comparator.comparing(Interaction::getCreatedAt));
		return this.interactions;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getStudent() == null) ? 0 : this.getStudent().hashCode());
		result = prime * result + ((this.getExpertModuleId() == null) ? 0 : this.getExpertModuleId().hashCode());
		result = prime * result + ((this.getType() == null) ? 0 : this.getType().hashCode());
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
		KnowledgePiece other = (KnowledgePiece) obj;
		
		if (this.getStudent() == null) {
			if (other.getStudent() != null)
				return false;
		} else if (!this.getStudent().equals(other.getStudent()))
			return false;
		
		if (this.getExpertModuleId() == null) {
			if (other.getExpertModuleId() != null)
				return false;
		} else if (!this.getExpertModuleId().equals(other.getExpertModuleId()))
			return false;
		
		if (this.getType() == null) {
			if (other.getType() != null)
				return false;
		} else if (!this.getType().equals(other.getType()))
			return false;
		
		return true;
	}
}
