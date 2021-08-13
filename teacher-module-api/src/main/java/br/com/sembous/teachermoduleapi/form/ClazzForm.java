package br.com.sembous.teachermoduleapi.form;

import java.time.LocalDate;

import br.com.sembous.teachermoduleapi.model.Clazz;

public class ClazzForm {

	private String name;
	private LocalDate createdAt;
	
	public ClazzForm(Clazz clazz) {
		this.name = clazz.getName();
		this.createdAt = clazz.getCreatedAt();
	}

	public String getName() {
		return name;
	}
	public LocalDate getCreatedAt() {
		return createdAt;
	}
}
