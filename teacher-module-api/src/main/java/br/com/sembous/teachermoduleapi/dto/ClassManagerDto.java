package br.com.sembous.teachermoduleapi.dto;

import java.util.Set;
import java.util.stream.Collectors;

import br.com.sembous.teachermoduleapi.model.ClazzManager;

public class ClassManagerDto {

	private Set<ClazzSimpleDto> classes;
	
	public ClazzManager convert() {
		return new ClazzManager(this.classes.stream().map(ClazzSimpleDto::convert).collect(Collectors.toSet()));
	}
	
	public void setClasses(Set<ClazzSimpleDto> classes) {
		this.classes = classes;
	}
}
