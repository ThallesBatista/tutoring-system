package br.com.sembous.smconsumerapi.model;

import java.time.Instant;

public class Preferences {

	private PreferenceType likesExercises;
	private PreferenceType needsMoreTime;
	private PreferenceType likesVideos;
	private PreferenceType likesTheChatbot;
	private Instant preferencesUpdatedAt;
	
	Preferences(PreferenceType likesExercises, PreferenceType needsMoreTime, PreferenceType likesVideos, 
			PreferenceType likesTheChatbot, Instant preferencesUpdatedAt) {
		this.likesExercises = likesExercises;
		this.needsMoreTime = needsMoreTime;
		this.likesVideos = likesVideos;
		this.likesTheChatbot = likesTheChatbot;
		this.preferencesUpdatedAt = preferencesUpdatedAt;
	}
	
	public PreferenceType getLikesExercises() {
		return likesExercises;
	}
	public PreferenceType getNeedsMoreTime() {
		return needsMoreTime;
	}
	public PreferenceType getLikesVideos() {
		return likesVideos;
	}
	public PreferenceType getLikesTheChatbot() {
		return likesTheChatbot;
	}
	public Instant getPreferencesUpdatedAt() {
		return preferencesUpdatedAt;
	}
}
