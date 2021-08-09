package br.com.sembous.expertmodule.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

import br.com.sembous.expertmodule.dto.concept.ConceptAllTreeDto;
import br.com.sembous.expertmodule.dto.concept.ConceptDetailedDto;
import br.com.sembous.expertmodule.dto.concept.ConceptDto;
import br.com.sembous.expertmodule.dto.concept.ConceptFormDto;
import br.com.sembous.expertmodule.dto.concept.ConceptUpdateFormDto;
import br.com.sembous.expertmodule.model.Concept;
import br.com.sembous.expertmodule.model.Notion;
import br.com.sembous.expertmodule.repository.ConceptRepository;
import br.com.sembous.expertmodule.repository.NotionRepository;

@RestController
@RequestMapping(path = "/concept")
public class ConceptController {

	@Autowired
	private NotionRepository notionRepository;
	@Autowired
	private ConceptRepository conceptRepository;

	@GetMapping
	public List<ConceptDto> getAll() {
		List<Concept> concepts = conceptRepository.findAll();
		return ConceptDto.convert(concepts);
	}
	
	@GetMapping(path = "/getTrees")
	public ResponseEntity<List<ConceptAllTreeDto>> getTree(@RequestParam UntilValue until, ObjectMapper mapper) {
		List<Concept> concepts = conceptRepository.findAll();
		return ResponseEntity.ok(ConceptAllTreeDto.convert(concepts, mapper, until));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ConceptDto> post(@RequestBody @Valid ConceptFormDto form, UriComponentsBuilder uriBuilder) {
		Concept concept = form.convert();
		
		Optional<Integer> optionalOrderForm = form.getOrder();
		Optional<Integer> optionalNotionIdForm = form.getNotionId();
		Optional<Integer> optionalParentIdForm = form.getChildOf();
		
		if (optionalNotionIdForm.isPresent()) {
			Integer notionIdForm = optionalNotionIdForm.get();
			Optional<Notion> optionalNewNotion = notionRepository.findByIdWithConcepts(notionIdForm);
			if (optionalNewNotion.isEmpty()) return ResponseEntity.notFound().build();
			Notion newNotion = optionalNewNotion.get();
			if (optionalOrderForm.isPresent()) newNotion.addConcept(optionalOrderForm.get(), concept);
			else newNotion.addConcept(concept);
			
		} else if (optionalParentIdForm.isPresent()) {
			Integer parentIdForm = optionalParentIdForm.get();			
			Optional<Concept> optionalNewParent = conceptRepository.findByIdWithChildren(parentIdForm);
			if (optionalNewParent.isEmpty()) return ResponseEntity.notFound().build();
			Concept newParent = optionalNewParent.get();
			if (optionalOrderForm.isPresent()) newParent.addChild(optionalOrderForm.get(), concept); 
			else newParent.addChild(concept);
		}
		
		if (!form.getDependentOf().isEmpty()) {
			List<Concept> dependencies = conceptRepository.findAllById(form.getDependentOf());
			if (dependencies.isEmpty()) return ResponseEntity.notFound().build();
			concept.addDependencies(dependencies);
		}
		
		conceptRepository.save(concept);
		URI uri = uriBuilder.path("concept/{id}").buildAndExpand(concept.getId()).toUri();
		return ResponseEntity.created(uri).body(new ConceptDto(concept));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ConceptDto> put(@PathVariable Integer id, @RequestBody @Valid ConceptUpdateFormDto form) {
		Optional<Concept> optionalConcept = conceptRepository.findByIdWithNotionAndParentAndDependencies(id);
		
		if (optionalConcept.isEmpty()) return ResponseEntity.notFound().build();
		
		Concept concept = optionalConcept.get();
		form.update(concept);
		
		Optional<Integer> optionalOrderForm = form.getOrder();
		Optional<Integer> optionalNotionIdForm = form.getNotionId();
		Optional<Notion> optionalNotionConcept = concept.getNotion();
		Optional<Integer> optionalParentIdForm = form.getChildOf();
		Optional<Concept> optionalParentConcept = concept.getParent();
		
		
		if (optionalNotionIdForm.isPresent()) {
			Integer notionIdForm = optionalNotionIdForm.get();
			if (optionalNotionConcept.isPresent()) {
				Notion notionConcept = optionalNotionConcept.get();
				
				if (notionIdForm.equals(notionConcept.getId()))
					optionalOrderForm.ifPresent(o -> {if (!o.equals(notionConcept.getConceptOrder(concept))) notionConcept.alterConceptOrder(o, concept);});
				else {
					Optional<Notion> optionalNewNotion = notionRepository.findByIdWithConcepts(notionIdForm);
					if (optionalNewNotion.isEmpty()) return ResponseEntity.notFound().build();
					Notion newNotion = optionalNewNotion.get();
					if (optionalOrderForm.isPresent()) newNotion.addConcept(optionalOrderForm.get(), concept);
					else newNotion.addConcept(concept);
				}
				
			} else {
				Optional<Notion> optionalNewNotion = notionRepository.findByIdWithConcepts(notionIdForm);
				if (optionalNewNotion.isEmpty()) return ResponseEntity.notFound().build();
				Notion newNotion = optionalNewNotion.get();
				if (optionalOrderForm.isPresent()) newNotion.addConcept(optionalOrderForm.get(), concept);
				else newNotion.addConcept(concept);
				
			}
		} else if (optionalParentIdForm.isPresent()) {
			Integer parentIdForm = optionalParentIdForm.get();
			if (optionalParentConcept.isPresent()) {
				Concept parentConcept = optionalParentConcept.get();
				
				if (parentIdForm.equals(parentConcept.getId()))
					optionalOrderForm.ifPresent(o -> {if (!o.equals(parentConcept.getConceptOrder(concept))) parentConcept.alterChildOrder(o, concept);});
				else {
					Optional<Concept> optionalNewParent = conceptRepository.findByIdWithChildren(parentIdForm);
					if (optionalNewParent.isEmpty()) return ResponseEntity.notFound().build();
					Concept newParent = optionalNewParent.get();
					if (optionalOrderForm.isPresent()) newParent.addChild(optionalOrderForm.get(), concept); 
					else newParent.addChild(concept);
				}
				
			} else {
				Optional<Concept> optionalNewParent = conceptRepository.findByIdWithChildren(parentIdForm);
				if (optionalNewParent.isEmpty()) return ResponseEntity.notFound().build();
				Concept newParent = optionalNewParent.get();
				if (optionalOrderForm.isPresent()) newParent.addChild(optionalOrderForm.get(), concept); 
				else newParent.addChild(concept);
			}
		}
		
		
		concept.getDependencies().stream().forEach(d -> {if (!form.getDependentOf().contains(d.getId())) concept.removeDependency(d);} );
		Set<Integer> dependencyIds = concept.getDependencies().stream().map(Concept::getId).collect(Collectors.toSet());
		Set<Integer> dependenciesToAddIds = form.getDependentOf().stream().filter(i -> !dependencyIds.contains(i)).collect(Collectors.toSet());
		if (!dependenciesToAddIds.isEmpty()) {
			List<Concept> dependenciesToAdd = conceptRepository.findAllById(dependenciesToAddIds);
			if (dependenciesToAdd.isEmpty()) return ResponseEntity.notFound().build();
			concept.addDependencies(dependenciesToAdd);
		}
		
		return ResponseEntity.ok().body(new ConceptDto(concept));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete (@PathVariable Integer id) {
		Optional<Concept> optional = conceptRepository.findById(id);
		
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		
		conceptRepository.delete(optional.get());
		return ResponseEntity.ok().build();		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getDetailed (@PathVariable Integer id, @RequestParam(required =  false) UntilValue until, ObjectMapper mapper) {		
		Optional<Concept> optional = conceptRepository.findByIdComplete(id);
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		Concept concept = optional.get();
		
		if (until!=null) return ResponseEntity.ok().body(new ConceptAllTreeDto(concept, mapper,until));
		return ResponseEntity.ok().body(new ConceptDetailedDto(concept));
	}

}
