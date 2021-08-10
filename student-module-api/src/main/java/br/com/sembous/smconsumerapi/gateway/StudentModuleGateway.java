package br.com.sembous.smconsumerapi.gateway;

import org.springframework.web.client.RestTemplate;

public interface StudentModuleGateway {
	
	public static StudentGateway getStudentGateway(RestTemplate restTemplate) {
		return new StudentGateway(restTemplate);
	}
	
	public static LearningPlanGateway getLearningPlanGateway(RestTemplate restTemplate) {
		return new LearningPlanGateway(restTemplate);
	}
	
	public static InteractionGateway getInteractionGateway(RestTemplate restTemplate) {
		return new InteractionGateway(restTemplate);
	}
	
}
