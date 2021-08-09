package br.com.sembous.expertmodule.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sembous.expertmodule.dto.topic.TopicAllTreeDto;
import br.com.sembous.expertmodule.dto.topic.TopicDetailedDto;
import br.com.sembous.expertmodule.dto.topic.TopicDto;
import br.com.sembous.expertmodule.model.Topic;
import br.com.sembous.expertmodule.repository.TopicRepository;

@RestController
@RequestMapping(path = "/topic")
public class TopicController {
	
	@Autowired
	TopicRepository topicRepostiory;
	
	@GetMapping
	public ResponseEntity<List<TopicDto>> getAll() {
		List<Topic> topics = topicRepostiory.findAll();
		return ResponseEntity.ok(TopicDto.convert(topics));
	}
	
	
	@GetMapping(path = "/getTrees")
	public ResponseEntity<List<TopicAllTreeDto>> getTree(@RequestParam UntilValue until, ObjectMapper mapper) {
		List<Topic> topics = topicRepostiory.findAll();
		return ResponseEntity.ok(TopicAllTreeDto.convert(topics, mapper, until));
	}
	
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getDetailed (@PathVariable Integer id, @RequestParam(required =  false) UntilValue until, ObjectMapper mapper) {
		Optional<Topic> optional = topicRepostiory.findById(id);
		if (optional.isPresent()) {
			Topic topic = optional.get();
			if (until != null) return ResponseEntity.ok().body(new TopicAllTreeDto(topic, mapper, until));
			return ResponseEntity.ok().body(new TopicDetailedDto(topic));
		}
		
		return ResponseEntity.notFound().build();
	}
}
