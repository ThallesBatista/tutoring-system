package br.com.sembous.tutoringmodule.service.util;

public enum StaticContentJson {

	NEW_STUDENT("/static/neverSetObjective.json"),
	NO_MORE_ACTIVITIES("/static/noMoreActivitiesToDo.json"),
	CONTENT_NOT_FOUND("/static/contentNotFound.json");
	
	private final String filename;
	
	StaticContentJson(String filename) {
		this.filename = filename;
	}
	
	public String getFilename() {
		return this.filename;
	}
}
