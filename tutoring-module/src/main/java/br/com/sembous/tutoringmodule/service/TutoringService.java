package br.com.sembous.tutoringmodule.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.sembous.smconsumerapi.gateway.StudentModuleGateway;
import br.com.sembous.smconsumerapi.model.Interaction;
import br.com.sembous.smconsumerapi.model.KnowledgeType;
import br.com.sembous.smconsumerapi.model.LearningPlanPiece;
import br.com.sembous.smconsumerapi.model.KnowledgeStatus;
import br.com.sembous.smconsumerapi.model.PreferenceType;
import br.com.sembous.smconsumerapi.model.Student;

@Service
public class TutoringService {

	private RestTemplate restTemplate = new RestTemplate();
	
	public JsonNode prepareContentJsonToView(JsonNode rawContentJson, Integer studentId) {
		
		StudentModuleGateway smg = new StudentModuleGateway(restTemplate);
		Optional<Student> optionalStudent = smg.getStudentWithPreferences(studentId); //melhor sobrecarregar o método getStudent
		if (optionalStudent.isEmpty()) {
			new IllegalArgumentException("No Student found with studentId = " + studentId.toString()).printStackTrace();
			return rawContentJson;
		}
		Student student = optionalStudent.get();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode content;
		try {
			content = mapper.readTree(rawContentJson.toPrettyString());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return rawContentJson;
		}
		
		JsonNode exercises = content.get("exercises");
		if (exercises != null) {
			this.setContentTip(content.get("exercises"), student);
		}		
		
		return content;
	}

	private void setContentTip(JsonNode exercises, Student student) {
		
		Set<String> tipsToRemove = Set.of("easy_tip", "medium_tip", "hard_tip"); //vir de um enum no expert module
		String tipToPreserve;
		PreferenceType needsMoreTime = student.getPreferences().get().getNeedsMoreTime();
		if (needsMoreTime.equals(PreferenceType.YES)) tipToPreserve = "easy_tip";
		else if (needsMoreTime.equals(PreferenceType.NEUTRAL_OR_UNDETECTED)) tipToPreserve = "medium_tip";
		else tipToPreserve = "hard_tip";
		
		Iterator<JsonNode> elementsIterator = exercises.elements();
		elementsIterator.forEachRemaining(ex -> {
			((ObjectNode) ex).set("tip", ex.get(tipToPreserve));
			tipsToRemove.forEach(t -> {
				((ObjectNode) ex).remove(t);
			});
		});
		
	}

	public Double calculeScore(JsonNode responses) {
		if (responses.isEmpty()) return Double.valueOf(0);
		
		Double sum = Double.valueOf(0);
		Double weight = Double.valueOf(0);
		Iterator<JsonNode> elements = responses.elements();
		while (elements.hasNext()) {
			JsonNode next = elements.next();
			String s = next.get("score").toString();
			String w = next.get("weight").toString();
			sum += Double.valueOf(s) * Double.valueOf(w);
			weight += Double.valueOf(next.get("weight").toString());
		}
		return 100*(sum/weight);
	}

	public KnowledgeStatus getActivityStatus(Integer foreignId, LearningPlanPiece currentActivity, Double score) {
		if (!KnowledgeType.getEvaluationActivityTypes().contains(currentActivity.getType())) return KnowledgeStatus.DONE;
		if (score >= 70) return KnowledgeStatus.DONE;
		StudentModuleGateway smg = new StudentModuleGateway(restTemplate);
		List<Interaction> interactions = smg.getInteractionWithLearningPlanPiece(foreignId, currentActivity);
		if (interactions.size() >= 3) return KnowledgeStatus.BLOCKED;
		return KnowledgeStatus.TO_DO;
	}
}
