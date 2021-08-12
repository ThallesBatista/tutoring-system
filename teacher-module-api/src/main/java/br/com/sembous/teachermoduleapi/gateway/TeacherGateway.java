package br.com.sembous.teachermoduleapi.gateway;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import br.com.sembous.teachermoduleapi.dto.TeacherDto;
import br.com.sembous.teachermoduleapi.form.TeacherForm;
import br.com.sembous.teachermoduleapi.model.Teacher;

public class TeacherGateway {

	private final RestTemplate restTemplate;
	private final String baseUrl = "http://localhost:8084/teacher";
	
	TeacherGateway(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public Optional<Teacher> get(Integer id) {
		Teacher teacher;
		String url = this.baseUrl + "/" + id.toString();
		try {
			ResponseEntity<TeacherDto> re = this.restTemplate.getForEntity(url, TeacherDto.class);
			TeacherDto dto = re.getBody();
			teacher = dto.convert();
		} catch (ResourceAccessException e) {
			teacher = null;
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) teacher = null;
			else throw e;
		}
		
		return Optional.ofNullable(teacher);
	}
	
	
	public Optional<Teacher> create(Teacher teacher) {
		TeacherForm form = new TeacherForm(teacher);
		String url = this.baseUrl;
		Teacher studentResponse;
		try {
			ResponseEntity<TeacherDto> re = this.restTemplate.postForEntity(url, form, TeacherDto.class);
			TeacherDto dto = re.getBody();
			studentResponse = dto.convert();
		} catch (ResourceAccessException e) {
			studentResponse = null;
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) studentResponse = null;
			else throw e;
		}
		
		return Optional.ofNullable(studentResponse);
	}
}
