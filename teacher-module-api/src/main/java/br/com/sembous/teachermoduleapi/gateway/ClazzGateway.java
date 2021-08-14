package br.com.sembous.teachermoduleapi.gateway;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import br.com.sembous.teachermoduleapi.dto.ClazzDto;
import br.com.sembous.teachermoduleapi.dto.ClazzSimpleDto;
import br.com.sembous.teachermoduleapi.form.ClazzForm;
import br.com.sembous.teachermoduleapi.model.Clazz;

public class ClazzGateway {

	private final RestTemplate restTemplate;
	private final String baseUrl = "http://localhost:8084/class";
	
	ClazzGateway(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	
	public Optional<Clazz> create(Clazz clazz, Integer teacherId) {
		ClazzForm form = new ClazzForm(clazz);
		String url = this.baseUrl + "?teacherId=" + teacherId.toString();
		Clazz clazzResponse;
		try {
			ResponseEntity<ClazzSimpleDto> re = this.restTemplate.postForEntity(url, form, ClazzSimpleDto.class);
			ClazzSimpleDto dto = re.getBody();
			clazzResponse = dto.convert();
		} catch (ResourceAccessException e) {
			clazzResponse = null;
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) clazzResponse = null;
			else throw e;
		}
		
		return Optional.ofNullable(clazzResponse);
	}


	public Boolean remove(Integer id) {
		String url = this.baseUrl + "/" + id.toString();
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


	public Optional<Clazz> get(Integer id) {
		String url = this.baseUrl + "/" + id.toString();
		Clazz clazz;
		try {
			ResponseEntity<ClazzDto> re = this.restTemplate.getForEntity(url, ClazzDto.class);
			ClazzDto dto = re.getBody();
			clazz = dto.convert();
		} catch (ResourceAccessException e) {
			clazz = null;
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) clazz = null;
			else throw e;
		}
		
		return Optional.ofNullable(clazz);
	}
}
