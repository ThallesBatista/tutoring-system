package br.com.sembous.expertmodule.controller;

import java.net.URI;
import java.util.List;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sembous.expertmodule.dto.pedagogicalobjective.PedagogicalObjectiveAllTreeDto;
import br.com.sembous.expertmodule.dto.pedagogicalobjective.PedagogicalObjectiveDetailedDto;
import br.com.sembous.expertmodule.dto.pedagogicalobjective.PedagogicalObjectiveDto;
import br.com.sembous.expertmodule.dto.pedagogicalobjective.PedagogicalObjectiveFormDto;
import br.com.sembous.expertmodule.dto.pedagogicalobjective.PedagogicalObjectiveUpdateFormDto;
import br.com.sembous.expertmodule.model.PedagogicalObjective;
import br.com.sembous.expertmodule.model.Topic;
import br.com.sembous.expertmodule.repository.PedagogicalObjectiveRepository;
import br.com.sembous.expertmodule.repository.TopicRepository;

@RestController
@RequestMapping(path = "/pedagogical-objective")
public class PedagogicalObjectiveController {

	@Autowired
	private PedagogicalObjectiveRepository pedagogicalObjectiveRepository;
	
	@Autowired
	private TopicRepository topicRepository;

	@GetMapping
	public List<?> getAll() {
		List<PedagogicalObjective> pedagogicalObjectives;
		pedagogicalObjectives = pedagogicalObjectiveRepository.findAll();
		return PedagogicalObjectiveDto.convert(pedagogicalObjectives);
	}
	
	@GetMapping(path = "/getTrees")
	public ResponseEntity<List<PedagogicalObjectiveAllTreeDto>> getTree(@RequestParam UntilValue until, ObjectMapper mapper) {
		List<PedagogicalObjective> objectives = pedagogicalObjectiveRepository.findAll();
		return ResponseEntity.ok(PedagogicalObjectiveAllTreeDto.convert(objectives, mapper, until));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<PedagogicalObjectiveDto> post(@RequestBody @Valid PedagogicalObjectiveFormDto form, UriComponentsBuilder uriBuilder) {
		PedagogicalObjective pedagogicalObjective = form.convert();
		
		Optional<Topic> optionalTopic = topicRepository.findById(form.getTopicId());
		
		if (optionalTopic.isEmpty()) return ResponseEntity.notFound().build();
		
		if (form.getOrder()!=null) optionalTopic.get().addPedagogicalObjective(form.getOrder(),pedagogicalObjective);
		else optionalTopic.get().addPedagogicalObjective(pedagogicalObjective);
		
		pedagogicalObjectiveRepository.save(pedagogicalObjective);
		
		URI uri = uriBuilder.path("pedagogical-objectives/{id}").buildAndExpand(pedagogicalObjective.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new PedagogicalObjectiveDto(pedagogicalObjective));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PedagogicalObjectiveDto> put (@PathVariable Integer id, @RequestBody @Valid PedagogicalObjectiveUpdateFormDto form) {
		Optional<PedagogicalObjective> optional = pedagogicalObjectiveRepository.findById(id);
		
		if (optional.isEmpty())return ResponseEntity.notFound().build();
		
		PedagogicalObjective pedagogicalObjective = optional.get();
		form.update(pedagogicalObjective);
		
		Integer newTopicId = form.getTopicId();
		Integer oldTopicId = pedagogicalObjective.getTopic().getId();
		Integer newOrder = form.getOrder();
		if (!newTopicId.equals(oldTopicId)) {
			Optional<Topic> optionalTopic = topicRepository.findById(newTopicId);
			
			if (optionalTopic.isEmpty()) return ResponseEntity.notFound().build();
			
			if (newOrder!=null) optionalTopic.get().addPedagogicalObjective(newOrder, pedagogicalObjective);
			else optionalTopic.get().addPedagogicalObjective(pedagogicalObjective);
		}		
		Integer oldOrder = pedagogicalObjective.getTopic().getPedagogicalObjectiveOrder(pedagogicalObjective);
		if (newOrder!=null && newOrder!=oldOrder) pedagogicalObjective.getTopic().alterPedagogicalObjectiveOrder(newOrder, pedagogicalObjective);
		
		return ResponseEntity.ok().body(new PedagogicalObjectiveDto(pedagogicalObjective));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete (@PathVariable Integer id) {
		Optional<PedagogicalObjective> optional = pedagogicalObjectiveRepository.findById(id);
		
		if (optional.isPresent()) {
			pedagogicalObjectiveRepository.delete(optional.get());
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getDetailed (@PathVariable Integer id, @RequestParam(required =  false) UntilValue until, ObjectMapper mapper){
		Optional<PedagogicalObjective> optional = pedagogicalObjectiveRepository.findByIdDetailed(id);
		if (optional.isPresent()) {
			PedagogicalObjective pedagogicalObjective = optional.get();
			if (until != null) return ResponseEntity.ok().body(new PedagogicalObjectiveAllTreeDto(pedagogicalObjective, mapper, until));
			return ResponseEntity.ok().body(new PedagogicalObjectiveDetailedDto(pedagogicalObjective));
		}
		
		return ResponseEntity.notFound().build();
	}

}
