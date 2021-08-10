package br.com.sembous.tutoringmodule.service.util;

import java.util.HashMap;
import java.util.Map;

import br.com.sembous.emconsumerapi.model.ActivitySubtype;
import br.com.sembous.emconsumerapi.model.ActivityType;
import br.com.sembous.smconsumerapi.model.KnowledgePieceType;
import br.com.sembous.smconsumerapi.model.LearningPlanPieceCategory;

public abstract class EnumTranslater {

	public static KnowledgePieceType activityType2KnowledgePieceType(ActivityType type) {
		return KnowledgePieceType.valueOf(type.toString()+"_ACTIVITY");
	}

	public static LearningPlanPieceCategory activitySubtype2LearningPlanPieceCategory(ActivitySubtype subtype) {
		return LearningPlanPieceCategory.valueOf(subtype.toString());
	}
	
	public static Map<KnowledgePieceType, String> getKpt2Labels() {
		Map<KnowledgePieceType, String> map = new HashMap<>();
		map.put(KnowledgePieceType.PEDAGOGICAL_OBJECTIVE, "Objetivo");
		map.put(KnowledgePieceType.NOTION, "Noção");
		map.put(KnowledgePieceType.CONCEPT, "Conceito");
		
		map.put(KnowledgePieceType.TEXT_ACTIVITY, "Leitura");
		map.put(KnowledgePieceType.VIDEO_ACTIVITY, "Vídeo");
		map.put(KnowledgePieceType.INTERACTIVE_ACTIVITY, "Atividade Interativa");
		
		map.put(KnowledgePieceType.DIAGNOSTIC_EVALUATION_ACTIVITY, "Avaliação Preliminar");
		map.put(KnowledgePieceType.FORMATIVE_EVALUATION_ACTIVITY, "Exercício");
		map.put(KnowledgePieceType.SUMATIVE_EVALUATION_ACTIVITY, "Avaliação");
		
		return map;
	}

}
