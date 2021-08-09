package br.com.sembous.smconsumerapi.form;

import br.com.sembous.smconsumerapi.model.Student;

public class StudentForm {

	private String firstName;
	private String lastName;
	private String email;
	
	public StudentForm(Student student) {
		this.firstName = student.getPersonalInformations().getFirstName();
		this.lastName = student.getPersonalInformations().getLastName();
		this.email = student.getPersonalInformations().getEmail();
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
