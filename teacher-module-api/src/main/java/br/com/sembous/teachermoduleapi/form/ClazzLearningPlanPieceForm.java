package br.com.sembous.teachermoduleapi.form;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.sembous.teachermoduleapi.model.ClazzLearningPlanPiece;

public class ClazzLearningPlanPieceForm {

	private List<ClazzLearningPlanPieceForm> childLPP = new ArrayList<>();
	private String type;
	private Integer expertModuleId;
	private String category;
	private String name;
	
	public ClazzLearningPlanPieceForm(ClazzLearningPlanPiece lPGraph) {
		this.childLPP = lPGraph.getChildLPP().stream().map(ClazzLearningPlanPieceForm::new).collect(Collectors.toList());
		this.type = lPGraph.getType().toString();
		this.expertModuleId = lPGraph.getExpertModuleId();
		this.category = lPGraph.getCategory().toString();
		this.name = lPGraph.getName();
	}

	public List<ClazzLearningPlanPieceForm> getChildLPP() {
		return childLPP;
	}
	public String getType() {
		return type;
	}
	public Integer getExpertModuleId() {
		return expertModuleId;
	}
	public String getCategory() {
		return category;
	}
	public String getName() {
		return name;
	}
}
