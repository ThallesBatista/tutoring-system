package br.com.sembous.teachermodule.form;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import br.com.sembous.teachermodule.config.validation.ValueOfEnum;
import br.com.sembous.teachermodule.model.ClazzLearningPlanPiece;
import br.com.sembous.teachermodule.model.KnowledgeCategory;
import br.com.sembous.teachermodule.model.KnowledgeType;

public class ClazzLearningPlanPieceForm {

	private List<@Valid ClazzLearningPlanPieceForm> childLPP = new ArrayList<>();
	@NotNull @ValueOfEnum(enumClass = KnowledgeType.class)
	private KnowledgeType type;
	@NotNull
	private Integer expertModuleId;
	@NotNull @ValueOfEnum(enumClass = KnowledgeCategory.class)
	private KnowledgeCategory category;
	@NotNull @Length(max = 50)
	private String name;
	
	
	public ClazzLearningPlanPiece convert() {
		List<ClazzLearningPlanPiece> childLPPConverted = new ArrayList<>();
		childLPPConverted = this.childLPP.stream().map(ClazzLearningPlanPieceForm::convert).collect(Collectors.toList());
		return new ClazzLearningPlanPiece(childLPPConverted, type, expertModuleId, category, name);
	}
	
	
	public void setChildLPP(List<ClazzLearningPlanPieceForm> childLPP) {
		this.childLPP = childLPP;
	}
	public void setType(KnowledgeType type) {
		this.type = type;
	}
	public void setExpertModuleId(Integer expertModuleId) {
		this.expertModuleId = expertModuleId;
	}
	public void setCategory(KnowledgeCategory category) {
		this.category = category;
	}
	public void setName(String name) {
		this.name = name;
	}	
}
