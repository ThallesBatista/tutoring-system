package br.com.sembous.tutoringmodule.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.sembous.teachermoduleapi.gateway.ClazzGateway;
import br.com.sembous.teachermoduleapi.gateway.ClazzLearningPlanGateway;
import br.com.sembous.teachermoduleapi.gateway.TeacherGateway;
import br.com.sembous.teachermoduleapi.gateway.TeacherInformations;
import br.com.sembous.teachermoduleapi.gateway.TeacherModuleGateway;
import br.com.sembous.teachermoduleapi.model.Clazz;
import br.com.sembous.teachermoduleapi.model.ClazzLearningPlanPiece;
import br.com.sembous.teachermoduleapi.model.Teacher;
import br.com.sembous.tutoringmodule.dto.newClassForm;
import br.com.sembous.tutoringmodule.service.util.ClazzLearningPlanGraphCreator;

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

	public Boolean removeClass(Integer id) {
		ClazzGateway clazzGateway = TeacherModuleGateway.getClazzGateway(restTemplate);		
		return clazzGateway.remove(id);
	}

	public Optional<Clazz> getClazz(Integer classId) {
		ClazzGateway clazzGateway = TeacherModuleGateway.getClazzGateway(restTemplate);
		return clazzGateway.get(classId);
	}

	public void addLearningPlan(Integer classId, Integer objectiveId) {
		ClazzLearningPlanGraphCreator clpGraphCreator = new ClazzLearningPlanGraphCreator(restTemplate);
		try {
			ClazzLearningPlanPiece lPGraph = clpGraphCreator.createFromExpertModule(objectiveId);
			ClazzLearningPlanGateway clpGateway = TeacherModuleGateway.getClazzLearningPlanGateway(restTemplate);
			clpGateway.create(lPGraph, classId);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Boolean removeLearningPlan(Integer learningPlanId) {
		ClazzLearningPlanGateway clp = TeacherModuleGateway.getClazzLearningPlanGateway(restTemplate);
		return clp.remove(learningPlanId);
	}

}
