package br.com.sembous.studentmodule.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.sembous.studentmodule.model.Student;

public class StudentFormDto {

	@NotNull @NotBlank @NotEmpty @Length(max = 20)
	private String firstName;
	@NotNull @NotBlank @NotEmpty @Length(max = 50)
	private String lastName;
	@NotNull @NotBlank @NotEmpty @Length(max = 50) @Email
	private String email;
	
	
	public Student convert() {
		return new Student(this.firstName, this.lastName, this.email);
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
