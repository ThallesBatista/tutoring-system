package br.com.sembous.studentmodule.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

interface ModelUtil {

//	static boolean isKnowledgePieceEquivalentToLearningPiece(KnowledgePiece kp, LearningPlanPiece lpp) {
//		return (kp.getStudent().equals(lpp.getStudent()) && 
//				kp.getType().equals(lpp.getType()) &&
//				kp.getExpertModuleId().equals(lpp.getExpertModuleId()));
//	}
	static boolean isKnowledgePieceEquivalentToLearningPieceWithoutStudent(KnowledgePiece kdp, LearningPlanPiece lpp) {
		return (kdp.getType().equals(lpp.getType()) && kdp.getExpertModuleId().equals(lpp.getExpertModuleId()));
	}
	
	static List<LearningPlanPiece> learningPlanOrderListFlatter(LearningPlanPiece pedagogicalObjective) {
		List<LearningPlanPiece> flatnedList = new ArrayList<>();
		flatnedList.add(pedagogicalObjective);
		learningPlanOrderedListFlatterHelper(flatnedList, pedagogicalObjective.getChildLPP());
		return flatnedList;
	}
	private static void learningPlanOrderedListFlatterHelper(List<LearningPlanPiece> flatnedList, List<LearningPlanPiece> toFlatList) {
		toFlatList.stream().forEachOrdered(c -> {
			flatnedList.add(c);
			if (!c.getChildLPP().isEmpty()) learningPlanOrderedListFlatterHelper(flatnedList, c.getChildLPP());
		});
	}
	static List<LearningPlanPiece> learningPlanActivitiesListOrdered(LearningPlanPiece g) {
		Set<KnowledgeType> activityTypes = KnowledgeType.getActivityTypes();
		return learningPlanOrderListFlatter(g).stream().filter(p -> {return activityTypes.contains(p.getType());}).collect(Collectors.toList());
	}
}
