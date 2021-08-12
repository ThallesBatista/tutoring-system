package br.com.sembous.tutoringmodule.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.sembous.smconsumerapi.model.Student;
import br.com.sembous.teachermoduleapi.model.Teacher;
import br.com.sembous.tutoringmodule.config.security.Role;
import br.com.sembous.tutoringmodule.config.security.RoleValue;
import br.com.sembous.tutoringmodule.config.security.User;
import br.com.sembous.tutoringmodule.config.validation.ValueOfEnum;

public class SignupForm {
	@NotNull @Length(max = 19)
	private String firstName;
	@NotNull @Length(max = 49)
	private String lastName;
	@NotNull @Email
	private String email;
	@NotNull @Length(max = 29)
	private String username;
	@NotNull @Length(max = 49)
	private String password;
	@NotNull @ValueOfEnum(enumClass = RoleValue.class)
	private String role;
	
	
	public User convert(Integer foreignId, Set<Role> roles) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return new User(username, encoder.encode(password), roles, foreignId);
	}
	public Student convertToStudent() {
		return new Student(firstName, lastName, email);
	}
	public Teacher convertToTeacher() {
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
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public RoleValue getRole() {
		return RoleValue.valueOf(role);
	}
}
