package br.com.sembous.tutoringmodule.dto;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import br.com.sembous.teachermoduleapi.model.Clazz;

public class newClassForm {
	
	@NotNull @Length(max = 50)
	private String name;
	
	public Clazz convert() {
		return new Clazz(name);
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
