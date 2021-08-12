package br.com.sembous.tutoringmodule.service;

import org.springframework.web.client.RestTemplate;

//@Service
public class UpdateEngine {

//	private RestTemplate restTemplate;
	
	public UpdateEngine(RestTemplate restTemplate) {
//		this.restTemplate = restTemplate;
	}
	
//	public void update(Integer studentId, Integer objectiveId) {
//		StudentGateway smg = StudentModuleGateway.getStudentGateway(restTemplate);	
//		Optional<Student> optionalStudent = smg.get(studentId, Boolean.FALSE, Boolean.FALSE);
//		if (optionalStudent.isEmpty()) return;
//		
//		LearningPlanPiece lPGraph;
//		try {
//			lPGraph = this.lPPCreatorFromExpertModule(objectiveId);
//		} catch (Exception e) {
//			return;
//		}
//		
//		LearningPlanGateway lpg = StudentModuleGateway.getLearningPlanGateway(restTemplate);	
//		lpg.updateLearningPlan(studentId, lPGraph);
//	}

//	private LearningPlanPiece lPPCreatorFromExpertModule(Integer objectiveId) throws Exception {
//		ExpertModuleGateway emg = new ExpertModuleGateway();
//		EntityGateway<PedagogicalObjective> pog = emg.getPedagogicalObjectiveGateway(restTemplate);
//		Optional<PedagogicalObjective> optional = pog.getOne(objectiveId, UntilValue.ACTIVIY);
//		if (optional.isEmpty()) throw new Exception("There is no PedagogicalObjective with this id");
//		
//		PedagogicalObjective po = optional.get();
//		
//		return this.lPPGraphBuilder(po);
//	}

//	private LearningPlanPiece lPPGraphBuilder(PedagogicalObjective po) {
////		List<LearningPlanPiece> notionsLPP = po.getNotions().stream().map(n -> this.lPPGraphBuilder(n)).collect(Collectors.toList());
////		return new LearningPlanPiece(KnowledgePieceType.PEDAGOGICAL_OBJECTIVE, po.getId(), notionsLPP, LearningPlanPieceCategory.ESSENTIAL);
//		return null;
//	}
	
	//////////////////////////////////  DAQUI PRA CIMA ERA NOVO  ////////////////////////////////////////////

//	private LearningPlanPiece lPPGraphBuilder(Notion notion) {
//		List<LearningPlanPiece> conceptsLPP = notion.getConcepts().stream().map(c -> this.lPPGraphBuilder(c)).collect(Collectors.toList());
//		return new LearningPlanPiece(KnowledgePieceType.NOTION, notion.getId(), conceptsLPP, LearningPlanPieceCategory.ESSENTIAL);
//	}
//
//	private LearningPlanPiece lPPGraphBuilder(Concept c) {
//		List<LearningPlanPiece> activitiesLPP = c.getActivities().stream().map(a -> this.lPPGraphBuilder(a)).collect(Collectors.toList());
//		List<LearningPlanPiece> childConceptsLPP = c.getChildren().stream().map(f -> this.lPPGraphBuilder(f)).collect(Collectors.toList());
//		
//		List<LearningPlanPiece> conceptChildrenLPP = new ArrayList<>();
//		activitiesLPP.forEach(conceptChildrenLPP::add);
//		childConceptsLPP.forEach(conceptChildrenLPP::add);
//		
//		return new LearningPlanPiece(KnowledgePieceType.CONCEPT, c.getId(), conceptChildrenLPP, LearningPlanPieceCategory.ESSENTIAL);
//	}
//
//	private LearningPlanPiece lPPGraphBuilder(Activity a) {
//		KnowledgePieceType type = this.activityType2KnowledgePieceType(a.getType());
//		LearningPlanPieceCategory category = this.activitySubtype2LearningPlanPieceCategory(a.getSubtype());
//		return new LearningPlanPiece(type, a.getId(), null, category);
//	}
//
//	private LearningPlanPieceCategory activitySubtype2LearningPlanPieceCategory(ActivitySubtype subtype) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	private KnowledgePieceType activityType2KnowledgePieceType(ActivityType type) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	

//	public void updateLearningPlan(Student student, Integer pedagogicalObjectiveId, RestTemplate restTemplate) {
//		ExpertModuleGateway emg = new ExpertModuleGateway(restTemplate);
//		Optional<PedagogicalObjective> optional = emg.getPedagogicalObjective(pedagogicalObjectiveId, Boolean.TRUE);
//		
//		if (optional.isEmpty()) return;
//		
//		List<Activity> activitiesList = optional.get().getNotions().stream()
//			.flatMap(n -> {
//				List<Concept> flatnedConcepts = new ArrayList<>();
//				this.conceptsFlatterRecursive(flatnedConcepts, n.getConcepts());
//				return flatnedConcepts.stream();
//			})
//			.flatMap(c -> {return c.getActivities().stream();})
//			.collect(Collectors.toList());
//		
//		activitiesList.stream().map(Activity::getName).forEach(System.out::println);
//		
//		
//		if (student.getPreferences() == null) {
//			StudentModuleGateway smg = new StudentModuleGateway(restTemplate);
//			smg.getStudentPreferences(student);
//		}
//			
//		this.adaptActivitiesList(student, activitiesList);
//		
//		activitiesList.stream().map(Activity::getName).forEach(System.out::println);
//		
//		StudentModuleGateway smg = new StudentModuleGateway(restTemplate);
//		smg.updateStudentLearningPlan(student);
//		smg.getStudentLearningPlan(student);
//	}
//
//	private void conceptsFlatterRecursive(List<Concept> flatnedConcepts, List<Concept> toFlatConcepts) {
//		toFlatConcepts.stream().forEach(c -> {
//			flatnedConcepts.add(c);
//			if (c.getChildren() != null) this.conceptsFlatterRecursive(flatnedConcepts, c.getChildren());
//		});
//	}
//	
//	private void adaptActivitiesList(Student student, List<Activity> activitiesList) {
//		List<Activity> adaptedActivities = new LinkedList<>();
//		activitiesList.forEach(adaptedActivities::add);
//		
//		switch (student.getPreferences().getNeedsMoreTime()) {
//		case YES:
//			activitiesList = activitiesList.stream()
//				.filter(a -> {return !(a.getSubtype().equals(ActivitySubtype.CHALLENGE) || a.getSubtype().equals(ActivitySubtype.COMPLEMENT));})
//				.collect(Collectors.toList());
//			break;
//		case NO:
//			activitiesList = activitiesList.stream()
//				.filter(a -> {return !(a.getSubtype().equals(ActivitySubtype.REINFORCEMENT));})
//				.collect(Collectors.toList());
//			break;
//		case NEUTRAL_OR_UNDETECTED:
//			activitiesList = activitiesList.stream()
//				.filter(a -> {return !(a.getSubtype().equals(ActivitySubtype.REINFORCEMENT) || a.getSubtype().equals(ActivitySubtype.CHALLENGE));})
//				.collect(Collectors.toList());
//			break;
//		}
//		
//		
//		switch (student.getPreferences().getLikesVideos()) {
//		case YES:
//			break;
//		case NO:
//			activitiesList = activitiesList.stream()
//				.filter(a -> {return !(a.getType().equals(ActivityType.VIDEO) && !a.getSubtype().equals(ActivitySubtype.ESSENTIAL));})
//				.collect(Collectors.toList());
//			break;
//		case NEUTRAL_OR_UNDETECTED:
//			break;
//		}
//		
//		
//		switch (student.getPreferences().getLikesExercises()) {
//		case YES:
//			break;
//		case NO:
//			if (!student.getPreferences().getNeedsMoreTime().equals(PreferenceType.YES)) {
//				activitiesList = activitiesList.stream()
//						.filter(a -> {
//							Set<ActivityType> exerciseTypes = Set.of(ActivityType.DIAGNOSTIC_EVALUATION, ActivityType.FORMATIVE_EVALUATION, ActivityType.SUMATIVE_EVALUATION);
//							if (exerciseTypes.contains(a.getType())) {
//								if (!a.getSubtype().equals(ActivitySubtype.ESSENTIAL)) return false;
//							}
//							return true;
//						})
//						.collect(Collectors.toList());
//			}
//			break;
//		case NEUTRAL_OR_UNDETECTED:
//			break;
//		}
//	}
}
