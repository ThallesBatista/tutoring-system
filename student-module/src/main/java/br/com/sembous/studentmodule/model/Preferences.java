package br.com.sembous.studentmodule.model;

import java.time.Instant;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Preferences {

	@Enumerated(EnumType.STRING)
	private PreferenceType likesExercises = PreferenceType.NEUTRAL_OR_UNDETECTED;
	@Enumerated(EnumType.STRING)
	private PreferenceType needsMoreTime = PreferenceType.NEUTRAL_OR_UNDETECTED;
	@Enumerated(EnumType.STRING)
	private PreferenceType likesVideos = PreferenceType.NEUTRAL_OR_UNDETECTED;
	@Enumerated(EnumType.STRING)
	private PreferenceType likesTheChatbot = PreferenceType.NEUTRAL_OR_UNDETECTED;
	private Instant preferencesUpdatedAt = Instant.now();
	
	
	public Preferences() { }
		
	
	public void setLikesExercises(PreferenceType likesExercises) {
		if (likesExercises.equals(this.likesExercises)) return;
		this.likesExercises = likesExercises;
		preferencesUpdatedAt = Instant.now();
	}
	public void setNeedsMoreTime(PreferenceType needsMoreTime) {
		if (needsMoreTime.equals(this.needsMoreTime)) return;
		this.needsMoreTime = needsMoreTime;
		preferencesUpdatedAt = Instant.now();
	}
	public void setLikesVideos(PreferenceType likesVideos) {
		if (likesVideos.equals(this.likesVideos)) return;
		this.likesVideos = likesVideos;
		preferencesUpdatedAt = Instant.now();
	}
	public void setLikesTheChatbot(PreferenceType likesTheChatbot) {
		if (likesTheChatbot.equals(this.likesTheChatbot)) return;
		this.likesTheChatbot = likesTheChatbot;
		preferencesUpdatedAt = Instant.now();
	}
	
	
	public Instant getPreferencesLastModification() {
		return preferencesUpdatedAt;
	}
	public PreferenceType likesExercises() {
		return likesExercises;
	}
	public PreferenceType needsMoreTime() {
		return needsMoreTime;
	}
	public PreferenceType likesVideos() {
		return likesVideos;
	}
	public PreferenceType likesTheChatbot() {
		return likesTheChatbot;
	}	
}
