package br.com.sembous.studentmodule.form;

import javax.validation.constraints.NotNull;

import br.com.sembous.studentmodule.config.validation.ValueOfEnum;
import br.com.sembous.studentmodule.model.KnowledgeType;
import br.com.sembous.studentmodule.model.LearningPlanPiece;
import br.com.sembous.studentmodule.model.KnowledgeStatus;

public class LearningPlanPieceDoneForm {

	@NotNull @ValueOfEnum(enumClass = KnowledgeType.class)
	private String type;	
	@NotNull
	private Integer expertModuleId;
	private Double score = Double.valueOf(0);
	@NotNull @ValueOfEnum(enumClass = KnowledgeStatus.class)
	private String status;
	
	public LearningPlanPiece convert() {
		return new LearningPlanPiece(KnowledgeType.valueOf(type), expertModuleId, score, KnowledgeStatus.valueOf(status));
	}

	public void setType(String type) {
		this.type = type;
	}
	public void setExpertModuleId(Integer expertModuleId) {
		this.expertModuleId = expertModuleId;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public void setStatus(String status) {
		this.status = status;
	}	

}
