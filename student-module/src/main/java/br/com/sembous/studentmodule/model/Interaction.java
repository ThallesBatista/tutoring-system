package br.com.sembous.studentmodule.model;

import java.time.Instant;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "interactions")
public final class Interaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	private Student student;
	private Instant createdAt;
	@Enumerated(EnumType.STRING)
	private InteractionType type;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumns({
		@JoinColumn(name="`knowledge_piece_student`", referencedColumnName="`student_id`"),
		@JoinColumn(name="`knowledge_piece_emid`", referencedColumnName="`expert_module_id`"),
		@JoinColumn(name="`knowledge_piece_type`", referencedColumnName="`type`")
	})
	private KnowledgePiece knowledgePiece;
	
	
	public Interaction() { }
	
	public Interaction(Instant createdAt, InteractionType type) {
		this.createdAt = createdAt;
		this.type = type;
	}
	
	void setStudent(Student student) {
		this.student = student;
	}
	void setKnowledgePiece(KnowledgePiece knowledgePiece) {
		this.knowledgePiece = knowledgePiece;
	}
	
	
	public Long getId() {
		return id;
	}
	public Student getStudent() {
		return student;
	}
	public Instant getCreatedAt() {
		return createdAt;
	}
	public InteractionType getType() {
		return type;
	}	
	public Optional<KnowledgePiece> getKnowledgePiece(){
		return Optional.ofNullable(this.knowledgePiece);
	}
}
