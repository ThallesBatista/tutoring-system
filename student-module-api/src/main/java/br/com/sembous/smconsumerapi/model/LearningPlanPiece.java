package br.com.sembous.smconsumerapi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LearningPlanPiece {
	
	private Long id;
	private KnowledgeType type;
	private Integer expertModuleId;
	private LearningPlanPiece fatherLPP = null;
	private List<LearningPlanPiece> childLPP = new ArrayList<>();
	private KnowledgeCategory category;
	private KnowledgeStatus status = KnowledgeStatus.TO_DO;
	private Double score = Double.valueOf(0);
	private String name;

// PELO MENOS OS IDS ACHO QUE TENHO QUE COLOCAR NOS CONSTRUTORES
	
	public LearningPlanPiece(KnowledgeType type, Integer expertModuleId, Double score, KnowledgeStatus status) {
		
		this.type = type;
		this.expertModuleId = expertModuleId;
		this.score = score;
		this.status = status;
	}
	public LearningPlanPiece(KnowledgeType type, Integer expertModuleId, List<LearningPlanPiece> childLPP, 
			KnowledgeCategory learningPlanPieceCategory, String name) {
		this.type = type;
		this.expertModuleId = expertModuleId;
		if (childLPP!=null) childLPP.stream().forEach(this::addChild);
		this.category = learningPlanPieceCategory;
		this.name = name;
	}
//	public LearningPlanPiece(KnowledgeType type, Integer expertModuleId,
//			List<LearningPlanPiece> childLPP, KnowledgeCategory learningPlanPieceCategory,
//			KnowledgeStatus status, Double score, String name) {
//		
//		this.type = type;
//		this.expertModuleId = expertModuleId;
//		if (childLPP!=null) childLPP.stream().forEach(this::addChild);
//		this.category = learningPlanPieceCategory;
//		this.score = score;
//		this.status = status;
//		this.name = name;
//	}	
	public LearningPlanPiece(Long id, KnowledgeType type, Integer expertModuleId, List<LearningPlanPiece> childLPPConverted, 
			KnowledgeCategory category, KnowledgeStatus status,	Double score, String name) {
		this.id = id;
		this.type = type;
		this.expertModuleId = expertModuleId;
		childLPPConverted.forEach(this::addChild);
		this.category = category;
		this.status = status;
		this.score = score;
		this.name = name;
	}
	private void addChild(LearningPlanPiece child) {
		child.setFatherLPP(this);
		this.childLPP.add(child);
	}


	void setFatherLPP(LearningPlanPiece fatherLPP) {
		this.fatherLPP = fatherLPP;
	}
// TALVEZ PRECISE AINDA, NO FLUXO DE UPDATE
//	void updateStatus(LearningPlanPieceStatus newStatus, Double newScore) {
//		this.status = newStatus;
//		this.score = newScore;
//	}
	
	
	public Double getProgress() {
		List<LearningPlanPiece> flatLP = LearningPlanGraphUtil.learningPlanOrderListFlatter(this).stream()
				.filter(p -> {return KnowledgeType.getActivityTypes().contains(p.getType());})
				.collect(Collectors.toList());
		if (flatLP.isEmpty()) return Double.valueOf(1);
		long totalLPP = flatLP.stream().count();
		long doneLPP = flatLP.stream().filter(p -> p.getStatus().equals(KnowledgeStatus.DONE)).count();
		Double progress = Double.valueOf(Double.valueOf(doneLPP)/Double.valueOf(totalLPP));
		return progress;
	}	
	public Long getId() {
		return this.id;
	}
	public KnowledgeType getType() {
		return type;
	}
	public Integer getExpertModuleId() {
		return expertModuleId;
	}
	public LearningPlanPiece getFatherLPP() {
		return fatherLPP;
	}
	public List<LearningPlanPiece> getChildLPP() {
		return childLPP;
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
	public void serializable() {
		this.fatherLPP = null;
		this.childLPP.forEach(c -> c.serializable());		
	}
}
