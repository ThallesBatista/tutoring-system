package br.com.sembous.teachermoduleapi.gateway;

import org.springframework.web.client.RestTemplate;

public interface TeacherModuleGateway {

	public static TeacherGateway getTeacherGateway(RestTemplate restTemplate) {
		return new TeacherGateway(restTemplate);
	}
	
	public static ClazzGateway getClazzGateway(RestTemplate restTemplate) {
		return new ClazzGateway(restTemplate);
	}
	
	public static ClazzLearningPlanGateway getClazzLearningPlanGateway(RestTemplate restTemplate) {
		return new ClazzLearningPlanGateway(restTemplate);
	}
}
