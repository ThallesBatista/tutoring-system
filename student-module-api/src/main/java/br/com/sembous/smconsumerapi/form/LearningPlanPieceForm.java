package br.com.sembous.smconsumerapi.form;

import java.util.List;
import java.util.stream.Collectors;

import br.com.sembous.smconsumerapi.model.LearningPlanPiece;

public class LearningPlanPieceForm {

	private String type;	
	private Integer expertModuleId;	
	private List<LearningPlanPieceForm> childLPP;
	private String category;
	private String name;
	
	public LearningPlanPieceForm(LearningPlanPiece piece) {
		this.type = piece.getType().toString();
		this.expertModuleId = piece.getExpertModuleId();
		this.childLPP = piece.getChildLPP().stream().map(LearningPlanPieceForm::new).collect(Collectors.toList());
		this.category = piece.getCategory().toString();
		this.name = piece.getName();
	}

	public String getType() {
		return type;
	}
	public Integer getExpertModuleId() {
		return expertModuleId;
	}
	public List<LearningPlanPieceForm> getChildLPP() {
		return childLPP;
	}
	public String getCategory() {
		return category;
	}
	public String getName() {
		return name;
	}
}
