package br.com.sembous.smconsumerapi.gateway;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import br.com.sembous.smconsumerapi.dto.InteractionSimpleDto;
import br.com.sembous.smconsumerapi.dto.StudentDto;
import br.com.sembous.smconsumerapi.dto.StudentWithLearningPlanDto;
import br.com.sembous.smconsumerapi.form.LearningPlanForm;
import br.com.sembous.smconsumerapi.form.LearningPlanPieceDoneForm;
import br.com.sembous.smconsumerapi.form.StudentForm;
import br.com.sembous.smconsumerapi.model.Interaction;
import br.com.sembous.smconsumerapi.model.LearningPlanPiece;
import br.com.sembous.smconsumerapi.model.Student;

public class StudentModuleGateway {

	private String studentBaseUrl;
	private String learningPlanBaseUrl;
//	private String preferencesBaseUrl;
//	private String knowledgeDoneBaseUrl;
	private String interactionBaseUrl;
	private RestTemplate restTemplate;
	
	public StudentModuleGateway(RestTemplate restTemplate) {
		this.studentBaseUrl = "http://localhost:8082/student";
		this.learningPlanBaseUrl = "http://localhost:8082/learningPlan";
//		this.preferencesBaseUrl = "http://localhost:8082/preferences";
//		this.knowledgeDoneBaseUrl = "http://localhost:8082/knowledgedone";
		this.interactionBaseUrl = "http://localhost:8082/interaction";
		this.restTemplate = restTemplate;
	}
	

	public Optional<Student> getStudent(Integer id) {
		Student student;
		String url = this.studentBaseUrl + "/" + id.toString();
		try {
			ResponseEntity<StudentDto> re = this.restTemplate.getForEntity(url, StudentDto.class);
			StudentDto dto = re.getBody();
//			if (dtos.length == 0) students = null;
//			else students = StudentDto.convertList(Arrays.asList(dtos));
			student = dto.convert();
		} catch (ResourceAccessException e) {
			student = null;
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) student = null;
			else throw e;
		}
		
		return Optional.ofNullable(student);
	}	
	public Optional<Student> postStudent(Student student) {
		StudentForm form = new StudentForm(student);
		String url = this.studentBaseUrl;
		Student studentResponse;
		try {
			ResponseEntity<StudentDto> re = this.restTemplate.postForEntity(url, form, StudentDto.class);
			StudentDto dto = re.getBody();
//			if (dtos.length == 0) students = null;
//			else students = StudentDto.convertList(Arrays.asList(dtos));
			studentResponse = dto.convert();
		} catch (ResourceAccessException e) {
			studentResponse = null;
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) studentResponse = null;
			else throw e;
		}
		
		return Optional.ofNullable(studentResponse);
	}
	
	public void update(Student student, LearningPlanPiece pedagogicalObjectivePiece) {
		LearningPlanForm form = new LearningPlanForm(pedagogicalObjectivePiece);
		String url = this.learningPlanBaseUrl + "/" + student.getId().toString();
		
		try {
			this.restTemplate.put(url,form);
		} catch (ResourceAccessException e) { 
			e.printStackTrace();
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
		}
	}
	public Optional<Student> get(Integer studentId) {
		String url = this.learningPlanBaseUrl + "/" + studentId;
		Student student = null;
		
		try {
			ResponseEntity<StudentWithLearningPlanDto> re = this.restTemplate.getForEntity(url, StudentWithLearningPlanDto.class);
			StudentWithLearningPlanDto dto = re.getBody();
			student = dto.convert();
		} catch (ResourceAccessException e) {
			e.printStackTrace();
		} catch (HttpClientErrorException e) {
			if (!e.getStatusCode().equals(HttpStatus.NOT_FOUND))e.printStackTrace();
		}
		
		return Optional.ofNullable(student);
	}
//	public void getStudentLearningPlan(Student student) {
//		String url = this.learningPlanBaseUrl + "/" + student.getId().toString();
//		try {
//			ResponseEntity<LearningPlanDto> re = this.restTemplate.getForEntity(url, LearningPlanDto.class);
//			LearningPlanDto dto = re.getBody();
//			if (dto == null) retur
//			dto.updateStudent(student);
//		} catch (ResourceAccessException | HttpClientErrorException e) {
//			e.printStackTrace();
//		}
//	}
//	public void updateStudentLearningPlan(Student student) {
//		String url = this.learningPlanBaseUrl + "/" + student.getId().toString();
//		restTemplate.put(url, new LearningPlanUpdateForm(student));
//	}


	public Optional<Student> getStudentWithPreferences(Integer studentId) {
		Student student;
		String url = this.studentBaseUrl + "/" + studentId.toString() + "?withPreferences=true";
		try {
			ResponseEntity<StudentDto> re = this.restTemplate.getForEntity(url, StudentDto.class);
			StudentDto dto = re.getBody();
//			if (dtos.length == 0) students = null;
//			else students = StudentDto.convertList(Arrays.asList(dtos));
			student = dto.convert();
		} catch (ResourceAccessException e) {
			student = null;
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) student = null;
			else throw e;
		}
		
		return Optional.ofNullable(student);
	}
	
	public void activityDone(Integer studentId, LearningPlanPiece piece) {
		LearningPlanPieceDoneForm form = new LearningPlanPieceDoneForm(piece);
		String url = this.learningPlanBaseUrl + "/updateActivityStatus/" + studentId;
		try {
			this.restTemplate.put(url,form);
		} catch (ResourceAccessException e) { 
			e.printStackTrace();
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
		}
	}


	public List<Interaction> getInteractionWithLearningPlanPiece(Integer studentId, LearningPlanPiece currentActivity) {
		List<Interaction> interactions;
		String url = this.interactionBaseUrl + "/knowledge/" + studentId + '/' + currentActivity.getType().toString() 
				+ '/' + currentActivity.getExpertModuleId().toString();
		try {
			ResponseEntity<InteractionSimpleDto[]> re = this.restTemplate.getForEntity(url, InteractionSimpleDto[].class);
			InteractionSimpleDto[] dtosArray = re.getBody();
			List<InteractionSimpleDto> dtos = Arrays.asList(dtosArray);
			interactions = InteractionSimpleDto.converList(dtos);
		} catch (ResourceAccessException e) {
			interactions = new ArrayList<>();
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) interactions = new ArrayList<>();
			else throw e;
		}
		
		return interactions;
	}
	
	
	
//	public void getStudentPreferences(Student student) {
//		String url = this.preferencesBaseUrl + "/" + student.getId().toString();
//		
//		try {
//			ResponseEntity<StudentPreferencesDto> re = this.restTemplate.getForEntity(url, StudentPreferencesDto.class);
//			StudentPreferencesDto dto = re.getBody();
//			if (dto == null) return;
//			dto.updateStudent(student);
//		} catch (ResourceAccessException | HttpClientErrorException e) {
//			e.printStackTrace();
//		}
//	}
	
	
	
//	public void getStudentKnowledge(Student student) {
//		String url = this.knowledgeDoneBaseUrl + "/" + student.getId().toString();
//		
//		try {
//			ResponseEntity<KnowledgeDoneDto> re = this.restTemplate.getForEntity(url, KnowledgeDoneDto.class);
//			KnowledgeDoneDto dto = re.getBody();
//			if (dto == null) return;
//			dto.updateStudent(student);
//		} catch (ResourceAccessException | HttpClientErrorException e) {
//			e.printStackTrace();
//		}
//	}
//	public void addStudentKnowledge(Student student, KnowledgePiece piece) {
//		String url = this.knowledgeDoneBaseUrl + "/" + student.getId().toString();
//		this.restTemplate.postForEntity(url, new KnowledgePieceForm(piece), KnowledgePieceDto.class);
//	}
	
	
	
//	public void getStudentInteraction(Student student, Integer page) {		
//		String url = this.interactionBaseUrl + "?studentId=" + student.getId().toString() + "&page=" + page.toString();
//		
//		try {
//			ResponseEntity<InteractionDto> re = this.restTemplate.getForEntity(url, InteractionDto.class);
//			InteractionDto dto = re.getBody();
//			if (dto == null) return;
//			dto.updateStudent(student);
//		} catch (ResourceAccessException | HttpClientErrorException e) {
//			e.printStackTrace();
//		}
//	}
//	public void addStudentInteraction(Student student, Interaction interaction) {
//		String url = this.interactionBaseUrl + "?studentId=" + student.getId().toString();
//		this.restTemplate.postForEntity(url, new InteractionForm(interaction), InteractionPieceDto.class);
//	}
}
