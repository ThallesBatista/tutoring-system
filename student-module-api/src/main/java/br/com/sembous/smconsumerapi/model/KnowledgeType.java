package br.com.sembous.smconsumerapi.model;

import java.util.Set;

public enum KnowledgeType {

	PEDAGOGICAL_OBJECTIVE,
	NOTION,
	CONCEPT,
	
	
	TEXT_ACTIVITY,
	VIDEO_ACTIVITY,
	INTERACTIVE_ACTIVITY,
	
	DIAGNOSTIC_EVALUATION_ACTIVITY,
	FORMATIVE_EVALUATION_ACTIVITY,
	SUMATIVE_EVALUATION_ACTIVITY;
	
	public static Set<KnowledgeType> getActivityTypes(){
		return Set.of(KnowledgeType.TEXT_ACTIVITY,
				KnowledgeType.VIDEO_ACTIVITY,
				KnowledgeType.INTERACTIVE_ACTIVITY,
				KnowledgeType.DIAGNOSTIC_EVALUATION_ACTIVITY,
				KnowledgeType.FORMATIVE_EVALUATION_ACTIVITY,
				KnowledgeType.SUMATIVE_EVALUATION_ACTIVITY);
	}
	
	public static Set<KnowledgeType> getEvaluationActivityTypes(){
		return Set.of(KnowledgeType.DIAGNOSTIC_EVALUATION_ACTIVITY,
				KnowledgeType.FORMATIVE_EVALUATION_ACTIVITY,
				KnowledgeType.SUMATIVE_EVALUATION_ACTIVITY);
	}
	
	public static Set<KnowledgeType> getNonEvaluationActivityTypes(){
		return Set.of(KnowledgeType.TEXT_ACTIVITY,
				KnowledgeType.VIDEO_ACTIVITY,
				KnowledgeType.INTERACTIVE_ACTIVITY);
	}
}
