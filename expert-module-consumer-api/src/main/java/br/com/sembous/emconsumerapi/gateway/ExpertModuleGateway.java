package br.com.sembous.emconsumerapi.gateway;

import org.springframework.web.client.RestTemplate;

import br.com.sembous.emconsumerapi.gateway.implementation.ActivityGateway;
import br.com.sembous.emconsumerapi.gateway.implementation.ConceptGateway;
import br.com.sembous.emconsumerapi.gateway.implementation.NotionGateway;
import br.com.sembous.emconsumerapi.gateway.implementation.PedagogicalObjectiveGateway;
import br.com.sembous.emconsumerapi.gateway.implementation.TopicGateway;
import br.com.sembous.emconsumerapi.model.Activity;
import br.com.sembous.emconsumerapi.model.Concept;
import br.com.sembous.emconsumerapi.model.Notion;
import br.com.sembous.emconsumerapi.model.PedagogicalObjective;
import br.com.sembous.emconsumerapi.model.Topic;

public class ExpertModuleGateway {

//	private String topicBaseUrl;
//	private String pedagogicalObjectiveBaseUrl;
//	private String notionBaseUrl;
//	private String conceptBaseUrl;
//	private String activitybaseUrl;
//	private RestTemplate restTemplate;
//	
//	public ExpertModuleGateway(RestTemplate restTemplate) {
////		this.topicBaseUrl = "http://localhost:8081/topic";
////		this.pedagogicalObjectiveBaseUrl = "http://localhost:8081/pedagogical-objective";
////		this.notionBaseUrl = "http://localhost:8081/notion";
////		this.conceptBaseUrl = "http://localhost:8081/concept";
////		this.activitybaseUrl = "http://localhost:8081/activity";
//		this.restTemplate = restTemplate;
//	}
	
	
//	Possível evolução, em que devolvo o entity gateway, o qual usa o respectivo dto e tudo, só precisando ser passado um rest template e um url
//	public <T> EntityGateway<T> getGateway(Class<T> c) {
//		return null;
//	}
	
	public EntityGateway<Topic> getTopicGateway(RestTemplate restTemplate) {
		return new TopicGateway(restTemplate);
	}
	public EntityGateway<PedagogicalObjective> getPedagogicalObjectiveGateway(RestTemplate restTemplate) {
		return new PedagogicalObjectiveGateway(restTemplate);
	}
	public EntityGateway<Notion> getNotionGateway(RestTemplate restTemplate) {
		return new NotionGateway(restTemplate);
	}
	public EntityGateway<Concept> getConceptGateway(RestTemplate restTemplate) {
		return new ConceptGateway(restTemplate);
	}
	public EntityGateway<Activity> getActivityGateway(RestTemplate restTemplate) {
		return new ActivityGateway(restTemplate);
	}
	
//	public Optional<Topic> getTopic(Integer id){
//		
//	}
//	public Optional<Topic> getTopic(Integer id, UntilValue until){
//		
//	}
//	public Optional<List<Topic>> getAllTopics() {
//		
//	}
//	public Optional<List<Topic>> getAllTopics(UntilValue until) {
//		
//	}
//	
	
	
//	public Optional<List<PedagogicalObjective>> getAllPedagogicalObjectives(UntilValue until) {
//		List<PedagogicalObjective> objectives;
//		
//		try {
//			ResponseEntity<PedagogicalObjectiveDto[]> re = this.restTemplate.getForEntity(this.pedagogicalObjectiveBaseUrl, PedagogicalObjectiveDto[].class);
//			PedagogicalObjectiveDto[] dtos = re.getBody();
//			if (dtos.length == 0) objectives = null;
//			else objectives = PedagogicalObjectiveDto.convertList(Arrays.asList(dtos));
//		} catch (ResourceAccessException e) {
//			objectives = null;
//		} catch (HttpClientErrorException e) {
//			if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) objectives = null;
//			else throw e;
//		}
//		
//		return Optional.ofNullable(objectives);
//	}	
//	public Optional<PedagogicalObjective> getPedagogicalObjective(Integer id, Boolean getAllTree) {
//		PedagogicalObjective objective;
//		
//		String url = this.pedagogicalObjectiveBaseUrl + "/" + id.toString();
//		if (getAllTree) url = url + "?getAllTree=true";
//		try {
//			ResponseEntity<PedagogicalObjectiveDto> re = this.restTemplate.getForEntity(url, PedagogicalObjectiveDto.class);
//			PedagogicalObjectiveDto dto = re.getBody();
//			objective = dto.convert();
//		} catch (ResourceAccessException e) {
//			objective = null;
//		} catch (HttpClientErrorException e) {
//			objective = null;
//		}
//		
//		return Optional.ofNullable(objective);
//	}
//	
//	
//	
//	public Optional<List<Notion>> getAllNotions() {
//		List<Notion> notions;
//		
//		try {
//			ResponseEntity<NotionDto[]> re = this.restTemplate.getForEntity(this.notionBaseUrl, NotionDto[].class);
//			NotionDto[] dtos = re.getBody();
//			if (dtos.length == 0) notions = null;
//			else notions = NotionDto.convertList(Arrays.asList(dtos));
//		} catch (ResourceAccessException e) {
//			notions = null;
//		}
//		
//		return Optional.ofNullable(notions);
//	}
//	public Optional<Notion> getNotion(Integer id, Boolean getAllTree) {
//		Notion notion;
//		
//		String url = this.notionBaseUrl + "/" + id.toString();
//		if (getAllTree) url = url + "?getAllTree=true";
//		try {
//			ResponseEntity<NotionDto> re = this.restTemplate.getForEntity(url, NotionDto.class);
//			NotionDto dto = re.getBody();
//			notion = dto.convert();
//		} catch (ResourceAccessException e) {
//			notion = null;
//		} catch (HttpClientErrorException e) {
//			notion = null;
//		}
//		
//		return Optional.ofNullable(notion);
//	}
//	
//	
//	
//	public Optional<List<Concept>> getAllConcepts() {
//		List<Concept> concepts;
//		
//		try {
//			ResponseEntity<ConceptDto[]> re = this.restTemplate.getForEntity(this.conceptBaseUrl, ConceptDto[].class);
//			ConceptDto[] dtos = re.getBody();
//			if (dtos.length == 0) concepts = null;
//			else concepts = ConceptDto.convertList(Arrays.asList(dtos));
//		} catch (ResourceAccessException e) {
//			concepts = null;
//		}
//		
//		return Optional.ofNullable(concepts);
//	}
//	public Optional<Concept> getConcept(Integer id, Boolean getAllTree) {
//		Concept concept;
//		
//		String url = this.conceptBaseUrl + "/" + id.toString();
//		if (getAllTree) url = url + "?getAllTree=true";
//		try {
//			ResponseEntity<ConceptDto> re = this.restTemplate.getForEntity(url, ConceptDto.class);
//			ConceptDto dto = re.getBody();
//			concept = dto.convert();
//		} catch (ResourceAccessException e) {
//			concept = null;
//		} catch (HttpClientErrorException e) {
//			concept = null;
//		}
//		
//		return Optional.ofNullable(concept);
//	}
//	
//	
//	
//	public Optional<List<Activity>> getAllActivities() {
//		List<Activity> activities;
//		
//		try {
//			ResponseEntity<ActivityDto[]> re = this.restTemplate.getForEntity(this.activitybaseUrl, ActivityDto[].class);
//			ActivityDto[] dtos = re.getBody();
//			if (dtos.length == 0) activities = null;
//			else activities = ActivityDto.convertList(Arrays.asList(dtos));
//		} catch (ResourceAccessException e) {
//			activities = null;
//		}
//		
//		return Optional.ofNullable(activities);
//	}
//	public Optional<Activity> getActivity(Integer id, Boolean getAllTree) {
//		Activity activity;
//		
//		String url = this.activitybaseUrl + "/" + id.toString();
//		if (getAllTree) url = url + "?getAllTree=true";
//		try {
//			ResponseEntity<ActivityDto> re = this.restTemplate.getForEntity(url, ActivityDto.class);
//			ActivityDto dto = re.getBody();
//			activity = dto.convert();
//		} catch (ResourceAccessException e) {
//			activity = null;
//		} catch (HttpClientErrorException e) {
//			activity = null;
//		}
//		
//		return Optional.ofNullable(activity);
//	}
}

