package br.com.sembous.studentmodule.dto;

import java.time.Instant;

import br.com.sembous.studentmodule.model.Student;

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
	
	public StudentDto(Student student, Boolean withPreferences) {
		this.id = student.getId();
		this.firstName = student.getPersonalInformations().getFirstName();
		this.lastName = student.getPersonalInformations().getLastName();
		this.email = student.getPersonalInformations().getEmail();
		if (withPreferences) {
			this.preferencesUpdatedAt = student.getPreferences().getPreferencesLastModification();
			this.likesExercises = student.getPreferences().likesExercises().toString();
			this.likesTheChatbot = student.getPreferences().likesTheChatbot().toString();
			this.likesVideos = student.getPreferences().likesVideos().toString();
			this.needsMoreTime = student.getPreferences().needsMoreTime().toString();
		}
	}

	
	public Integer getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public Instant getPreferencesUpdatedAt() {
		return preferencesUpdatedAt;
	}
	public String getLikesExercises() {
		return likesExercises;
	}
	public String getLikesTheChatbot() {
		return likesTheChatbot;
	}
	public String getLikesVideos() {
		return likesVideos;
	}
	public String getNeedsMoreTime() {
		return needsMoreTime;
	}
}
