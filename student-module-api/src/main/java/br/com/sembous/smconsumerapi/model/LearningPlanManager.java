package br.com.sembous.smconsumerapi.model;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public class LearningPlanManager {
	
	private Set<LearningPlan> learningPlans;

	
//	public LearningPlanManager(Set<LearningPlan> learningPlans) {
//		this.learningPlans = learningPlans;
//	}
	
	
	public Optional<LearningPlan> getLearningPlan(Integer id) {
		return this.learningPlans.stream().filter(lp -> lp.getId().equals(id)).findAny();
	}	
	public Set<LearningPlan> getLearningPlans() {
		return Collections.unmodifiableSet(this.learningPlans);
	}
	public void removeLearningPlan(Integer id) {
		this.learningPlans.removeIf(l -> l.getId().equals(id));		
	}
}
