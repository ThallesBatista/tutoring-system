package br.com.sembous.tutoringmodule.api;

import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

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
import br.com.sembous.tutoringmodule.service.TeacherService;

@RestController
@RequestMapping(path = "/selectObjective")
public class SelectObjectiveRestController {
	
	@Autowired
	private LearningPlanService learningPlanService;
	
	@Autowired
	private TeacherService teacherService;
	
	@PostMapping
	public String newObjective(@RequestBody JsonNode objectiveIdJson, HttpSession session) {
		Integer objectiveId;
		try {objectiveId = Integer.valueOf(objectiveIdJson.get("objectiveId").toString());} 
		catch (Exception e) {e.printStackTrace(); return "index";}
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<RoleValue> userRoles = user.getRoles().stream().map(Role::getRole).collect(Collectors.toSet());
		if (userRoles.contains(RoleValue.ROLE_STUDENT)) {
			learningPlanService.selectNewLearningPlan(user.getForeignId(), Integer.valueOf(objectiveId));
			return "dashboard";
		}
		if (userRoles.contains(RoleValue.ROLE_TEACHER) && session.getAttribute("classId")!=null) {
			Integer classId = (Integer) session.getAttribute("classId");
			teacherService.addLearningPlan(classId, objectiveId);
			return "class/"+classId.toString();
		}
		return "index";
	}

}
