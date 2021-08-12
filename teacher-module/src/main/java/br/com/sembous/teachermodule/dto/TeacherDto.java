package br.com.sembous.teachermodule.dto;

import br.com.sembous.teachermodule.controller.TeacherInformations;
import br.com.sembous.teachermodule.model.Teacher;

public class TeacherDto {

	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private ClassManagerDto classManagerDto;
	
	public TeacherDto(Teacher teacher, TeacherInformations informations) {
		this.id = teacher.getId();
		this.firstName = teacher.getPersonalInformations().getFirstName();
		this.lastName = teacher.getPersonalInformations().getLastName();
		this.email = teacher.getPersonalInformations().getEmail();
		
		if (informations.equals(TeacherInformations.CLASSES)) {
			this.classManagerDto = new ClassManagerDto(teacher.getClazzManager());
		}
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
	public ClassManagerDto getClassManagerDto() {
		return classManagerDto;
	}
}
