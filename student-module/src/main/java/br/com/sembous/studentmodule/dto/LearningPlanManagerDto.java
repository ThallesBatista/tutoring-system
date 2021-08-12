package br.com.sembous.studentmodule.dto;

import java.util.Set;
import java.util.stream.Collectors;

import br.com.sembous.studentmodule.model.LearningPlanManager;

public class LearningPlanManagerDto {

	private Set<LearningPlanSimpleDto> learningPlans;
	
	LearningPlanManagerDto(LearningPlanManager lpm) {
		this.learningPlans = lpm.getLearningPlans().stream().map(LearningPlanSimpleDto::new).collect(Collectors.toSet());
	}

	public Set<LearningPlanSimpleDto> getLearningPlans() {
		return learningPlans;
	}
}
