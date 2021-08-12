package br.com.sembous.teachermodule.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sembous.teachermodule.dto.TeacherDto;
import br.com.sembous.teachermodule.form.TeacherForm;
import br.com.sembous.teachermodule.model.Teacher;
import br.com.sembous.teachermodule.repository.TeacherRespository;

@RestController
@RequestMapping(path = "/teacher")
public class TeacherController {

	@Autowired
	TeacherRespository teacherRespository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<TeacherDto> post(@RequestBody @Valid TeacherForm form, UriComponentsBuilder uriBuilder){
		Teacher teacher = form.convert();
		teacherRespository.save(teacher);
		
		URI uri = uriBuilder.path("teacher/{id}").buildAndExpand(teacher.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new TeacherDto(teacher, TeacherInformations.PERSONAL_INFORMATIONS));
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<TeacherDto> getOne(@PathVariable Integer id, @RequestParam(required = false) TeacherInformations informations) {
		if (informations == null) informations = TeacherInformations.PERSONAL_INFORMATIONS;
		
		Optional<Teacher> optional = teacherRespository.findById(id);
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		Teacher teacher = optional.get();
		return ResponseEntity.ok().body(new TeacherDto(teacher, informations));	
	}
}
