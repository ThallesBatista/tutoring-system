package br.com.sembous.teachermoduleapi.form;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.sembous.teachermoduleapi.model.ClazzLearningPlanPiece;
import br.com.sembous.teachermoduleapi.model.KnowledgeCategory;
import br.com.sembous.teachermoduleapi.model.KnowledgeType;

public class ClazzLearningPlanPieceForm {

	private List<ClazzLearningPlanPieceForm> childLPP = new ArrayList<>();
	private KnowledgeType type;
	private Integer expertModuleId;
	private KnowledgeCategory category;
	private String name;
	
	public ClazzLearningPlanPieceForm(ClazzLearningPlanPiece lPGraph) {
		this.childLPP = lPGraph.getChildLPP().stream().map(ClazzLearningPlanPieceForm::new).collect(Collectors.toList());
		this.type = lPGraph.getType();
		this.expertModuleId = lPGraph.getExpertModuleId();
		this.category = lPGraph.getCategory();
		this.name = lPGraph.getName();
	}

	public List<ClazzLearningPlanPieceForm> getChildLPP() {
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
}
