package br.com.sembous.tutoringmodule.api;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sembous.smconsumerapi.model.LearningPlanManager;
import br.com.sembous.smconsumerapi.model.LearningPlanPiece;
import br.com.sembous.smconsumerapi.model.KnowledgeStatus;
import br.com.sembous.smconsumerapi.model.Student;
import br.com.sembous.tutoringmodule.config.security.Role;
import br.com.sembous.tutoringmodule.config.security.RoleValue;
import br.com.sembous.tutoringmodule.config.security.User;
import br.com.sembous.tutoringmodule.service.LearningPlanService;
import br.com.sembous.tutoringmodule.service.TutoringService;

@RestController
@SessionAttributes(names = {"responses", "currentActivity"})
@RequestMapping(path="/api/learn")
public class LearnRestController {
	
	@Autowired
	private LearningPlanService learningPlanService;
	
	@Autowired
	private TutoringService tutoringService;

	@GetMapping(path="/content")
	public ResponseEntity<JsonNode> getContent(Model model) throws JsonMappingException, JsonProcessingException {
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<RoleValue> userRoles = user.getRoles().stream().map(Role::getRole).collect(Collectors.toSet());
		if (userRoles.contains(RoleValue.ROLE_STUDENT)) {
			Optional<Student> optional = learningPlanService.getLearningPlan(user.getForeignId());
			if (optional.isEmpty()) throw new IllegalStateException("The user has the role of a student, but was not found as one");
			
			Student student = optional.get();			
			Optional<LearningPlanManager> optionalLPM = student.getLearningPlanManager();
			if (optionalLPM.isEmpty()) return ResponseEntity.ok(learningPlanService.newStudentContentJson());
			
			LearningPlanManager lpm = optionalLPM.get();			
			Optional<LearningPlanPiece> optionalActivity = lpm.getNextActivity();
			if (optionalActivity.isEmpty()) return ResponseEntity.ok(learningPlanService.noMoreActivitiesContentJson());
			
			LearningPlanPiece activity = optionalActivity.get();
			model.addAttribute("currentActivity", activity);
			Optional<JsonNode> optionalContent = learningPlanService.getContentOfActivity(activity);
			if (optionalContent.isEmpty()) return ResponseEntity.ok(learningPlanService.contentNotFoundJson());
			
			JsonNode rawContentJson = optionalContent.get();
			JsonNode contentJson = tutoringService.prepareContentJsonToView(rawContentJson.get("content"), student.getId());
			
			return ResponseEntity.ok(contentJson);
		}
		else throw new IllegalStateException("This view is only allowed for students, but the current user is not one");	
	}
	
	@GetMapping(path="/responses")
	public ResponseEntity<JsonNode> getResponses(@ModelAttribute("responses") JsonNode responses) {		
		return ResponseEntity.ok(responses);
	}
	
	@PutMapping(path="/responses")
	public ResponseEntity<?> updateResponses(@RequestBody JsonNode newResponses, Model model) {
		model.addAttribute("responses",newResponses);		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(path="/finishActivity")
	public String finishActivity(Model model, ObjectMapper mapper) { // comportamento só deve ser chamado se a atividade for do tipo evaluation;		
		Double score = tutoringService.calculeScore((JsonNode)model.getAttribute("responses"));
		
		LearningPlanPiece currentActivity = (LearningPlanPiece) model.getAttribute("currentActivity");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
		KnowledgeStatus status = tutoringService.getActivityStatus(user.getForeignId(), currentActivity, score);
		
		learningPlanService.activityDone(user.getForeignId(), currentActivity, score, status);
		
		model.addAttribute("responses",this.responses(mapper));
		return "dashboard";
	}
	
	@ModelAttribute("responses")
	public JsonNode responses(ObjectMapper mapper) {
		JsonNode json = null;
		try {
			json = mapper.readTree("[]");
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
}
