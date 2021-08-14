package br.com.sembous.teachermodule.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sembous.teachermodule.dto.ClazzDto;
import br.com.sembous.teachermodule.dto.ClazzSimpleDto;
import br.com.sembous.teachermodule.form.ClazzForm;
import br.com.sembous.teachermodule.model.Clazz;
import br.com.sembous.teachermodule.model.Teacher;
import br.com.sembous.teachermodule.repository.ClazzRepository;
import br.com.sembous.teachermodule.repository.TeacherRespository;

@RestController
@RequestMapping(path="/class")
public class ClazzController {
	
	@Autowired
	TeacherRespository teacherRepository;
	
	@Autowired
	ClazzRepository clazzRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<ClazzSimpleDto> create(@RequestBody @Valid ClazzForm form, @RequestParam(required = true) Integer teacherId, 
			UriComponentsBuilder uriBuilder){
		Optional<Teacher> optional = teacherRepository.findById(teacherId);
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		Teacher teacher = optional.get();
		
		Clazz clazz = form.convert();
		teacher.addClazz(clazz);
		
		URI uri = uriBuilder.path("class/{id}").buildAndExpand(clazz.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new ClazzSimpleDto(clazz));
	}
	
	@DeleteMapping(path = "/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id){
		Optional<Clazz> optional = clazzRepository.findById(id);
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		Clazz clazz = optional.get();
		
		Teacher teacher = clazz.getTeacher();
		teacher.getClazzManager().removeClazz(clazz);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<ClazzDto> getOne(@PathVariable Integer id) {
		Optional<Clazz> optional = clazzRepository.findById(id);
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		Clazz clazz = optional.get();
		return ResponseEntity.ok().body(new ClazzDto(clazz));	
	}
}
