package br.com.sembous.smconsumerapi.model;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class LearningPlanManager {
	
	private Instant learningPlanUpdateAt;
	private LearningPlanPiece pedagogicalLPP;

	public LearningPlanManager(Instant learningPlanUpdateAt, LearningPlanPiece pedagogicalLPP, Student student) {
		this.learningPlanUpdateAt = learningPlanUpdateAt;
		this.pedagogicalLPP = pedagogicalLPP;
		
		List<LearningPlanPiece> flatnedList = LearningPlanGraphUtil.learningPlanOrderListFlatter(pedagogicalLPP);
		flatnedList.forEach(p -> p.setStudent(student));
	}
	
	
	public List<LearningPlanPiece> getLearningPlanPiecesList() {
		return LearningPlanGraphUtil.learningPlanOrderListFlatter(this.pedagogicalLPP);
	}
	public Double getLearningPlanAvgScore() {
		Double score = this.pedagogicalLPP.getScore();
		return score;
	}
	public Double getLearningPlanProgress() {
		Double progress = this.pedagogicalLPP.getProgress();
		return progress;
	}
	public Optional<LearningPlanPiece> getNextActivity() {
		Set<KnowledgePieceType> activityTypes = KnowledgePieceType.getActivityTypes();
		return this.getLearningPlanPiecesList().stream()
				.filter(p -> activityTypes.contains(p.getType()))
				.filter(a -> !a.getStatus().equals(LearningPlanPieceStatus.DONE))
				.findFirst();
	}
	
	
	public Instant getLearningPlanUpdateAt() {
		return learningPlanUpdateAt;
	}
	public LearningPlanPiece getPedagogicalLPP() {
		return pedagogicalLPP;
	}
}
