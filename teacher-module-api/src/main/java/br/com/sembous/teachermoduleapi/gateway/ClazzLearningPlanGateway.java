package br.com.sembous.teachermoduleapi.gateway;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import br.com.sembous.teachermoduleapi.dto.ClazzLearningPlanSimpleDto;
import br.com.sembous.teachermoduleapi.form.ClazzLearningPlanPieceForm;
import br.com.sembous.teachermoduleapi.model.ClazzLearningPlan;
import br.com.sembous.teachermoduleapi.model.ClazzLearningPlanPiece;

public class ClazzLearningPlanGateway {

	private final RestTemplate restTemplate;
	private final String baseUrl = "http://localhost:8084/classLearningPlan";
	
	ClazzLearningPlanGateway(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public Optional<ClazzLearningPlan> create(ClazzLearningPlanPiece lPGraph, Integer classId) {
		ClazzLearningPlanPieceForm form = new ClazzLearningPlanPieceForm(lPGraph);
		String url = this.baseUrl + "?classId=" + classId.toString();
		ClazzLearningPlan clpResponse;
		try {
			ResponseEntity<ClazzLearningPlanSimpleDto> re = this.restTemplate.postForEntity(url, form, ClazzLearningPlanSimpleDto.class);
			ClazzLearningPlanSimpleDto dto = re.getBody();
			clpResponse = dto.convert();
		} catch (ResourceAccessException e) {
			clpResponse = null;
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) clpResponse = null;
			else throw e;
		}
		
		return Optional.ofNullable(clpResponse);
		
	}

	public Boolean remove(Integer learningPlanId) {
		String url = this.baseUrl + "/" + learningPlanId.toString();
		try {
			this.restTemplate.delete(url);
			return Boolean.TRUE;
		} catch (ResourceAccessException e) {
			return Boolean.FALSE;
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) return Boolean.FALSE;
			else throw e;
		}
	}

}
