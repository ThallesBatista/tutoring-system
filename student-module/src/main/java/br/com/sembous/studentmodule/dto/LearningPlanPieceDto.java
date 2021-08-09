package br.com.sembous.studentmodule.dto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.sembous.studentmodule.model.LearningPlanPiece;

public class LearningPlanPieceDto {

	private Long id;
	private String type;
	private Integer expertModuleId;
	private LearningPlanPieceDto fatherLPP = null;
	private List<LearningPlanPieceDto> childLPP;
	private String category; 
	private String status;
	private Double score;
	private String name;
	
	public LearningPlanPieceDto(LearningPlanPiece learningPlanGraph) {
		this.id = learningPlanGraph.getId();
		this.type = learningPlanGraph.getType().toString();
		this.expertModuleId = learningPlanGraph.getExpertModuleId();
		Optional<LearningPlanPiece> optional = learningPlanGraph.getFatherLPP();
		if (optional.isPresent()) this.fatherLPP = new LearningPlanPieceDto(optional.get());
		this.childLPP = learningPlanGraph.getChildLPP().stream().map(LearningPlanPieceDto::new).collect(Collectors.toList());
		this.category = learningPlanGraph.getCategory().toString();
		this.status = learningPlanGraph.getStatus().toString();
		this.score = learningPlanGraph.getScore();
		this.name = learningPlanGraph.getName();
	}

	public Long getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public Integer getExpertModuleId() {
		return expertModuleId;
	}
	public LearningPlanPieceDto getFatherLPP() {
		return fatherLPP;
	}
	public List<LearningPlanPieceDto> getChildLPP() {
		return childLPP;
	}
	public String getCategory() {
		return category;
	}
	public String getStatus() {
		return status;
	}
	public Double getScore() {
		return score;
	}
	public String getName() {
		return name;
	}
}
