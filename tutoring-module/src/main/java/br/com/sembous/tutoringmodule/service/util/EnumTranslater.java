package br.com.sembous.tutoringmodule.service.util;

import java.util.HashMap;
import java.util.Map;

import br.com.sembous.emconsumerapi.model.ActivitySubtype;
import br.com.sembous.emconsumerapi.model.ActivityType;
import br.com.sembous.smconsumerapi.model.KnowledgeType;
import br.com.sembous.smconsumerapi.model.KnowledgeCategory;

public abstract class EnumTranslater {

	public static KnowledgeType activityType2KnowledgePieceType(ActivityType type) {
		return KnowledgeType.valueOf(type.toString()+"_ACTIVITY");
	}

	public static KnowledgeCategory activitySubtype2LearningPlanPieceCategory(ActivitySubtype subtype) {
		return KnowledgeCategory.valueOf(subtype.toString());
	}
	
	public static Map<KnowledgeType, String> getKpt2Labels() {
		Map<KnowledgeType, String> map = new HashMap<>();
		map.put(KnowledgeType.PEDAGOGICAL_OBJECTIVE, "Objetivo");
		map.put(KnowledgeType.NOTION, "Noção");
		map.put(KnowledgeType.CONCEPT, "Conceito");
		
		map.put(KnowledgeType.TEXT_ACTIVITY, "Leitura");
		map.put(KnowledgeType.VIDEO_ACTIVITY, "Vídeo");
		map.put(KnowledgeType.INTERACTIVE_ACTIVITY, "Atividade Interativa");
		
		map.put(KnowledgeType.DIAGNOSTIC_EVALUATION_ACTIVITY, "Avaliação Preliminar");
		map.put(KnowledgeType.FORMATIVE_EVALUATION_ACTIVITY, "Exercício");
		map.put(KnowledgeType.SUMATIVE_EVALUATION_ACTIVITY, "Avaliação");
		
		return map;
	}

}
