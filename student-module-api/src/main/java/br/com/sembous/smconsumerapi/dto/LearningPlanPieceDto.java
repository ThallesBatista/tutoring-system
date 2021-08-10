package br.com.sembous.smconsumerapi.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.sembous.smconsumerapi.model.KnowledgeCategory;
import br.com.sembous.smconsumerapi.model.KnowledgeStatus;
import br.com.sembous.smconsumerapi.model.KnowledgeType;
import br.com.sembous.smconsumerapi.model.LearningPlanPiece;

public class LearningPlanPieceDto {

	private Long id;
	private String type;
	private Integer expertModuleId;
//	private LearningPlanPieceDto fatherLPP;
	private List<LearningPlanPieceDto> childLPP;
	private String category; 
	private String status;
	private Double score;
	private String name;
	
	
	public LearningPlanPiece convert() {
		List<LearningPlanPiece> childLPPConverted;
		if (childLPP!=null) childLPPConverted = childLPP.stream().map(LearningPlanPieceDto::convert).collect(Collectors.toList()); //PRECISO DESSA CHECAGEM?
		else childLPPConverted = new ArrayList<>();
		return new LearningPlanPiece(id, KnowledgeType.valueOf(type), expertModuleId, childLPPConverted, 
				KnowledgeCategory.valueOf(category), KnowledgeStatus.valueOf(status), score, name);
	}

	
	public void setId(Long id) {
		this.id = id;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setExpertModuleId(Integer expertModuleId) {
		this.expertModuleId = expertModuleId;
	}
//	public void setFatherLPP(LearningPlanPieceDto fatherLPP) {
//		this.fatherLPP = fatherLPP;
//	}
	public void setChildLPP(List<LearningPlanPieceDto> childLPP) {
		this.childLPP = childLPP;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public void setName(String name) {
		this.name = name;
	}
}
