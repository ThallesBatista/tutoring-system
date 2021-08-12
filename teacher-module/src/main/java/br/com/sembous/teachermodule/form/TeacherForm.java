package br.com.sembous.teachermodule.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.sembous.teachermodule.model.Teacher;

public class TeacherForm {

	@NotNull @NotBlank @NotEmpty @Length(max = 20)
	private String firstName;
	@NotNull @NotBlank @NotEmpty @Length(max = 50)
	private String lastName;
	@NotNull @NotBlank @NotEmpty @Length(max = 50) @Email
	private String email;
	
	public Teacher convert() {
		return new Teacher(this.firstName, this.lastName, this.email);
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
