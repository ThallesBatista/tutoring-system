package br.com.sembous.teachermodule.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sembous.teachermodule.dto.ClazzLearningPlanSimpleDto;
import br.com.sembous.teachermodule.form.ClazzLearningPlanPieceForm;
import br.com.sembous.teachermodule.model.Clazz;
import br.com.sembous.teachermodule.model.ClazzLearningPlan;
import br.com.sembous.teachermodule.model.ClazzLearningPlanPiece;
import br.com.sembous.teachermodule.repository.ClazzRepository;

@RestController
@RequestMapping(path = "classLearningPlan")
public class ClazzLearningPlanController {
	
	@Autowired
	private ClazzRepository clazzRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<ClazzLearningPlanSimpleDto> create(@RequestBody @Valid ClazzLearningPlanPieceForm form, 
			@RequestParam(required = true, name = "classId") Integer classId, UriComponentsBuilder uriBuilder) {
		Optional<Clazz> optional = clazzRepository.findById(classId);
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		Clazz clazz = optional.get();
		
		ClazzLearningPlanPiece clazzLPP = form.convert();
		ClazzLearningPlan learningPlan = clazz.addLearningPlan(clazzLPP);
		
		URI uri = uriBuilder.path("classLearningPlan/{id}").buildAndExpand(learningPlan.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new ClazzLearningPlanSimpleDto(learningPlan));
	}

}
