package br.com.sembous.tutoringmodule.api;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping(path = "/class")
	public ResponseEntity<ClazzDto> getClazz(HttpSession session){
		Integer classId = (Integer) session.getAttribute("classId");
		Optional<Clazz> optional = teacherService.getClazz(classId);
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
				
		return ResponseEntity.ok(new ClazzDto(optional.get()));
	}
}
