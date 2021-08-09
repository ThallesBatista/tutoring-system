package br.com.sembous.emconsumerapi.dto;

import br.com.sembous.emconsumerapi.model.Notion;

public class NotionSimpleDto implements SimpleDto<Notion> {

	private Integer id;
	private String name;
	
	@Override
	public Notion convert() {
		return new Notion(id, name);
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
}
