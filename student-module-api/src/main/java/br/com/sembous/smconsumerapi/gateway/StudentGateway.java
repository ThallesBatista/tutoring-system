package br.com.sembous.smconsumerapi.gateway;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import br.com.sembous.smconsumerapi.dto.StudentDto;
import br.com.sembous.smconsumerapi.form.StudentForm;
import br.com.sembous.smconsumerapi.model.Student;

public final class StudentGateway {

	private final RestTemplate restTemplate;
	private final String baseUrl = "http://localhost:8082/student";
	
	StudentGateway(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	
	public Optional<Student> get(Integer id, Boolean withPreferences, Boolean withLearningPlan) {
		Student student;
		String url = this.baseUrl + "/" + id.toString() 
			+ "?withPreferences=" + withPreferences.toString() 
			+ "+withLearningPlan=" + withLearningPlan.toString();
		try {
			ResponseEntity<StudentDto> re = this.restTemplate.getForEntity(url, StudentDto.class);
			StudentDto dto = re.getBody();
			student = dto.convert();
		} catch (ResourceAccessException e) {
			student = null;
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) student = null;
			else throw e;
		}
		
		return Optional.ofNullable(student);
	}
	
	
	public Optional<Student> create(Student student) {
		StudentForm form = new StudentForm(student);
		String url = this.baseUrl;
		Student studentResponse;
		try {
			ResponseEntity<StudentDto> re = this.restTemplate.postForEntity(url, form, StudentDto.class);
			StudentDto dto = re.getBody();
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
