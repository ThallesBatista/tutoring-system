package br.com.sembous.tutoringmodule.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sembous.tutoringmodule.config.security.Role;
import br.com.sembous.tutoringmodule.config.security.RoleValue;
import br.com.sembous.tutoringmodule.config.security.User;

@Controller
@RequestMapping("/content")
public class ContentController {

	@GetMapping
	public String get() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal.getClass().equals(User.class)) {
			User user = (User) principal;
			Set<RoleValue> userRoles = user.getRoles().stream().map(Role::getRole).collect(Collectors.toSet());
			if (userRoles.contains(RoleValue.ROLE_TEACHER)) return "learningPlans/teacherContent";
		} 
		
		return "learningPlans/content";
	}
}
