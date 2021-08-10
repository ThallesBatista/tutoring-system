package br.com.sembous.tutoringmodule.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sembous.emconsumerapi.gateway.EntityGateway;
import br.com.sembous.emconsumerapi.gateway.ExpertModuleGateway;
import br.com.sembous.emconsumerapi.gateway.UntilValue;
import br.com.sembous.emconsumerapi.model.Activity;
import br.com.sembous.emconsumerapi.model.PedagogicalObjective;
import br.com.sembous.emconsumerapi.model.Topic;
import br.com.sembous.smconsumerapi.gateway.StudentModuleGateway;
import br.com.sembous.smconsumerapi.model.KnowledgeType;
import br.com.sembous.smconsumerapi.model.LearningPlanPiece;
import br.com.sembous.smconsumerapi.model.KnowledgeStatus;
import br.com.sembous.smconsumerapi.model.Student;
import br.com.sembous.tutoringmodule.QuickTest;
import br.com.sembous.tutoringmodule.service.util.EnumTranslater;
import br.com.sembous.tutoringmodule.service.util.LearningPlanGraphCreator;
import br.com.sembous.tutoringmodule.service.util.StaticContentJson;

@Service
public class LearningPlanService {

	private RestTemplate restTemplate = new RestTemplate();
	
	public void selectNewLearningPlan(Integer studentId, Integer objectiveId) {
		StudentModuleGateway smg = new StudentModuleGateway(restTemplate);	
		Optional<Student> optionalStudent = smg.getStudent(studentId);
		if (optionalStudent.isEmpty()) return;
		Student student = optionalStudent.get();
		
		LearningPlanGraphCreator lpgCreator = new LearningPlanGraphCreator(restTemplate);
		
		LearningPlanPiece lPGraph;
		try {
			lPGraph = lpgCreator.createFromExpertModule(objectiveId);
		} catch (IOException e) {
			return;
		}
		smg.update(student, lPGraph);
	}

	public Optional<Student> getLearningPlan(Integer studentId) {
		StudentModuleGateway smg = new StudentModuleGateway(restTemplate);
		return smg.get(studentId);
	}

	public Map<KnowledgeType, String> getKpt2Labels() {
		return EnumTranslater.getKpt2Labels();
	}

	public Optional<Topic> getTopicByPOId(Integer expertModuleId) {
		EntityGateway<PedagogicalObjective> pog = new ExpertModuleGateway().getPedagogicalObjectiveGateway(restTemplate);
		Optional<PedagogicalObjective> po = pog.getOne(expertModuleId, UntilValue.PEDAGOGICAL_OBJECTIVE);
		if (po.isEmpty()) return Optional.empty();
		return Optional.of(po.get().getTopic());
	}
	
	public Optional<JsonNode> getContentOfActivity(LearningPlanPiece activityLPP) {
		if (!KnowledgeType.getActivityTypes().contains(activityLPP.getType())) {
			new IllegalArgumentException("Object passed as argument is not an activity").printStackTrace();
			return Optional.empty();
		}
		
		Optional<Activity> optional = new ExpertModuleGateway().getActivityGateway(restTemplate).getOne(activityLPP.getExpertModuleId(), UntilValue.ACTIVIY);
		if (optional.isEmpty()) {
			new IllegalStateException("Learning Plan Piece activity not exists in Expert Module").printStackTrace();
			return Optional.empty();
		}
		
		Activity activity = optional.get();
		return Optional.ofNullable(activity.getContent());
	}

	public JsonNode newStudentContentJson() { //as duas proximas funções só mudam o nome, usar um método privado pra isso
		return this.getStaticJsonContent(StaticContentJson.NEW_STUDENT.getFilename());
	}

	public JsonNode noMoreActivitiesContentJson() {		
		return this.getStaticJsonContent(StaticContentJson.NO_MORE_ACTIVITIES.getFilename());
	}
	
	public JsonNode contentNotFoundJson() {		
		return this.getStaticJsonContent(StaticContentJson.CONTENT_NOT_FOUND.getFilename());
	}
	
	private JsonNode getStaticJsonContent(String fileName) {
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode json;
		try {
	        InputStream fis = QuickTest.class.getResourceAsStream(fileName);
			Reader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
		
			String jsonString = "";
			String line = br.readLine();
			while (line != null && !line.isEmpty()) {
				jsonString += line;
				line = br.readLine();
			}
			br.close();
			json = mapper.readTree(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
			json = null;
		}
		
		return json;
	}

	public void activityDone(Integer studentId, LearningPlanPiece currentActivity, Double score, KnowledgeStatus status) {
		LearningPlanPiece learningPlanPiece = new LearningPlanPiece(currentActivity.getType(), currentActivity.getExpertModuleId(), score, status);
		StudentModuleGateway smg = new StudentModuleGateway(restTemplate);		
		smg.activityDone(studentId, learningPlanPiece);
	}
}
