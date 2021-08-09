package br.com.sembous.expertmodule.dto.pedagogicalobjective;

import java.util.List;
import java.util.stream.Collectors;

import br.com.sembous.expertmodule.model.PedagogicalObjective;

public class PedagogicalObjectiveDto {

	private Integer id;
	private String name;

	public PedagogicalObjectiveDto(PedagogicalObjective pedagogicalObjective) {
		this.id = pedagogicalObjective.getId();
		this.name = pedagogicalObjective.getName();
	}
	
	
	public static List<PedagogicalObjectiveDto> convert (List<PedagogicalObjective> pedagogicalObjectives) {
		return pedagogicalObjectives.stream().map(PedagogicalObjectiveDto::new).collect(Collectors.toList());
	}

	
	public Integer getId() { return this.id; }
	public String getName() { return this.name; }
}
