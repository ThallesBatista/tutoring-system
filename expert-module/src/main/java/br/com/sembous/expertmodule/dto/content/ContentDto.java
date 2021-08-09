package br.com.sembous.expertmodule.dto.content;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sembous.expertmodule.model.Content;

public class ContentDto {
	
	private Integer id;
	private JsonNode content;

	public ContentDto(Content content, ObjectMapper mapper) {
		this.id = content.getId();
		this.content = content.getContentJson(mapper);
	}
	

	public Integer getId() {
		return id;
	}
	public JsonNode getContent() {
		return content;
	}	
}
