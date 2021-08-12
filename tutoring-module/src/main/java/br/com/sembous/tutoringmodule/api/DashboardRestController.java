package br.com.sembous.tutoringmodule.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sembous.emconsumerapi.model.Topic;
import br.com.sembous.smconsumerapi.model.KnowledgeType;
import br.com.sembous.smconsumerapi.model.LearningPlan;
import br.com.sembous.smconsumerapi.model.Student;
import br.com.sembous.tutoringmodule.config.security.User;
import br.com.sembous.tutoringmodule.service.LearningPlanService;

@RestController
@RequestMapping(path="/api/dashboard")
public class DashboardRestController {
	
	@Autowired
	private LearningPlanService learningPlanService;	
	
	@GetMapping(path="/learningPlans")
	public ResponseEntity<List<LearningPlan>> getLearningPlans() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Student> optional = learningPlanService.getStudentWithLearningPlansAndKnowledgeDone(user.getForeignId());
		if (optional.isEmpty()) throw new IllegalStateException("The user has the role of a student, but was not found as one");
		
		Student student = optional.get();
//			model.addAttribute("student", student);
		
//			Map<KnowledgeType, String> kpt2str = learningPlanService.getKpt2Labels();
//			model.addAttribute("kpt2str", kpt2str);
		
		Set<LearningPlan> learningPlans = student.getLearningPlanManager().get().getLearningPlans();
		List<LearningPlan> learningPlansList = List.copyOf(learningPlans);
//			model.addAttribute("learningPlansList", learningPlansList);
		return ResponseEntity.ok(learningPlansList);
			
//			Map<LearningPlan, String> lp2topic = new HashMap<>();
//			for (LearningPlan learningPlan : learningPlansList) {
//				Optional<Topic> optionalTopic = learningPlanService.getTopicByPOId(learningPlan.getExpertModuleId());
//				String topicName;
//				if (optionalTopic.isPresent()) topicName = optionalTopic.get().getName();
//				else topicName = "";
//				lp2topic.put(learningPlan, topicName);
//			}
//			model.addAttribute("lp2topic", lp2topic);
	}
	
	@GetMapping(path="/learningPlan2TopicMap")
	public ResponseEntity<Map<Integer, String>> getLearningPlan2TopicMap() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Student> optional = learningPlanService.getStudentWithLearningPlansAndKnowledgeDone(user.getForeignId());
		if (optional.isEmpty()) throw new IllegalStateException("The user has the role of a student, but was not found as one");
		
		Student student = optional.get();			
		Set<LearningPlan> learningPlans = student.getLearningPlanManager().get().getLearningPlans();
		List<LearningPlan> learningPlansList = List.copyOf(learningPlans);
		
		Map<Integer, String> lp2topic = new HashMap<>();
		for (LearningPlan learningPlan : learningPlansList) {
			Optional<Topic> optionalTopic = learningPlanService.getTopicByPOId(learningPlan.getExpertModuleId());
			String topicName;
			if (optionalTopic.isPresent()) topicName = optionalTopic.get().getName();
			else topicName = "";
			lp2topic.put(learningPlan.getId(), topicName);
		}
		return ResponseEntity.ok(lp2topic);
	}
	
	@GetMapping(path="/learningPlan/{id}")
	public ResponseEntity<LearningPlan> getLearningPlanDetailed(@PathVariable(name = "id") Integer id) {
		Optional<LearningPlan> optional = learningPlanService.getLearningPlan(id);
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		LearningPlan learningPlan = optional.get();
		learningPlan.serializable();
		return ResponseEntity.ok(learningPlan);
	}
	
	@DeleteMapping(path="/learningPlan/{id}")
	public ResponseEntity<?> removeLearningPlan(@PathVariable(name = "id") Integer id) {
		learningPlanService.removeLearningPlan(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(path="/learningPlan/{id}")
	public ResponseEntity<?> updateCurrentLearningPlan(@PathVariable(name = "id") Integer id, HttpSession session) {
		Optional<LearningPlan> optional = learningPlanService.getLearningPlan(id);
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		LearningPlan learningPlan = optional.get();
		session.setAttribute("currentLearningPlan", learningPlan);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(path="/knowledgeType2NewLabel")
	public ResponseEntity<Map<KnowledgeType, String>> getLearningPlan2LabelMap() {
		Map<KnowledgeType, String> kpt2str = learningPlanService.getKpt2Labels();
		return ResponseEntity.ok(kpt2str);
	}

}
