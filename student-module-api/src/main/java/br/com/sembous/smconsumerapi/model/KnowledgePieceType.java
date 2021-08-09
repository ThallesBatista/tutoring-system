package br.com.sembous.smconsumerapi.model;

import java.util.Set;

public enum KnowledgePieceType {

	PEDAGOGICAL_OBJECTIVE,
	NOTION,
	CONCEPT,
	
	
	TEXT_ACTIVITY,
	VIDEO_ACTIVITY,
	INTERACTIVE_ACTIVITY,
	
	DIAGNOSTIC_EVALUATION_ACTIVITY,
	FORMATIVE_EVALUATION_ACTIVITY,
	SUMATIVE_EVALUATION_ACTIVITY;
	
	public static Set<KnowledgePieceType> getActivityTypes(){
		return Set.of(KnowledgePieceType.TEXT_ACTIVITY,
				KnowledgePieceType.VIDEO_ACTIVITY,
				KnowledgePieceType.INTERACTIVE_ACTIVITY,
				KnowledgePieceType.DIAGNOSTIC_EVALUATION_ACTIVITY,
				KnowledgePieceType.FORMATIVE_EVALUATION_ACTIVITY,
				KnowledgePieceType.SUMATIVE_EVALUATION_ACTIVITY);
	}
	
	public static Set<KnowledgePieceType> getEvaluationActivityTypes(){
		return Set.of(KnowledgePieceType.DIAGNOSTIC_EVALUATION_ACTIVITY,
				KnowledgePieceType.FORMATIVE_EVALUATION_ACTIVITY,
				KnowledgePieceType.SUMATIVE_EVALUATION_ACTIVITY);
	}
	
	public static Set<KnowledgePieceType> getNonEvaluationActivityTypes(){
		return Set.of(KnowledgePieceType.TEXT_ACTIVITY,
				KnowledgePieceType.VIDEO_ACTIVITY,
				KnowledgePieceType.INTERACTIVE_ACTIVITY);
	}
}
