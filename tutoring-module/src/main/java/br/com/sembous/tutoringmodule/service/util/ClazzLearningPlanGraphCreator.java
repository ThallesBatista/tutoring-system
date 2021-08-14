package br.com.sembous.tutoringmodule.service.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.client.RestTemplate;

import br.com.sembous.emconsumerapi.gateway.EntityGateway;
import br.com.sembous.emconsumerapi.gateway.ExpertModuleGateway;
import br.com.sembous.emconsumerapi.gateway.UntilValue;
import br.com.sembous.emconsumerapi.model.Activity;
import br.com.sembous.emconsumerapi.model.Concept;
import br.com.sembous.emconsumerapi.model.Notion;
import br.com.sembous.emconsumerapi.model.PedagogicalObjective;
import br.com.sembous.teachermoduleapi.model.KnowledgeCategory;
import br.com.sembous.teachermoduleapi.model.KnowledgeType;
import br.com.sembous.teachermoduleapi.model.ClazzLearningPlanPiece;

public class ClazzLearningPlanGraphCreator {

	private RestTemplate restTemplate;

	public ClazzLearningPlanGraphCreator(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	

	public ClazzLearningPlanPiece createFromExpertModule(Integer objectiveId) throws IOException{
		ExpertModuleGateway emg = new ExpertModuleGateway();
		EntityGateway<PedagogicalObjective> pog = emg.getPedagogicalObjectiveGateway(restTemplate);
		Optional<PedagogicalObjective> optional = pog.getOne(objectiveId, UntilValue.ACTIVIY);
		if (optional.isEmpty()) throw new IOException("There is no PedagogicalObjective with this id");
		
		PedagogicalObjective po = optional.get();
		
		return this.lPPGraphBuilder(po);
	}
	
	
	
	private ClazzLearningPlanPiece lPPGraphBuilder(PedagogicalObjective po) {
		List<ClazzLearningPlanPiece> notionsLPP = po.getNotions().stream().map(n -> this.lPPGraphBuilder(n)).collect(Collectors.toList());
		return new ClazzLearningPlanPiece(KnowledgeType.PEDAGOGICAL_OBJECTIVE, po.getId(), notionsLPP, KnowledgeCategory.ESSENTIAL, po.getName());
	}
	private ClazzLearningPlanPiece lPPGraphBuilder(Notion notion) {
		List<ClazzLearningPlanPiece> conceptsLPP = notion.getConcepts().stream().map(c -> this.lPPGraphBuilder(c)).collect(Collectors.toList());
		return new ClazzLearningPlanPiece(KnowledgeType.NOTION, notion.getId(), conceptsLPP, KnowledgeCategory.ESSENTIAL, notion.getName());
	}
	private ClazzLearningPlanPiece lPPGraphBuilder(Concept c) {
		List<ClazzLearningPlanPiece> activitiesLPP = c.getActivities().stream().map(a -> this.lPPGraphBuilder(a)).collect(Collectors.toList());
		List<ClazzLearningPlanPiece> childConceptsLPP = c.getChildren().stream().map(f -> this.lPPGraphBuilder(f)).collect(Collectors.toList());
		
		List<ClazzLearningPlanPiece> conceptChildrenLPP = new ArrayList<>();
		activitiesLPP.forEach(conceptChildrenLPP::add);
		childConceptsLPP.forEach(conceptChildrenLPP::add);
		
		return new ClazzLearningPlanPiece(KnowledgeType.CONCEPT, c.getId(), conceptChildrenLPP, KnowledgeCategory.ESSENTIAL, c.getName());
	}
	private ClazzLearningPlanPiece lPPGraphBuilder(Activity a) {		
		KnowledgeType type = KnowledgeType.valueOf(EnumTranslater.activityType2KnowledgePieceType(a.getType()).toString());
		KnowledgeCategory category = KnowledgeCategory.valueOf(EnumTranslater.activitySubtype2LearningPlanPieceCategory(a.getSubtype()).toString());
		return new ClazzLearningPlanPiece(type, a.getId(), null, category, a.getName());
	}
}
