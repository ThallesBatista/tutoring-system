package br.com.sembous.emconsumerapi.dto;

import br.com.sembous.emconsumerapi.model.PedagogicalObjective;

public class PedagogicalObjectiveSimpleDto implements SimpleDto<PedagogicalObjective> {

	private Integer id;
	private String name;
	
	@Override
	public PedagogicalObjective convert() {
		return new PedagogicalObjective(id, name);
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
}
