package br.com.sembous.studentmodule.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.sembous.studentmodule.model.Interaction;
import br.com.sembous.studentmodule.model.InteractionType;
import br.com.sembous.studentmodule.model.PreferenceType;
import br.com.sembous.studentmodule.model.Student;
import br.com.sembous.studentmodule.repository.InteractionRepository;
import br.com.sembous.studentmodule.repository.specifications.InteractionSpecification;

@Service
public class PreferencesDeducerService {

	@Autowired
	InteractionRepository interactionRepository;
	
	public void update(Student student) {
		this.updateLikesExercises(student);
		this.updateNeedsMoreTime(student);
		this.updateLikesVideos(student);
		this.updateLikesTheChatbot(student);
	}
	
	
	private void updateNeedsMoreTime(Student student) { //ponderar com atividades mais recentes
		Double historicalAvgScore = student.getKnowledgeManager().getAvgScore();
		Double recentAvgScore = student.getKnowledgeManager().getAvgScoreOfLast(10);
		Double performanceFactor = Double.valueOf((historicalAvgScore + recentAvgScore)/2);		
		
		if (performanceFactor >= 80) student.getPreferences().setNeedsMoreTime(PreferenceType.NO);
		else if (performanceFactor <= 60) student.getPreferences().setNeedsMoreTime(PreferenceType.YES);
		else student.getPreferences().setNeedsMoreTime(PreferenceType.NEUTRAL_OR_UNDETECTED);
	}
	
	private void updateLikesTheChatbot(Student student) {
		Integer pageNumber = Integer.valueOf(30);
		
		Set<InteractionType> types = Set.of(InteractionType.ASKED_THE_CHATBOT, InteractionType.ASKED_PER_TEXT, 
				InteractionType.SKIPPED_CHATBOT_FEEDBACK, InteractionType.COMPLY_CHATBOT_FEEDBACK);
		
		Map<InteractionType, Integer> typesValues = new HashMap<>();
		typesValues.put(InteractionType.ASKED_THE_CHATBOT, Integer.valueOf(3));
		typesValues.put(InteractionType.ASKED_PER_TEXT, Integer.valueOf(-2));
		typesValues.put(InteractionType.SKIPPED_CHATBOT_FEEDBACK, Integer.valueOf(-1));
		typesValues.put(InteractionType.COMPLY_CHATBOT_FEEDBACK, Integer.valueOf(1));
		
		Integer factor = this.basicUpdateAssistant(types, typesValues, pageNumber, student);
		
		if (factor>=7) student.getPreferences().setLikesExercises(PreferenceType.YES);
		else if (factor<=-7) student.getPreferences().setLikesExercises(PreferenceType.NO);
		else student.getPreferences().setLikesExercises(PreferenceType.NEUTRAL_OR_UNDETECTED);
		
	}

	private void updateLikesVideos(Student student) {
		Integer pageNumber = Integer.valueOf(10);
		
		Set<InteractionType> types = Set.of(InteractionType.SKIPPED_VIDEO, InteractionType.WATCHED_VIDEO, 
				InteractionType.SKIPPED_OPTIONAL_VIDEO, InteractionType.WATCHED_OPTIONAL_VIDEO);
		
		Map<InteractionType, Integer> typesValues = new HashMap<>();
		typesValues.put(InteractionType.SKIPPED_VIDEO, Integer.valueOf(-1));
		typesValues.put(InteractionType.WATCHED_VIDEO, Integer.valueOf(1));
		typesValues.put(InteractionType.SKIPPED_OPTIONAL_VIDEO, Integer.valueOf(-1));
		typesValues.put(InteractionType.WATCHED_OPTIONAL_VIDEO, Integer.valueOf(1));
		
		Integer factor = this.basicUpdateAssistant(types, typesValues, pageNumber, student);
		
		if (factor>=4) student.getPreferences().setLikesExercises(PreferenceType.YES);
		else if (factor<=-4) student.getPreferences().setLikesExercises(PreferenceType.NO);
		else student.getPreferences().setLikesExercises(PreferenceType.NEUTRAL_OR_UNDETECTED);
		
	}

	private void updateLikesExercises(Student student) {
		Integer pageNumber = Integer.valueOf(20);
		
		Set<InteractionType> types = Set.of(InteractionType.ACCEPTED_CHALLENGE, InteractionType.ACCEPTED_OPTIONAL_EXERCISE, 
				InteractionType.REFUSED_OPTIONAL_EXERCISE, InteractionType.REFUSED_CHALLENGE);
		
		Map<InteractionType, Integer> typesValues = new HashMap<>();
		typesValues.put(InteractionType.ACCEPTED_CHALLENGE, Integer.valueOf(1));
		typesValues.put(InteractionType.ACCEPTED_OPTIONAL_EXERCISE, Integer.valueOf(1));
		typesValues.put(InteractionType.REFUSED_CHALLENGE, Integer.valueOf(-1));
		typesValues.put(InteractionType.REFUSED_OPTIONAL_EXERCISE, Integer.valueOf(-1));
		
		Integer factor = this.basicUpdateAssistant(types, typesValues, pageNumber, student);
		
		if (factor>=5) student.getPreferences().setLikesExercises(PreferenceType.YES);
		else if (factor<=-5) student.getPreferences().setLikesExercises(PreferenceType.NO);
		else student.getPreferences().setLikesExercises(PreferenceType.NEUTRAL_OR_UNDETECTED);
	}
	
	private Integer basicUpdateAssistant(Set<InteractionType> types, Map<InteractionType, Integer> typesValues, Integer interactionsNumber, Student student) {
		Pageable page = PageRequest.of(0, interactionsNumber, Sort.by(Sort.Direction.DESC, "createdAt"));
		
		Specification<Interaction> where = Specification.where(InteractionSpecification.student(student).
				and(InteractionSpecification.typeIn(types))
				);
		
		Page<Interaction> interactions = interactionRepository.findAll(where, page);
		int sum = interactions.stream()
			.map(Interaction::getType)
			.mapToInt(typesValues::get)
			.sum();
		
		return sum;
	}
}
