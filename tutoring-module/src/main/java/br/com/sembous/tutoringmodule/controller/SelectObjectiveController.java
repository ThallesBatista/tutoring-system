package br.com.sembous.tutoringmodule.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.sembous.tutoringmodule.config.security.Role;
import br.com.sembous.tutoringmodule.config.security.RoleValue;
import br.com.sembous.tutoringmodule.config.security.User;
import br.com.sembous.tutoringmodule.service.LearningPlanService;

@RestController
@RequestMapping(path = "/selectObjective")
public class SelectObjectiveController {
	
	@Autowired
	private LearningPlanService learningPlanService;
	
	@PostMapping
	public String newObjective(@RequestBody JsonNode objectiveIdJson) {
		Integer objectiveId = Integer.valueOf(0);
		try {objectiveId = Integer.valueOf(objectiveIdJson.get("objectiveId").toString());} 
		catch (Exception e) {e.printStackTrace();}
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<RoleValue> userRoles = user.getRoles().stream().map(Role::getRole).collect(Collectors.toSet());
		if (userRoles.contains(RoleValue.ROLE_STUDENT)) learningPlanService.selectNewLearningPlan(
				user.getForeignId(), Integer.valueOf(objectiveId));
		return "dashboard";
	}

}
