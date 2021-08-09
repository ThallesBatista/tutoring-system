package br.com.sembous.smconsumerapi.model;

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
	
}
