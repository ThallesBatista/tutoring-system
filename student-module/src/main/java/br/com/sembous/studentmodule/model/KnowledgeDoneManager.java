package br.com.sembous.studentmodule.model;

import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Embeddable
public final class KnowledgeDoneManager {

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.student", cascade = CascadeType.PERSIST)
	private Set<KnowledgePiece> knowledgeDone = new HashSet<KnowledgePiece>();

	
	
	public void updateNewLearningPlan(LearningPlanPiece pedagogicalObjectivePiece) {
		List<LearningPlanPiece> flattnedPO = ModelUtil.learningPlanOrderListFlatter(pedagogicalObjectivePiece);
		flattnedPO.stream().forEach(lpp -> {
			Optional<KnowledgePiece> optional = this.knowledgeDone.stream()
					.filter(kdp -> {return ModelUtil.isKnowledgePieceEquivalentToLearningPieceWithoutStudent(kdp, lpp);})
				.findAny();
			if (optional.isPresent()) {
				lpp.updateStatus(optional.get().getStatus(), optional.get().getScore());
			}
		});		
	}
	public void remove(Set<LearningPlanPiece> piecesToRemove) {
		this.knowledgeDone.removeIf(k -> piecesToRemove.stream().anyMatch(l -> ModelUtil.isKnowledgePieceEquivalentToLearningPieceWithoutStudent(k,l)));
	}
	void update(Set<LearningPlanPiece> modifiedLPP, Student student) {
		modifiedLPP.stream().forEach(mLPP -> {
			Optional<KnowledgePiece> optional = this.knowledgeDone.stream()
					.filter(kd -> {return ModelUtil.isKnowledgePieceEquivalentToLearningPieceWithoutStudent(kd, mLPP);})
					.findAny();
			if (optional.isPresent()) {
				KnowledgePiece kdp = optional.get();
				Interaction interaction = null;
				if (KnowledgeType.getEvaluationActivityTypes().contains(kdp.getType())) 
					interaction = new Interaction(Instant.now(), InteractionType.getInteractionTypeFromKnowledgeDone(mLPP.getScore(), mLPP.getType()));
				else if (!mLPP.getStatus().equals(kdp.getStatus())) { //posteriormente podem ser pegas outras mudanças de status
					if (mLPP.getStatus().equals(KnowledgeStatus.DONE) && !kdp.getStatus().equals(KnowledgeStatus.DONE)) 
						interaction=new Interaction(Instant.now(),InteractionType.getInteractionTypeFromKnowledgeDone(mLPP.getScore(), mLPP.getType()));
				}
				if (interaction != null) {
					student.addInteraction(interaction);
					kdp.update(mLPP.getScore(), mLPP.getStatus(), interaction);
				}
				else kdp.update(mLPP.getScore(), mLPP.getStatus());
			} else {
				Interaction interaction = null;
				if (KnowledgeType.getEvaluationActivityTypes().contains(mLPP.getType()) || mLPP.getStatus().equals(KnowledgeStatus.DONE)) 
					interaction = new Interaction(Instant.now(), InteractionType.getInteractionTypeFromKnowledgeDone(mLPP.getScore(), mLPP.getType()));
				KnowledgePiece newKdp;
				if (interaction == null) newKdp = new KnowledgePiece(
						Instant.now(), mLPP.getExpertModuleId(), mLPP.getType(), mLPP.getScore(), mLPP.getStatus(), student);
				else {
					student.addInteraction(interaction);
					newKdp = new KnowledgePiece(Instant.now(), mLPP.getExpertModuleId(), mLPP.getType(), mLPP.getScore(), 
							mLPP.getStatus(), student, interaction);
				}
				this.knowledgeDone.add(newKdp);
			}		
		});
	}	
	
	
	
	public Set<KnowledgePiece> getKnowledgeDone() {
		return Collections.unmodifiableSet(this.knowledgeDone);
	}
	public Double getAvgScore() {
		return getAvgScoreForTypesSet(Set.of(KnowledgeType.DIAGNOSTIC_EVALUATION_ACTIVITY, 
				KnowledgeType.FORMATIVE_EVALUATION_ACTIVITY,
				KnowledgeType.SUMATIVE_EVALUATION_ACTIVITY));
	}
	public Double getAvgScoreOfLast(Integer n) {
		Set<KnowledgeType> types = KnowledgeType.getEvaluationActivityTypes();
		return this.knowledgeDone.stream()
			.filter(p -> types.contains(p.getType()))
			.sorted(Comparator.comparing(KnowledgePiece::getDoneAt, Comparator.reverseOrder()))
			.sequential()
			.limit(n)
			.mapToDouble(KnowledgePiece::getScore)
			.sum();
	}
	
	
	
	private Double getAvgScoreForTypesSet(Set<KnowledgeType> types) {
		return Double.valueOf(this.knowledgeDone.stream().filter(p -> types.contains(p.getType())).mapToDouble(p -> p.getScore()).sum());
	}
}
