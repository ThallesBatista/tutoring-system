package br.com.sembous.tutoringmodule.api;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sembous.teachermoduleapi.model.Clazz;
import br.com.sembous.tutoringmodule.api.dto.ClazzDto;
import br.com.sembous.tutoringmodule.service.TeacherService;

@RestController
@RequestMapping(path = "/api/class")
public class ClassRestController {
	
	@Autowired
	TeacherService teacherService;

	@GetMapping
	public ResponseEntity<ClazzDto> getClazz(HttpSession session){
		Integer classId = (Integer) session.getAttribute("classId");
		Optional<Clazz> optional = teacherService.getClazz(classId);
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
				
		return ResponseEntity.ok(new ClazzDto(optional.get()));
	}
	
	@DeleteMapping
	public ResponseEntity<?> removeClazz(HttpSession session) {
		Integer classId = (Integer) session.getAttribute("classId");
		Boolean classRemoved = teacherService.removeClass(classId);
		if (classRemoved) {
			session.removeAttribute("classId");;
			return ResponseEntity.ok().build();
		}
		else return ResponseEntity.internalServerError().build();
	}
	
	@DeleteMapping(path="/learningPlan/{id}")
	public ResponseEntity<?> removeLearningPlan(@PathVariable(required = true, name = "id") Integer learningPlanId) {
		Boolean learningPlanRemoved = teacherService.removeLearningPlan(learningPlanId);
		if (learningPlanRemoved) return ResponseEntity.ok().build();
		else return ResponseEntity.internalServerError().build();
	}
	
}
