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
import br.com.sembous.smconsumerapi.model.KnowledgePieceType;
import br.com.sembous.smconsumerapi.model.LearningPlanPiece;
import br.com.sembous.smconsumerapi.model.LearningPlanPieceCategory;

public class LearningPlanGraphCreator {

	private RestTemplate restTemplate;

	public LearningPlanGraphCreator(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	

	public LearningPlanPiece createFromExpertModule(Integer objectiveId) throws IOException{
		ExpertModuleGateway emg = new ExpertModuleGateway();
		EntityGateway<PedagogicalObjective> pog = emg.getPedagogicalObjectiveGateway(restTemplate);
		Optional<PedagogicalObjective> optional = pog.getOne(objectiveId, UntilValue.ACTIVIY);
		if (optional.isEmpty()) throw new IOException("There is no PedagogicalObjective with this id");
		
		PedagogicalObjective po = optional.get();
		
		return this.lPPGraphBuilder(po);
	}
	
	
	
	private LearningPlanPiece lPPGraphBuilder(PedagogicalObjective po) {
		List<LearningPlanPiece> notionsLPP = po.getNotions().stream().map(n -> this.lPPGraphBuilder(n)).collect(Collectors.toList());
		return new LearningPlanPiece(KnowledgePieceType.PEDAGOGICAL_OBJECTIVE, po.getId(), notionsLPP, LearningPlanPieceCategory.ESSENTIAL, po.getName());
	}
	private LearningPlanPiece lPPGraphBuilder(Notion notion) {
		List<LearningPlanPiece> conceptsLPP = notion.getConcepts().stream().map(c -> this.lPPGraphBuilder(c)).collect(Collectors.toList());
		return new LearningPlanPiece(KnowledgePieceType.NOTION, notion.getId(), conceptsLPP, LearningPlanPieceCategory.ESSENTIAL, notion.getName());
	}
	private LearningPlanPiece lPPGraphBuilder(Concept c) {
		List<LearningPlanPiece> activitiesLPP = c.getActivities().stream().map(a -> this.lPPGraphBuilder(a)).collect(Collectors.toList());
		List<LearningPlanPiece> childConceptsLPP = c.getChildren().stream().map(f -> this.lPPGraphBuilder(f)).collect(Collectors.toList());
		
		List<LearningPlanPiece> conceptChildrenLPP = new ArrayList<>();
		activitiesLPP.forEach(conceptChildrenLPP::add);
		childConceptsLPP.forEach(conceptChildrenLPP::add);
		
		return new LearningPlanPiece(KnowledgePieceType.CONCEPT, c.getId(), conceptChildrenLPP, LearningPlanPieceCategory.ESSENTIAL, c.getName());
	}
	private LearningPlanPiece lPPGraphBuilder(Activity a) {		
		KnowledgePieceType type = EnumTranslater.activityType2KnowledgePieceType(a.getType());
		LearningPlanPieceCategory category = EnumTranslater.activitySubtype2LearningPlanPieceCategory(a.getSubtype());
		return new LearningPlanPiece(type, a.getId(), null, category, a.getName());
	}
}
