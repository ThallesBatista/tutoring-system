package br.com.sembous.teachermoduleapi.dto;

import br.com.sembous.teachermoduleapi.model.Teacher;

public class TeacherDto {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	
	public Teacher convert() {
		return new Teacher(id, firstName, lastName, email);
	}
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
