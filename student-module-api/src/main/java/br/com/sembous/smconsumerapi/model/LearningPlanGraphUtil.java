package br.com.sembous.smconsumerapi.model;

import java.util.ArrayList;
import java.util.List;

class LearningPlanGraphUtil {

	public static List<LearningPlanPiece> learningPlanOrderListFlatter(LearningPlanPiece pedagogicalLPP) {
		List<LearningPlanPiece> flatnedList = new ArrayList<>();
		flatnedList.add(pedagogicalLPP);
		LearningPlanGraphUtil.learningPlanOrderedListFlatterHelper(flatnedList, pedagogicalLPP.getChildLPP());
		return flatnedList;
	}
	private static void learningPlanOrderedListFlatterHelper(List<LearningPlanPiece> flatnedList, List<LearningPlanPiece> toFlatList) {
		toFlatList.stream().forEachOrdered(c -> {
			flatnedList.add(c);
			if (!c.getChildLPP().isEmpty()) LearningPlanGraphUtil.learningPlanOrderedListFlatterHelper(flatnedList, c.getChildLPP());
		});
	}
}
