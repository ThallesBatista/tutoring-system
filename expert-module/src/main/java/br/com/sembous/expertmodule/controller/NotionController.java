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

import br.com.sembous.expertmodule.dto.notion.NotionAllTreeDto;
import br.com.sembous.expertmodule.dto.notion.NotionDetailedDto;
import br.com.sembous.expertmodule.dto.notion.NotionDto;
import br.com.sembous.expertmodule.dto.notion.NotionFormDto;
import br.com.sembous.expertmodule.dto.notion.NotionUpdateFormDto;
import br.com.sembous.expertmodule.model.Notion;
import br.com.sembous.expertmodule.model.PedagogicalObjective;
import br.com.sembous.expertmodule.repository.NotionRepository;
import br.com.sembous.expertmodule.repository.PedagogicalObjectiveRepository;

@RestController
@RequestMapping(path = "/notion")
public class NotionController {

	@Autowired
	private NotionRepository notionRepository;
	@Autowired
	private PedagogicalObjectiveRepository pedagogicalObjectiveRepository;

	@GetMapping
	public List<NotionDto> getAll() {
		List<Notion> notions = notionRepository.findAll();
		return NotionDto.convert(notions);
	}

	
	@GetMapping(path = "/getTrees")
	public ResponseEntity<List<NotionAllTreeDto>> getTree(@RequestParam UntilValue until, ObjectMapper mapper) {
		List<Notion> notions = notionRepository.findAll();
		return ResponseEntity.ok(NotionAllTreeDto.convert(notions, mapper, until));
	}
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<NotionDto> post(@RequestBody @Valid NotionFormDto form, UriComponentsBuilder uriBuilder) {
		Notion notion = form.convert();
		Optional<PedagogicalObjective> optional = pedagogicalObjectiveRepository.findByIdWtihNotion(form.getPedagogicalObjectiveId());
		
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		
		if (form.getOrder()!=null) optional.get().addNotion(form.getOrder(),notion);
		else optional.get().addNotion(notion);
		
		notionRepository.save(notion);
		URI uri = uriBuilder.path("notion/{id}").buildAndExpand(notion.getId()).toUri();
		return ResponseEntity.created(uri).body(new NotionDto(notion));		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<NotionDto> put (@PathVariable Integer id, @RequestBody @Valid NotionUpdateFormDto form) {
		Optional<Notion> optionalNotion = notionRepository.findByIdWithPedagogicalObjective(id);
		
		if (optionalNotion.isEmpty()) return ResponseEntity.notFound().build();
		
		Notion notion = optionalNotion.get();
		form.update(notion);
		
		Integer newObjectiveId = form.getPedagogicalObjectiveId();
		Integer oldObjectiveId = notion.getPedagogicalObjective().getId();
		Integer newOrder = form.getOrder();
		if (!newObjectiveId.equals(oldObjectiveId)) {
			Optional<PedagogicalObjective> optionalObjective = pedagogicalObjectiveRepository.findByIdWtihNotion(newObjectiveId);
			
			if (optionalObjective.isEmpty()) return ResponseEntity.notFound().build();
			
			if (newOrder!=null) optionalObjective.get().addNotion(newOrder, notion);
			else optionalObjective.get().addNotion(notion);
		}
		
		Integer oldOrder = notion.getPedagogicalObjective().getNotionOrder(notion);
		if (newOrder!=null && newOrder!=oldOrder) notion.getPedagogicalObjective().alterNotionOrder(newOrder, notion);
		
		return ResponseEntity.ok().body(new NotionDto(notion));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete (@PathVariable Integer id) {
		Optional<Notion> optional = notionRepository.findById(id);
		
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		
		notionRepository.delete(optional.get());
		return ResponseEntity.ok().build();		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getDetailed (@PathVariable Integer id, @RequestParam(required =  false) UntilValue until, ObjectMapper mapper) {
		Optional<Notion> optional = notionRepository.findByIdDetailed(id);
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		Notion notion = optional.get();
		
		if (until!=null) return ResponseEntity.ok().body(new NotionAllTreeDto(notion,mapper,until));
		return ResponseEntity.ok().body(new NotionDetailedDto(notion));		
	}

}
