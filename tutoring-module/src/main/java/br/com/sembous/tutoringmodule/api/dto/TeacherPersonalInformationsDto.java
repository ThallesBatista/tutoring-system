package br.com.sembous.tutoringmodule.api.dto;

import br.com.sembous.teachermoduleapi.model.Teacher;

public class TeacherPersonalInformationsDto {

	private Integer id;
	private String firstName;
	private String lastName;
//	private String email;
	
	public TeacherPersonalInformationsDto(Teacher teacher) {
		this.id = teacher.getId();
		this.firstName = teacher.getPersonalInformations().getFirstName();
		this.lastName = teacher.getPersonalInformations().getLastName();
//		this.email = teacher.getPersonalInformations().getEmail();
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
//	public String getEmail() {
//		return email;
//	}
}
