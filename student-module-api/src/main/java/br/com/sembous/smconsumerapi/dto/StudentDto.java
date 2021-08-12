package br.com.sembous.smconsumerapi.dto;

import java.time.Instant;

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
	private LearningPlanManagerDto learningPlanManager;
	private KnowledgeDoneManagerDto knowledgeDoneManager;
	
	public Student convert() {
		Student student;
		if (this.preferencesUpdatedAt == null) student = new Student(id, firstName, lastName, email);
		else student = new Student(id, firstName, lastName, email, PreferenceType.valueOf(likesExercises), PreferenceType.valueOf(needsMoreTime), 
				PreferenceType.valueOf(likesVideos), PreferenceType.valueOf(likesTheChatbot), preferencesUpdatedAt);
		
		if (learningPlanManager != null) student.setLearningPlanManager(learningPlanManager.convert());
		if (knowledgeDoneManager != null) student.setKnowledgeDoneManager(knowledgeDoneManager.convert());		
		
		return student;
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
	public void setLearningPlanManager(LearningPlanManagerDto learningPlanManager) {
		this.learningPlanManager = learningPlanManager;
	}
	public void setKnowledgeDoneManager(KnowledgeDoneManagerDto knowledgeDoneManager) {
		this.knowledgeDoneManager = knowledgeDoneManager;
	}
}
