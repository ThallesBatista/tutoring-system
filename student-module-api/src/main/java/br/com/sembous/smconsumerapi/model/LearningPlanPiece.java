package br.com.sembous.smconsumerapi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LearningPlanPiece {
	
	private Student student;
	private KnowledgePieceType type;
	private Integer expertModuleId;
	
	private LearningPlanPiece fatherLPP = null;
	private List<LearningPlanPiece> childLPP = new ArrayList<>();
	private LearningPlanPieceCategory category;
	private LearningPlanPieceStatus status = LearningPlanPieceStatus.TO_DO;
	private Double score = Double.valueOf(0);
	private String name;
	
	public LearningPlanPiece(KnowledgePieceType type, Integer expertModuleId, Double score, LearningPlanPieceStatus status) {
		
		this.type = type;
		this.expertModuleId = expertModuleId;
		this.score = score;
		this.status = status;
	}
	public LearningPlanPiece(KnowledgePieceType type, Integer expertModuleId, List<LearningPlanPiece> childLPP, 
			LearningPlanPieceCategory learningPlanPieceCategory, String name) {
		
		this.type = type;
		this.expertModuleId = expertModuleId;
		if (childLPP!=null) childLPP.stream().forEach(this::addChild);
		this.category = learningPlanPieceCategory;
		this.name = name;
	}
	public LearningPlanPiece(KnowledgePieceType type, Integer expertModuleId,
			List<LearningPlanPiece> childLPP, LearningPlanPieceCategory learningPlanPieceCategory,
			LearningPlanPieceStatus status, Double score, String name) {
		
		this.type = type;
		this.expertModuleId = expertModuleId;
		if (childLPP!=null) childLPP.stream().forEach(this::addChild);
		this.category = learningPlanPieceCategory;
		this.score = score;
		this.status = status;
		this.name = name;
	}
	private void addChild(LearningPlanPiece child) {
		child.setFatherLPP(this);
		this.childLPP.add(child);
	}
	
	
	void setFatherLPP(LearningPlanPiece fatherLPP) {
		this.fatherLPP = fatherLPP;
	}
	void setStudent(Student student) {
		this.student = student;
	}
//	void updateStatus(LearningPlanPieceStatus newStatus, Double newScore) {
//		this.status = newStatus;
//		this.score = newScore;
//	}
	public Double getProgress() {
		List<LearningPlanPiece> flatLP = LearningPlanGraphUtil.learningPlanOrderListFlatter(this).stream()
				.filter(p -> {return KnowledgePieceType.getActivityTypes().contains(p.getType());})
				.collect(Collectors.toList());
		if (flatLP.isEmpty()) return Double.valueOf(1);
//		flatLP.remove(this);
		long totalLPP = flatLP.stream().count();
		long doneLPP = flatLP.stream().filter(p -> p.getStatus().equals(LearningPlanPieceStatus.DONE)).count();
		Double progress = Double.valueOf(Double.valueOf(doneLPP)/Double.valueOf(totalLPP));
		return progress;
	}
	
	
	public Student getStudent() {
		return student;
	}
	public KnowledgePieceType getType() {
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
	public LearningPlanPieceCategory getCategory() {
		return category;
	}
	public LearningPlanPieceStatus getStatus() {
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
		result = prime * result + ((this.getStudent() == null) ? 0 : this.getStudent().hashCode());
		result = prime * result + ((this.getType() == null) ? 0 : this.getType().hashCode());
		result = prime * result + ((this.getExpertModuleId() == null) ? 0 : this.getExpertModuleId().hashCode());
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
		
		if (this.getStudent() == null) {
			if (other.getStudent() != null)
				return false;
		} else if (!this.getStudent().equals(other.getStudent()))
			return false;
		
		if (this.getType() == null) {
			if (other.getType() != null)
				return false;
		} else if (!this.getType().equals(other.getType()))
			return false;
		
		if (this.getExpertModuleId() == null) {
			if (other.getExpertModuleId() != null)
				return false;
		} else if (!this.getExpertModuleId().equals(other.getExpertModuleId()))
			return false;
		
		return true;
	}	
	
}
