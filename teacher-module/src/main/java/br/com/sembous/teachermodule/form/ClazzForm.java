package br.com.sembous.teachermodule.form;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.sembous.teachermodule.model.Clazz;

public class ClazzForm {

	@NotNull @Length(max = 50)
	private String name;
	@NotNull
	private LocalDate createdAt;
	
	public Clazz convert() {
		return new Clazz(name, createdAt);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}	
}
