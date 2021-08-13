package br.com.sembous.teachermoduleapi.model;

import java.util.ArrayList;
import java.util.List;

public class ClazzLearningPlanPiece {
	
	private Integer id;
	private ClazzLearningPlanPiece father = null;
	private List<ClazzLearningPlanPiece> childLPP = new ArrayList<>();
	private KnowledgeType type;
	private Integer expertModuleId;
	private KnowledgeCategory category;
	private String name;
	
	public ClazzLearningPlanPiece(KnowledgeType type, Integer expertModuleId, List<ClazzLearningPlanPiece> childLPP, 
			KnowledgeCategory learningPlanPieceCategory, String name) {
		this.type = type;
		this.expertModuleId = expertModuleId;
		if (childLPP!=null) childLPP.stream().forEach(this::addChild);
		this.category = learningPlanPieceCategory;
		this.name = name;
	}
	
	void setFather(ClazzLearningPlanPiece father) {
		this.father = father;
	}
	
	public Integer getId() {
		return id;
	}
	public ClazzLearningPlanPiece getFather() {
		return father;
	}
	public List<ClazzLearningPlanPiece> getChildLPP() {
		return childLPP;
	}
	public KnowledgeType getType() {
		return type;
	}
	public Integer getExpertModuleId() {
		return expertModuleId;
	}
	public KnowledgeCategory getCategory() {
		return category;
	}
	public String getName() {
		return name;
	}
	
	
	
	private void addChild(ClazzLearningPlanPiece childLPP) {
		childLPP.setFather(this);
		this.childLPP.add(childLPP);
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
		ClazzLearningPlanPiece other = (ClazzLearningPlanPiece) obj;
		
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		
		return true;
	}
}
