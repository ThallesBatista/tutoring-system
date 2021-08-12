package br.com.sembous.studentmodule.controller;

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

import br.com.sembous.studentmodule.dto.StudentDto;
import br.com.sembous.studentmodule.form.StudentFormDto;
import br.com.sembous.studentmodule.model.Student;
import br.com.sembous.studentmodule.repository.StudentRepository;
import br.com.sembous.studentmodule.service.PreferencesDeducerService;

@RestController
@RequestMapping(path = "/student")
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	PreferencesDeducerService preferencesDeducerService;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<StudentDto> getOne(@PathVariable Integer id, 
			@RequestParam(defaultValue = "false") Boolean withPreferences, 
			@RequestParam(defaultValue = "false") Boolean withLearningPlan,
			@RequestParam(defaultValue = "false") Boolean withKnowledgeDone) {
		Optional<Student> optional = studentRepository.findById(id);
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		Student student = optional.get();
		
		if (withPreferences) preferencesDeducerService.update(student);
		return ResponseEntity.ok().body(new StudentDto(student, withPreferences, withLearningPlan, withKnowledgeDone));	
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<StudentDto> post(@RequestBody @Valid StudentFormDto form, UriComponentsBuilder uriBuilder){
		Student student = form.convert();
		studentRepository.save(student);
		
		URI uri = uriBuilder.path("student/{id}").buildAndExpand(student.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new StudentDto(student, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE));
	}
	
//	@PutMapping(path = "/{id}")
//	@Transactional
//	public ResponseEntity<StudentDto> put(@PathVariable Integer id, @RequestBody @Valid StudentUpdateForm form) {
//		Optional<Student> optional = studentRepository.findById(id);
//		if (optional.isEmpty()) return ResponseEntity.notFound().build();
//		
//		Student student = optional.get();
//		form.update(student);
//		
//		return ResponseEntity.ok(new StudentDto(student));
//	}
	
//	@DeleteMapping(path = "/{id}")
//	@Transactional
//	public ResponseEntity<?> delete(@PathVariable Integer id){
//		boolean existsById = studentRepository.existsById(id);
//		if (!existsById) return ResponseEntity.notFound().build();
//		
//		studentRepository.deleteById(id);
//		return ResponseEntity.ok().build();
//	}
}
