package br.com.sembous.teachermoduleapi.dto;

import br.com.sembous.teachermoduleapi.model.Teacher;

public class TeacherDto {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private ClassManagerDto classManagerDto;
	
	public Teacher convert() {
		if (classManagerDto==null) return new Teacher(id, firstName, lastName, email);
		return new Teacher(id, firstName, lastName, email, classManagerDto.convert());
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
	public void setClassManagerDto(ClassManagerDto classManagerDto) {
		this.classManagerDto = classManagerDto;
	}
}
