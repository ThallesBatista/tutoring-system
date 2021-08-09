package br.com.sembous.smconsumerapi.model;

import java.time.Instant;
import java.util.Optional;

public class Student {

	private Integer id;
	private PersonalInformations personalInformations;
	private LearningPlanManager learningPlanManager;
	private Preferences preferences;
	
	
	public Student(String firstName, String lastName, String email) {
		this.personalInformations = new PersonalInformations(firstName, lastName, email);
	}
	public Student(Integer id, String firstName, String lastName, String email) {
		this.id = id;
		this.personalInformations = new PersonalInformations(firstName, lastName, email);
	}
	public Student(Integer id, String firstName, String lastName, String email, Instant learningPlanUpdateAt, LearningPlanPiece pedagogicalLPP) {
		this.id = id;
		this.personalInformations = new PersonalInformations(firstName, lastName, email);
		this.learningPlanManager = new LearningPlanManager(learningPlanUpdateAt, pedagogicalLPP, this);
	}
	public Student(Integer id, String firstName, String lastName, String email, 
			PreferenceType likesExercises, PreferenceType needsMoreTime, PreferenceType likesVideos, 
			PreferenceType likesTheChatbot, Instant preferencesUpdatedAt) {
		this.id = id;
		this.personalInformations = new PersonalInformations(firstName, lastName, email);
		this.preferences = new Preferences(likesExercises, needsMoreTime, likesVideos, likesTheChatbot, preferencesUpdatedAt);
	}
	
	
	public Integer getId() {
		return id;
	}
	public PersonalInformations getPersonalInformations() {
		return personalInformations;
	}
	public Optional<LearningPlanManager> getLearningPlanManager() {
		return Optional.ofNullable(learningPlanManager);
	}
	public Optional<Preferences> getPreferences() {
		return Optional.ofNullable(preferences);
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		
		return true;
	}
}
