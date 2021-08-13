package br.com.sembous.tutoringmodule.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.sembous.teachermoduleapi.gateway.ClazzGateway;
import br.com.sembous.teachermoduleapi.gateway.TeacherGateway;
import br.com.sembous.teachermoduleapi.gateway.TeacherInformations;
import br.com.sembous.teachermoduleapi.gateway.TeacherModuleGateway;
import br.com.sembous.teachermoduleapi.model.Clazz;
import br.com.sembous.teachermoduleapi.model.Teacher;
import br.com.sembous.tutoringmodule.dto.newClassForm;

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

	public void createClass(newClassForm form, Integer teacherId) {
		Clazz clazz = form.convert();
		ClazzGateway clazzGateway = TeacherModuleGateway.getClazzGateway(restTemplate);
		clazzGateway.create(clazz, teacherId);
	}

	public void removeClass(Integer id) {
		ClazzGateway clazzGateway = TeacherModuleGateway.getClazzGateway(restTemplate);		
		clazzGateway.remove(id);
	}

}
