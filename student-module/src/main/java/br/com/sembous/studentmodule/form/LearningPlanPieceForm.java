package br.com.sembous.studentmodule.form;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.sembous.studentmodule.config.validation.ValueOfEnum;
import br.com.sembous.studentmodule.model.KnowledgeType;
import br.com.sembous.studentmodule.model.LearningPlanPiece;
import br.com.sembous.studentmodule.model.KnowledgeCategory;

public class LearningPlanPieceForm {

	@NotNull @ValueOfEnum(enumClass = KnowledgeType.class)
	private String type;	
	@NotNull
	private Integer expertModuleId;	
	private List<@Valid LearningPlanPieceForm> childLPP;	
	@NotNull @ValueOfEnum(enumClass = KnowledgeCategory.class)
	private String category;	
	@NotNull
	private String name;
	
	
	public LearningPlanPiece convert() {
		List<LearningPlanPiece> childLPPConverted = new ArrayList<>();
		if (this.childLPP!=null) childLPPConverted = this.childLPP.stream().map(LearningPlanPieceForm::convert).collect(Collectors.toList());
		return new LearningPlanPiece(KnowledgeType.valueOf(type), expertModuleId, childLPPConverted, 
				KnowledgeCategory.valueOf(category), name);
	}
	
	
	public void setType(String type) {
		this.type = type;
	}
	public void setExpertModuleId(Integer expertModuleId) {
		this.expertModuleId = expertModuleId;
	}
	public void setChildLPP(List<LearningPlanPieceForm> childLPP) {
		this.childLPP = childLPP;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
