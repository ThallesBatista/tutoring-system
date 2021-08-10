package br.com.sembous.tutoringmodule.controller;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sembous.emconsumerapi.model.Topic;
import br.com.sembous.smconsumerapi.model.KnowledgePieceType;
import br.com.sembous.smconsumerapi.model.LearningPlanManager;
import br.com.sembous.smconsumerapi.model.Student;
import br.com.sembous.tutoringmodule.config.security.Role;
import br.com.sembous.tutoringmodule.config.security.RoleValue;
import br.com.sembous.tutoringmodule.config.security.User;
import br.com.sembous.tutoringmodule.service.LearningPlanService;

@Controller
@RequestMapping(path = "/dashboard")
public class DashboardController {
	
	@Autowired
	private LearningPlanService learningPlanService;
	
	@GetMapping
	public String get(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<RoleValue> userRoles = user.getRoles().stream().map(Role::getRole).collect(Collectors.toSet());
		if (userRoles.contains(RoleValue.ROLE_STUDENT)) {
			Optional<Student> optional = learningPlanService.getLearningPlan(user.getForeignId());
			if (optional.isEmpty()) throw new IllegalStateException("The user has the role of a student, but was not found as one");
			Student student = optional.get();
			model.addAttribute("student", student);
			
			Map<KnowledgePieceType, String> kpt2str = learningPlanService.getKpt2Labels();
			model.addAttribute("kpt2str", kpt2str);
			
			String topicName = null;
			if (student.getLearningPlanManager().isPresent()) {
				LearningPlanManager lpm = student.getLearningPlanManager().get();
				Optional<Topic> optionalTopic = learningPlanService.getTopicByPOId(lpm.getPedagogicalLPP().getExpertModuleId());
				if (optionalTopic.isEmpty()) throw new IllegalStateException("No topic for Pedagogical Objective");
				topicName = optionalTopic.get().getName();
			}
			model.addAttribute("topicName", topicName);
			
//			Optional<LearningPlanManager> optionalLPM = student.getLearningPlanManager();
//			if (optionalLPM.isEmpty()) model.addAttribute("lPP2EME",null);
//			else model.addAttribute("lPP2EME",learningPlanService.getLearningPlanPieceToExpertModuleEntityMap(optionalLPM.get()));
			
		}
		else throw new IllegalStateException("This view is only allowed for students, but the current user is not one");
		return "dashboard/index";
	}
}
