package br.com.sembous.teachermoduleapi.gateway;

import org.springframework.web.client.RestTemplate;

public interface TeacherModuleGateway {

	public static TeacherGateway getTeacherGateway(RestTemplate restTemplate) {
		return new TeacherGateway(restTemplate);
	}
}
