package br.com.sembous.studentmodule.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sembous.studentmodule.dto.InteractionDto;
import br.com.sembous.studentmodule.model.Interaction;
import br.com.sembous.studentmodule.model.KnowledgeType;
import br.com.sembous.studentmodule.model.Student;
import br.com.sembous.studentmodule.repository.InteractionRepository;
import br.com.sembous.studentmodule.repository.StudentRepository;

@RestController
@RequestMapping(path = "/interaction")
public class InteractionController {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	InteractionRepository interactionRepository;

	@GetMapping(path = "/knowledge/{studentId}/{knowledgePieceType}/{expertModuleId}")
	public ResponseEntity<List<InteractionDto>> getKnowledgeInteractions(
			@PathVariable Integer studentId, @PathVariable String knowledgePieceType, @PathVariable Integer expertModuleId) {
		
		Optional<Student> optional = studentRepository.findById(studentId);
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		
		KnowledgeType type;
		try {type = KnowledgeType.valueOf(knowledgePieceType);}
		catch (Exception e) {return ResponseEntity.notFound().build();}
		
		List<Interaction> interactions = interactionRepository.findByStudentIdAndKnowledgePieceIdTypeAndKnowledgePieceIdExpertModuleId(studentId, type, expertModuleId);
		List<InteractionDto> dtos = InteractionDto.convertList(interactions);
		
		return ResponseEntity.ok(dtos);
	}
}
