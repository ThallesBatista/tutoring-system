package br.com.sembous.emconsumerapi.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.sembous.emconsumerapi.model.Concept;

public class ConceptSimpleDto implements SimpleDto<Concept> {

	private Integer id;
	private String name;
	
	@Override
	public Concept convert() {
		return new Concept(id, name);
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}

	public static List<Concept> convertList(List<ConceptSimpleDto> dependencies) {
		return dependencies.stream().map(ConceptSimpleDto::convert).collect(Collectors.toList());
	}
}
