package br.com.sembous.teachermoduleapi.form;

import br.com.sembous.teachermoduleapi.model.Teacher;

public class TeacherForm {

	private String firstName;
	private String lastName;
	private String email;
	
	public TeacherForm(Teacher teacher) {
		this.firstName = teacher.getPersonalInformations().getFirstName();
		this.lastName = teacher.getPersonalInformations().getLastName();
		this.email = teacher.getPersonalInformations().getEmail();
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
