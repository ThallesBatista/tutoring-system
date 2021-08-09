package br.com.sembous.studentmodule.model;

import javax.persistence.Embeddable;

@Embeddable
public class PersonalInformations {

	private String firstName;
	private String lastName;
	private String email;
	
	
	public PersonalInformations() {	}
	
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
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
