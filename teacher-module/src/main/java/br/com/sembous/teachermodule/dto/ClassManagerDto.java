package br.com.sembous.teachermodule.dto;

import java.util.Set;
import java.util.stream.Collectors;

import br.com.sembous.teachermodule.model.ClazzManager;

public class ClassManagerDto {

	private Set<ClazzSimpleDto> classes;
	
	public ClassManagerDto(ClazzManager clazzManager) {
		this.classes = clazzManager.getClasses().stream().map(ClazzSimpleDto::new).collect(Collectors.toSet());
	}
	
	public Set<ClazzSimpleDto> getClasses() {
		return classes;
	}

}
