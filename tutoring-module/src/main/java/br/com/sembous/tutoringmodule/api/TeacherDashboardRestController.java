package br.com.sembous.tutoringmodule.api;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sembous.teachermoduleapi.model.Teacher;
import br.com.sembous.tutoringmodule.api.dto.SimplifiedClazzDto;
import br.com.sembous.tutoringmodule.api.dto.TeacherPersonalInformationsDto;
import br.com.sembous.tutoringmodule.config.security.User;
import br.com.sembous.tutoringmodule.service.TeacherService;

@RestController
@RequestMapping(path="/api/teacherDashboard")
public class TeacherDashboardRestController {
	
	@Autowired
	TeacherService teacherService;
	
	@GetMapping(path="/personalInformations")
	public ResponseEntity<TeacherPersonalInformationsDto> getTeacherPersonalInformations() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Teacher> optional = teacherService.getTeacher(user.getForeignId());
		if (optional.isEmpty()) throw new IllegalStateException("The user has the role of a teacher, but was not found as one");
		return ResponseEntity.ok(new TeacherPersonalInformationsDto(optional.get()));
	}

	
	@GetMapping(path="/classes")
	public ResponseEntity<Set<SimplifiedClazzDto>> getTeacherSimplifiedClasses() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Teacher> optional = teacherService.getTeacherWithSimplifiedClasses(user.getForeignId());
		if (optional.isEmpty()) throw new IllegalStateException("The user has the role of a teacher, but was not found as one");
		return ResponseEntity.ok(SimplifiedClazzDto.convert(optional.get().getClazzManager().getClasses()));
	}
}
