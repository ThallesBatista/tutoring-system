package br.com.sembous.studentmodule.model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Embeddable
public final class LearningPlanManager {

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "student", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<LearningPlan> learningPlans = new HashSet<>();
	
//	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
//	private LearningPlan activeLearningPlan;
	
		
	
	void addLearningPlan(LearningPlan learningPlan, Student student) {		
		if (this.learningPlans.stream().anyMatch(p -> LearningPlan.compareWithoutId(p, learningPlan))) 
			throw new IllegalArgumentException("The student allready have this learning plan, maybe you must try to update it");
		
		learningPlan.setStudent(student);
		this.learningPlans.add(learningPlan);
	}
	Optional<LearningPlan> getLearningPlan(Integer id) {
		return this.learningPlans.stream().filter(lp -> lp.getId().equals(id)).findAny();
	}
	public void removeLearningPlan(Integer id) {
		this.learningPlans.removeIf(l -> l.getId().equals(id));		
	}
//	public void alterLearningActiveLearningPlan(Integer id) {
//		Optional<LearningPlan> optional = this.learningPlans.stream().filter(lp -> lp.getId().equals(id)).findAny();
//		if (optional.isEmpty()) return;
//		this.activeLearningPlan = optional.get();
//	}
	
	
	
//	public Optional<LearningPlan> getActiveLearningPlan() {
//		return Optional.ofNullable(this.activeLearningPlan);
//	}
}
