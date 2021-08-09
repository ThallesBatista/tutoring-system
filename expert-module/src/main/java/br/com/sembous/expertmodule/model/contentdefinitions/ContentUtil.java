package br.com.sembous.expertmodule.model.contentdefinitions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;

public class ContentUtil {

	public static boolean contentValidator(JsonNode contents) {
		
		Set<String> validContentFieldNames = Set.of("type","content");
		Set<String> validContentTypes = Arrays.stream(ContentField.values()).map(ContentField::toString).collect(Collectors.toSet());
		if (!ContentUtil.fieldsValidator(validContentFieldNames, validContentTypes, contents.elements())) return false;
		
		Set<JsonNode> exerciseNodes = new HashSet<>();
		Iterator<JsonNode> elementsIterator = contents.elements();
		while (elementsIterator.hasNext()) {
			JsonNode content = elementsIterator.next();
			String dirtyType = content.get("type").toString();
			String type = dirtyType.substring(1,dirtyType.length()-1);
			if (ContentField.EXERCISES.toString().equals(type)) {
				exerciseNodes.add(content.get("content"));
			}
		}
		if (!exerciseNodes.isEmpty()) {
			Set<String> validExerciseFieldNames = Set.of("type","content");
			Set<String> validExerciseTypes = Arrays.stream(ExerciseField.values()).map(ExerciseField::toString).collect(Collectors.toSet());
			for (JsonNode exerciseNode : exerciseNodes) {
				if (!ContentUtil.fieldsValidator(validExerciseFieldNames, validExerciseTypes, exerciseNode.elements())) return false;
				
				Set<JsonNode> alternativeNodes = new HashSet<>();
				Iterator<JsonNode> elementsIterator2 = exerciseNode.elements();
				while (elementsIterator2.hasNext()) {
					JsonNode content = elementsIterator2.next();
					String dirtyType = content.get("type").toString();
					String type = dirtyType.substring(1,dirtyType.length()-1);
					if (ExerciseField.ALTERNATIVE.toString().equals(type)) {
						alternativeNodes.add(content.get("content"));
					}
				}
				if (!alternativeNodes.isEmpty()) {
					Set<String> validAlternativeFieldNames = Set.of("type","content");
					Set<String> validAlternativeTypes = Arrays.stream(AlternativeField.values()).map(AlternativeField::toString).collect(Collectors.toSet());
					for (JsonNode alternativeNode : alternativeNodes) {
						if (!ContentUtil.fieldsValidator(validAlternativeFieldNames, validAlternativeTypes, alternativeNode.elements())) return false;
					}
				}
			}			
		}		
		return true;
	}
	
	
	public static boolean fieldsValidator(Set<String> validFieldNames, Set<String> validContentTypes, Iterator<JsonNode> elements) {
		if (!elements.hasNext()) return false;	
		try {
			elements.forEachRemaining(e -> {
				Iterator<String> fieldNames = e.fieldNames();
				if(!iteratorElementsValidator(validFieldNames, validFieldNames, fieldNames)) throw new IllegalArgumentException();
				
				String dirtyType = e.get("type").toString();
				String type = dirtyType.substring(1,dirtyType.length()-1);
				if (!validContentTypes.contains(type)) throw new IllegalArgumentException();
			});
		} catch (IllegalArgumentException e) { return false; }

		return true;
	}
	
	
	private static <T> boolean iteratorElementsValidator(Set<T> validValues, Set<T> mandatoryValues, Iterator<T> iterator) {
		if (!iterator.hasNext()) return false;

		Set<T> iteratorSet = new HashSet<T>();
		iterator.forEachRemaining(iteratorSet::add);
		try {
			if (validValues != null) {
				iteratorSet.forEach(v -> {if (!validValues.contains(v)) throw new IllegalArgumentException();});
			}
		} catch (IllegalArgumentException e) { return false; }
		
		try {
			if (mandatoryValues!=null) {
				mandatoryValues.forEach(v -> {if (!iteratorSet.contains(v)) throw new IllegalArgumentException();});
			}
		} catch (IllegalArgumentException e) { return false; }
		
		return true;
	}

}
