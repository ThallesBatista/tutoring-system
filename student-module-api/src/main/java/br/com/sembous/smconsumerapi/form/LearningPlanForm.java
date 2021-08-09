package br.com.sembous.smconsumerapi.form;

import java.util.List;
import java.util.stream.Collectors;

import br.com.sembous.smconsumerapi.model.LearningPlanPiece;

public class LearningPlanForm {
	
	private String type;
	private Integer expertModuleId;	
	private List<LearningPlanForm> childLPP;
	private String category;
	private String name;
	
	
	public LearningPlanForm(LearningPlanPiece lpp) {
		this.type = lpp.getType().toString();
		this.expertModuleId = lpp.getExpertModuleId();
		this.category = lpp.getCategory().toString();
		this.name = lpp.getName();
		
		if (lpp.getChildLPP().isEmpty()) this.childLPP = null;
		else this.childLPP = lpp.getChildLPP().stream().map(LearningPlanForm::new).collect(Collectors.toList());
	}
	

	public String getType() {
		return type;
	}
	public Integer getExpertModuleId() {
		return expertModuleId;
	}
	public List<LearningPlanForm> getChildLPP() {
		return childLPP;
	}
	public String getCategory() {
		return category;
	}
	public String getName() {
		return name;
	}
}
