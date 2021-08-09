package br.com.sembous.studentmodule.model;

public enum InteractionType {
	
	ACCEPTED_OPTIONAL_EXERCISE,
	REFUSED_OPTIONAL_EXERCISE,
	ACCEPTED_CHALLENGE,
	REFUSED_CHALLENGE,
	
	SKIPPED_VIDEO,
	WATCHED_VIDEO,
	SKIPPED_OPTIONAL_VIDEO,
	WATCHED_OPTIONAL_VIDEO,
	
	ASKED_THE_CHATBOT,
	ASKED_PER_TEXT,
	SKIPPED_CHATBOT_FEEDBACK,
	COMPLY_CHATBOT_FEEDBACK,
	
	VERY_MISSED_EVALUATION,
	MISSED_EVALUATION,
	RIGHT_EVALUATION,
	VERY_RIGHT_EVALUATION,
	
	PEDAGOGICAL_OBJECTIVE_DONE,
	NOTION_DONE,
	CONCEPT_DONE,
	ACTIVITY_DONE;
	
	static InteractionType getInteractionTypeFromKnowledgeDone(Double score, KnowledgeType type) {
		if (type.equals(KnowledgeType.PEDAGOGICAL_OBJECTIVE)) return InteractionType.PEDAGOGICAL_OBJECTIVE_DONE;
		if (type.equals(KnowledgeType.NOTION)) return InteractionType.NOTION_DONE;
		if (type.equals(KnowledgeType.CONCEPT)) return InteractionType.CONCEPT_DONE;
		
		if (KnowledgeType.getActivityTypes().contains(type) && !KnowledgeType.getEvaluationActivityTypes().contains(type)) 
			return InteractionType.ACTIVITY_DONE;
		
		if (score.compareTo(Double.valueOf(90))>0) return InteractionType.VERY_RIGHT_EVALUATION;
		if (score.compareTo(Double.valueOf(70))>0) return InteractionType.RIGHT_EVALUATION;
		if (score.compareTo(Double.valueOf(40))>0) return InteractionType.MISSED_EVALUATION;
		return InteractionType.VERY_MISSED_EVALUATION;
	}
}
