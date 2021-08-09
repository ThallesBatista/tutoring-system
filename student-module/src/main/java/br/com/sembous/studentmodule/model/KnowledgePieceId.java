package br.com.sembous.studentmodule.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Embeddable
public class KnowledgePieceId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Student student;
	@Column(name = "expert_module_id")
	private Integer expertModuleId;
	@Enumerated(EnumType.STRING)
	private KnowledgeType type;
	
	
	public KnowledgePieceId() {	}
	public KnowledgePieceId(Student student, Integer expertModuleId, KnowledgeType type) {	
		this.student = student;
		this.expertModuleId = expertModuleId;
		this.type = type;
	}
	
	
	Student getStudent() {
		return student;
	}
	void setStudent(Student student) {
		this.student = student;
	}
	Integer getExpertModuleId() {
		return expertModuleId;
	}
	void setExpertModuleId(Integer expertModuleId) {
		this.expertModuleId = expertModuleId;
	}
	KnowledgeType getType() {
		return type;
	}
	void setType(KnowledgeType type) {
		this.type = type;
	}
}
