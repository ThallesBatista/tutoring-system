package br.com.sembous.expertmodule.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity @Table(name = "contents")
public class Content {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String content;
	@OneToOne(fetch = FetchType.LAZY)
	private Activity activity;
	
	public Content() {	}
	
	public Content(String content) {
		this.content = content;
	}
	
	
	public JsonNode getContentJson(ObjectMapper mapper) {
		try {
			return mapper.readTree(this.content);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return null;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	protected void update(Content content) {
		this.content = content.getContent();
	}
	
	protected void setActivity(Activity activity) {
		this.activity = activity;
	}

	
	public Integer getId() {
		return id;
	}
	public String getContent() {
		return content;
	}
	public Activity getActivity() {
		return activity;
	}	
}
