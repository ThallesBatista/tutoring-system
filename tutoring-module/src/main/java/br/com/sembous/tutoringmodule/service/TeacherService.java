package br.com.sembous.tutoringmodule.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.sembous.teachermoduleapi.gateway.TeacherGateway;
import br.com.sembous.teachermoduleapi.gateway.TeacherInformations;
import br.com.sembous.teachermoduleapi.gateway.TeacherModuleGateway;
import br.com.sembous.teachermoduleapi.model.Teacher;

@Service
public class TeacherService {

	private RestTemplate restTemplate = new RestTemplate();
	
	public Optional<Teacher> getTeacher(Integer foreignId) {
		TeacherGateway teacherGateway = TeacherModuleGateway.getTeacherGateway(restTemplate);
		return teacherGateway.get(foreignId, TeacherInformations.PERSONAL_INFORMATIONS);
	}
	
	public Optional<Teacher> getTeacherWithSimplifiedClasses(Integer foreignId) {
		TeacherGateway teacherGateway = TeacherModuleGateway.getTeacherGateway(restTemplate);
		return teacherGateway.get(foreignId, TeacherInformations.CLASSES);
	}

}
