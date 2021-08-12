package br.com.sembous.teachermodule.dto;

import br.com.sembous.teachermodule.model.Teacher;

public class TeacherDto {

	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	
	public TeacherDto(Teacher teacher) {
		this.id = teacher.getId();
		this.firstName = teacher.getPersonalInformations().getFirstName();
		this.lastName = teacher.getPersonalInformations().getLastName();
		this.email = teacher.getPersonalInformations().getEmail();
	}

	public Integer getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
}
