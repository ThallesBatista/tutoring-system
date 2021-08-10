package br.com.sembous.smconsumerapi.gateway;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import br.com.sembous.smconsumerapi.dto.LearningPlanDto;
import br.com.sembous.smconsumerapi.dto.LearningPlanSimpleDto;
import br.com.sembous.smconsumerapi.form.LearningPlanPieceDoneForm;
import br.com.sembous.smconsumerapi.form.LearningPlanPieceForm;
import br.com.sembous.smconsumerapi.model.LearningPlan;
import br.com.sembous.smconsumerapi.model.LearningPlanPiece;

public class LearningPlanGateway {

	private final RestTemplate restTemplate;
	private final String baseUrl = "http://localhost:8082/learningPlan";
	
	LearningPlanGateway(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	
	public Optional<LearningPlan> get(Integer id) {
		LearningPlan lp;
		String url = this.baseUrl + "/" + id.toString();
		
		try {
			ResponseEntity<LearningPlanDto> re = this.restTemplate.getForEntity(url, LearningPlanDto.class);
			LearningPlanDto dto = re.getBody();
			lp = dto.convert();
		} catch (ResourceAccessException e) {
			lp = null;
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) lp = null;
			else throw e;
		}
		
		return Optional.ofNullable(lp);		
	}
	
	
	public Optional<LearningPlan> create(LearningPlanPiece piece) {
		LearningPlanPieceForm form = new LearningPlanPieceForm(piece);
		LearningPlan lp;
		String url = this.baseUrl;
		
		try {
			ResponseEntity<LearningPlanSimpleDto> re = this.restTemplate.postForEntity(url, form, LearningPlanSimpleDto.class);
			LearningPlanSimpleDto dto = re.getBody();
			lp = dto.convert();
		} catch (ResourceAccessException e) {
			lp = null;
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) lp = null;
			else throw e;
		}
		
		return Optional.ofNullable(lp);		
	}
	
	
	public void updateActivity(LearningPlanPiece piece) {
		LearningPlanPieceDoneForm form = new LearningPlanPieceDoneForm(piece);
		String url = this.baseUrl + "/updateActivity/" + piece.getId().toString();
		try {
			this.restTemplate.put(url,form);
		} catch (ResourceAccessException e) {
			return;
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) return;
			else throw e;
		}
	}
	
	
	public void updateLearningPlan(Integer id, LearningPlanPiece piece) {
		LearningPlanPieceForm form = new LearningPlanPieceForm(piece);
		String url = this.baseUrl + "/" + id.toString();
		try {
			this.restTemplate.put(url,form);
		} catch (ResourceAccessException e) {
			return;
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) return;
			else throw e;
		}
	}
	
	public void delete(Integer id) {
		String url = this.baseUrl + "/" + id.toString();
		try {
			this.restTemplate.delete(url);
		} catch (ResourceAccessException e) {
			return;
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) return;
			else throw e;
		}
	}
}
