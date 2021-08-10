package br.com.sembous.tutoringmodule.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sembous.tutoringmodule.config.security.Role;
import br.com.sembous.tutoringmodule.config.security.RoleValue;
import br.com.sembous.tutoringmodule.config.security.User;

@Controller
@RequestMapping(path="/learn")
public class LearnController {
	
	@Autowired
	ObjectMapper mapper;
	
	@GetMapping
	public String get(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<RoleValue> userRoles = user.getRoles().stream().map(Role::getRole).collect(Collectors.toSet());
		if (userRoles.contains(RoleValue.ROLE_STUDENT)) {
			return "learn/index";						
		}
		else throw new IllegalStateException("This view is only allowed for students, but the current user is not one");
	}
}
