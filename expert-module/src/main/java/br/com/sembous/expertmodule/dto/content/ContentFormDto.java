package br.com.sembous.expertmodule.dto.content;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.sembous.expertmodule.dto.ContentFormat;
import br.com.sembous.expertmodule.model.Content;

public class ContentFormDto {
	
	@NotNull @ContentFormat//@NotEmpty @NotBlank
	private JsonNode content;

	public Content convert () {
		return new Content(this.content.toString());
	}

	public JsonNode getContent() {
		return content;
	}



	public void setContent(JsonNode content) {
		this.content = content;
	}	
}
