package br.com.sembous.tutoringmodule.controller;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
			Optional<Student> optional = learningPlanService.getStudentWithLearningPlansAndKnowledgeDone(user.getForeignId());
			if (optional.isEmpty()) throw new IllegalStateException("The user has the role of a student, but was not found as one");
			
			Student student = optional.get();
			model.addAttribute("student", student);
			return "dashboard/dashboard";
		} else if (userRoles.contains(RoleValue.ROLE_TEACHER)) {
			return "dashboard/teacherDashboard";
		} else throw new IllegalStateException("The user doesn't a page");
	}
}
