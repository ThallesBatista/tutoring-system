package br.com.sembous.studentmodule.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name = "learning_plan_pieces")
public final class LearningPlanPiece {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@ManyToOne(optional = false)
//	private LearningPlan learningPlan;
	
	@Enumerated(EnumType.STRING)
	private KnowledgeType type;
	
	private Integer expertModuleId;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private LearningPlanPiece father = null;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "father", orphanRemoval = true)
	@OrderColumn(name = "`order`")
	private List<LearningPlanPiece> childLPP = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	private KnowledgeCategory category; //todos os conceitos são essenciais, mas nem todas as atividades são
	
	@Enumerated(EnumType.STRING)
	private KnowledgeStatus status = KnowledgeStatus.TO_DO;
	
	private Double score = Double.valueOf(0);
	
	private String name;
	
	
	
	public LearningPlanPiece() { }
	public LearningPlanPiece(KnowledgeType type, Integer expertModuleId,List<LearningPlanPiece> childLPP, 
			KnowledgeCategory knowledgeCategory, String name) {
		this.type = type;
		this.expertModuleId = expertModuleId;
		if (childLPP!=null) childLPP.stream().forEach(this::addChild);
		this.category = knowledgeCategory;
		this.name = name;
	}
	public LearningPlanPiece(KnowledgeType type, Integer expertModuleId, Double score, KnowledgeStatus status) {
		this.type = type;
		this.expertModuleId = expertModuleId;
		this.score = score;
		this.status = status;
	}
	
	

//	void setLearningPlan(LearningPlan learningPlan) {
//		this.learningPlan = learningPlan;	
//	}
	void setFatherLPP(LearningPlanPiece fatherLPP) {
		this.father = fatherLPP;
	}
	Boolean updateStatus(KnowledgeStatus newStatus, Double newScore) {
		if (this.status.equals(newStatus) && this.score.equals(newScore)) {
			return Boolean.FALSE;
		}
		this.status = newStatus;
		this.score = newScore;
		return Boolean.TRUE;	
	}


//	public LearningPlan getLearningPlan() {
//		return this.learningPlan;
//	}
	public KnowledgeType getType() {
		return this.type;
	}
	public Integer getExpertModuleId() {
		return this.expertModuleId;
	}
	public Long getId() {
		return id;
	}	
	public Optional<LearningPlanPiece> getFatherLPP() {
		return Optional.ofNullable(this.father);
	}
	public List<LearningPlanPiece> getChildLPP() {
		return Collections.unmodifiableList(childLPP);
	}
	public KnowledgeCategory getCategory() {
		return category;
	}
	public KnowledgeStatus getStatus() {
		return status;
	}
	public Double getScore() {
		return score;
	}
	public String getName() {
		return name;
	}
	public Double getProgress() {
		List<LearningPlanPiece> flatLP = ModelUtil.learningPlanOrderListFlatter(this).stream()
				.filter(p -> {return KnowledgeType.getActivityTypes().contains(p.getType());})
				.collect(Collectors.toList());
		if (flatLP.isEmpty()) return Double.valueOf(1);
		long totalLPP = flatLP.stream().count();
		long doneLPP = flatLP.stream().filter(p -> p.getStatus().equals(KnowledgeStatus.DONE)).count();
		Double progress = Double.valueOf(Double.valueOf(doneLPP)/Double.valueOf(totalLPP));
		return progress;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
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
		LearningPlanPiece other = (LearningPlanPiece) obj;
		
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		
		return true;
	}
	
	
	
	private void addChild(LearningPlanPiece child) {
		child.setFatherLPP(this);
		this.childLPP.add(child);
	}
	
	
	
	static Boolean compareWithoutId(LearningPlanPiece p1, LearningPlanPiece p2) {		
		return (p1.getExpertModuleId().equals(p2.expertModuleId) && p1.getType().equals(p2.getType()));
	}
//	static Boolean compareWithoutStudent(LearningPlanPiece p1, LearningPlanPiece p2) {
//		if (p1 == p2) return Boolean.TRUE;
//		if (p1 == null && p2 != null) return Boolean.FALSE;
//		if (!p1.getType().equals(p2.getType())) return Boolean.FALSE;
//		if (!p1.getExpertModuleId().equals(p2.getExpertModuleId())) return Boolean.FALSE;
//		
//		return Boolean.TRUE;
//	}
}
