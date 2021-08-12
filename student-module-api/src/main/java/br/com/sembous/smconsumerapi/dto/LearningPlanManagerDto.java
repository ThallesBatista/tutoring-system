package br.com.sembous.smconsumerapi.dto;

import java.util.Set;
import java.util.stream.Collectors;

import br.com.sembous.smconsumerapi.model.LearningPlanManager;

public class LearningPlanManagerDto {

	private Set<LearningPlanSimpleDto> learningPlans;
	
	LearningPlanManager convert() {
		return new LearningPlanManager(learningPlans.stream().map(LearningPlanSimpleDto::convert).collect(Collectors.toSet()));
	}
	
	public void setLearningPlans(Set<LearningPlanSimpleDto> learningPlans) {
		this.learningPlans = learningPlans;
	}	
}
