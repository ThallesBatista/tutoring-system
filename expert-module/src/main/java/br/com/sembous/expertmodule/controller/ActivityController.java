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

import br.com.sembous.expertmodule.dto.activity.ActivityAllTreeDto;
import br.com.sembous.expertmodule.dto.activity.ActivityDetailedDto;
import br.com.sembous.expertmodule.dto.activity.ActivityDto;
import br.com.sembous.expertmodule.dto.activity.ActivityFormDto;
import br.com.sembous.expertmodule.dto.activity.ActivityUpdateFormDto;
import br.com.sembous.expertmodule.model.Activity;
import br.com.sembous.expertmodule.model.Concept;
import br.com.sembous.expertmodule.repository.ActivityRepository;
import br.com.sembous.expertmodule.repository.ConceptRepository;

@RestController
@RequestMapping("/activity")
public class ActivityController {
	
	@Autowired
	ActivityRepository activityRepository;
	@Autowired
	ConceptRepository conceptRepository;
	
	@GetMapping
	public List<ActivityDto> getAll() {
		List<Activity> activities = activityRepository.findAll();
		return ActivityDto.convert(activities);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ActivityDto> post(@RequestBody @Valid ActivityFormDto form, UriComponentsBuilder uriBuilder) {
		Activity activity = form.convert();
		Optional<Concept> optional = conceptRepository.findByIdWithActivities(form.getConceptId());
		
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		
		if (form.getOrder()!= null) optional.get().addActivity(form.getOrder(), activity);
		else optional.get().addActivity(activity);
		
		activityRepository.save(activity);
		
		URI uri = uriBuilder.path("activity/{id}").buildAndExpand(activity.getId()).toUri();
		return ResponseEntity.created(uri).body(new ActivityDto(activity));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ActivityDto> put(@PathVariable Integer id, @RequestBody @Valid ActivityUpdateFormDto form) {
		Optional<Activity> optional = activityRepository.findByIdDetailed(id);
		
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		
		Activity activity = optional.get();		
		form.update(activity);
		
		if (form.getConceptId().equals(activity.getConcept().getId())) {
			if (form.getOrder()!=null && !form.getOrder().equals(activity.getConcept().getActivityOrder(activity))) {
				activity.getConcept().alterActivityPosition(form.getOrder(), activity);
			}
		} else {
			Optional<Concept> optionalConcept = conceptRepository.findByIdWithActivities(form.getConceptId());
			if (optionalConcept.isEmpty()) return ResponseEntity.notFound().build();
			if (form.getOrder()!=null) optionalConcept.get().addActivity(form.getOrder(), activity);
			else optionalConcept.get().addActivity(activity);
		}
		
		return ResponseEntity.ok().body(new ActivityDto(activity));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Optional<Activity> optional = activityRepository.findById(id);
		
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		
		activityRepository.delete(optional.get());		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<?> getDetailed (@PathVariable Integer id, @RequestParam(required =  false) UntilValue until, ObjectMapper mapper) {		
		Optional<Activity> optional = activityRepository.findById(id);
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		Activity activity = optional.get();
		
		if (until!=null) return ResponseEntity.ok().body(new ActivityAllTreeDto(activity, mapper));
		return ResponseEntity.ok().body(new ActivityDetailedDto(activity, mapper));
	}
}
