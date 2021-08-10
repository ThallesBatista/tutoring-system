package br.com.sembous.smconsumerapi.dto;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.sembous.smconsumerapi.model.PreferenceType;
import br.com.sembous.smconsumerapi.model.Student;

public class StudentDto {

	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private Instant preferencesUpdatedAt;
	private String likesExercises;
	private String likesTheChatbot;
	private String likesVideos;
	private String needsMoreTime;
	private Set<LearningPlanSimpleDto> learningPlans;
	
	public Student convert() {
		if (this.preferencesUpdatedAt == null && learningPlans == null) 
			return new Student(id, firstName, lastName, email);
		if (this.preferencesUpdatedAt == null)
			return new Student(id, firstName, lastName, email, 
					this.learningPlans.stream().map(LearningPlanSimpleDto::convert).collect(Collectors.toSet()));
		if (learningPlans == null)
			return new Student(id, firstName, lastName, email, PreferenceType.valueOf(likesExercises), PreferenceType.valueOf(needsMoreTime), 
					PreferenceType.valueOf(likesVideos), PreferenceType.valueOf(likesTheChatbot), preferencesUpdatedAt);
		return new Student(id, firstName, lastName, email, PreferenceType.valueOf(likesExercises), PreferenceType.valueOf(needsMoreTime), 
				PreferenceType.valueOf(likesVideos), PreferenceType.valueOf(likesTheChatbot), preferencesUpdatedAt,
				this.learningPlans.stream().map(LearningPlanSimpleDto::convert).collect(Collectors.toSet()));
	}
	
//	public static List<Student> convertList(Collection<StudentDto> dtos){
//		return dtos.stream().map(StudentDto::convert).collect(Collectors.toList());
//	}
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPreferencesUpdatedAt(Instant preferencesUpdatedAt) {
		this.preferencesUpdatedAt = preferencesUpdatedAt;
	}
	public void setLikesExercises(String likesExercises) {
		this.likesExercises = likesExercises;
	}
	public void setLikesTheChatbot(String likesTheChatbot) {
		this.likesTheChatbot = likesTheChatbot;
	}
	public void setLikesVideos(String likesVideos) {
		this.likesVideos = likesVideos;
	}
	public void setNeedsMoreTime(String needsMoreTime) {
		this.needsMoreTime = needsMoreTime;
	}
	public void setLearningPlans(Set<LearningPlanSimpleDto> learningPlans) {
		this.learningPlans = learningPlans;
	}
}
