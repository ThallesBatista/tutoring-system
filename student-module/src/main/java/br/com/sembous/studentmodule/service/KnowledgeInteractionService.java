//package br.com.sembous.studentmodule.service;
//
//import java.time.Instant;
//import java.util.Optional;
//
//import org.springframework.stereotype.Service;
//
//import br.com.sembous.studentmodule.model.Interaction;
//import br.com.sembous.studentmodule.model.InteractionType;
//import br.com.sembous.studentmodule.model.KnowledgePiece;
//
//@Service
//public class KnowledgeInteractionService {
//
//	public Optional<Interaction> createCorrespondentInteraction(KnowledgePiece piece) {
//		Interaction interaction = null;
//		switch (piece.getType()) {
//		case TEXT_ACTIVITY:
//			interaction = new Interaction(piece.getDoneAt(), InteractionType.ACTIVITY_DONE);
//			break;
//		case INTERACTIVE_ACTIVITY:
//			interaction = new Interaction(piece.getDoneAt(), InteractionType.ACTIVITY_DONE);
//			break;
//		case VIDEO_ACTIVITY:
//			interaction = new Interaction(piece.getDoneAt(), InteractionType.ACTIVITY_DONE);
//			break;
//		case CONCEPT:
//			interaction = new Interaction(piece.getDoneAt(), InteractionType.CONCEPT_DONE);
//			break;
//		case NOTION:
//			interaction = new Interaction(piece.getDoneAt(), InteractionType.NOTION_DONE);
//			break;
//		case PEDAGOGICAL_OBJECTIVE:
//			interaction = new Interaction(piece.getDoneAt(), InteractionType.PEDAGOGICAL_OBJECTIVE_DONE);
//			break;
//		case DIAGNOSTIC_EVALUATION_ACTIVITY:
//			interaction = this.interactionOfEvaluationFromScore(piece.getDoneAt(), piece.getScore());
//			break;
//		case FORMATIVE_EVALUATION_ACTIVITY:
//			interaction = this.interactionOfEvaluationFromScore(piece.getDoneAt(), piece.getScore());
//			break;
//		case SUMATIVE_EVALUATION_ACTIVITY:
//			interaction = this.interactionOfEvaluationFromScore(piece.getDoneAt(), piece.getScore());
//			break;
//		default:
//			throw new IllegalStateException("unexpected KnowledgeType");
//		}
//		
//		return Optional.ofNullable(interaction);
//	}
//
//	private Interaction interactionOfEvaluationFromScore(Instant doneAt, Double score) {
//		try {
//			if (score >= 9) return new Interaction(doneAt, InteractionType.VERY_RIGHT_EVALUATION);
//			else if (score >= 7) return new Interaction(doneAt, InteractionType.RIGHT_EVALUATION);
//			else if (score >= 4) return new Interaction(doneAt, InteractionType.MISSED_EVALUATION);
//			else return new Interaction(doneAt, InteractionType.VERY_MISSED_EVALUATION);
//		} catch (NullPointerException e) {
//			throw new IllegalStateException("Evaluation without score");
//		}
//	}
//}
