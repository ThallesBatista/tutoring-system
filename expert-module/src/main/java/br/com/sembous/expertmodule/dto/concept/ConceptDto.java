package br.com.sembous.expertmodule.dto.concept;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import br.com.sembous.expertmodule.model.Concept;

public class ConceptDto {

	private Integer id;
	private String name;

	public ConceptDto(Concept concept) {
		this.id = concept.getId();
		this.name = concept.getName();
	}
	
	
	public static List<ConceptDto> convert(Collection<Concept> concepts) {
		return concepts.stream().map(ConceptDto::new).collect(Collectors.toList());
	}
	

	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}
