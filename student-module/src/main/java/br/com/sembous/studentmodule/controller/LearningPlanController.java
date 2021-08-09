package br.com.sembous.studentmodule.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sembous.studentmodule.dto.LearningPlanDto;
import br.com.sembous.studentmodule.dto.LearningPlanSimpleDto;
import br.com.sembous.studentmodule.form.LearningPlanPieceDoneForm;
import br.com.sembous.studentmodule.form.LearningPlanPieceForm;
import br.com.sembous.studentmodule.model.LearningPlan;
import br.com.sembous.studentmodule.model.Student;
import br.com.sembous.studentmodule.repository.LearningPlanRepository;
import br.com.sembous.studentmodule.repository.StudentRepository;

@RestController
@RequestMapping(path = "/learningPlan")
public class LearningPlanController {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	LearningPlanRepository learningPlanRespository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<LearningPlanSimpleDto> addLearningPlan(
			@RequestParam Integer studentId, @RequestBody @Valid LearningPlanPieceForm form, UriComponentsBuilder uriBuilder) {
		Optional<Student> optional = studentRepository.findById(studentId);
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		
		Student student = optional.get();
		LearningPlan learningPlan = student.addLearningPlan(form.convert());

		URI uri = uriBuilder.path("learningPlan/{id}").buildAndExpand(learningPlan.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new LearningPlanSimpleDto(learningPlan));
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<LearningPlanDto> getLearningPlan(@PathVariable(name = "id") Integer id) {
		
		Optional<LearningPlan> optional = learningPlanRespository.findById(id);
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		
		LearningPlan learningPlan = optional.get();	
		return ResponseEntity.ok(new LearningPlanDto(learningPlan));
	}
	
	@PutMapping(path = "/updateActivity/{id}")
	@Transactional
	public ResponseEntity<?> updateActivity(@PathVariable(name = "id") Integer id, @RequestBody @Valid LearningPlanPieceDoneForm form) {
		Optional<LearningPlan> optional = learningPlanRespository.findById(id);
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		LearningPlan learningPlan = optional.get();
		
		Student student = learningPlan.getStudent();
		student.updateLearningPlan(id, form.convert());
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(path = "/{id}")
	@Transactional
	public ResponseEntity<?> updateLearningPlan(@PathVariable(name = "id") Integer id, @RequestBody @Valid LearningPlanPieceForm form) {
		
		Optional<LearningPlan> optional = learningPlanRespository.findById(id);
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		LearningPlan learningPlan = optional.get();
		
		Student student = learningPlan.getStudent();
		student.updateLearningPlan(id, form.convert());
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(path = "/{id}")
	@Transactional
	public ResponseEntity<?> deleteLearningPlan(@PathVariable(name = "id") Integer id) {
		
		Optional<LearningPlan> optional = learningPlanRespository.findById(id);
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		LearningPlan learningPlan = optional.get();
		
		Student student = learningPlan.getStudent();
		student.getLearningPlanManager().removeLearningPlan(id);
		
		return ResponseEntity.ok().build();
	}
}
