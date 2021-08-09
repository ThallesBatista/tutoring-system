package br.com.sembous.smconsumerapi.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.sembous.smconsumerapi.model.KnowledgePieceType;
import br.com.sembous.smconsumerapi.model.LearningPlanPiece;
import br.com.sembous.smconsumerapi.model.LearningPlanPieceCategory;
import br.com.sembous.smconsumerapi.model.LearningPlanPieceStatus;

public class LearningPlanDto {
	
	private String type;
	private Integer expertModuleId;
	private List<LearningPlanDto> childLPP;
	private String category;
	private String status;
	private Double score;
	private String name;
	
	public LearningPlanPiece convert() {
		List<LearningPlanPiece> childLPPConverted;
		if (childLPP!=null) childLPPConverted = childLPP.stream().map(LearningPlanDto::convert).collect(Collectors.toList()); //PRECISO DESSA CHECAGEM?
		else childLPPConverted = new ArrayList<>();
		return new LearningPlanPiece(KnowledgePieceType.valueOf(type), expertModuleId, childLPPConverted, 
				LearningPlanPieceCategory.valueOf(category), LearningPlanPieceStatus.valueOf(status), score, name);
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setExpertModuleId(Integer expertModuleId) {
		this.expertModuleId = expertModuleId;
	}
	public void setChildLPP(List<LearningPlanDto> childLPP) {
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
