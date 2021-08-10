package br.com.sembous.smconsumerapi.gateway;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import br.com.sembous.smconsumerapi.dto.InteractionDto;
import br.com.sembous.smconsumerapi.model.Interaction;
import br.com.sembous.smconsumerapi.model.KnowledgeType;

public class InteractionGateway {

	private final RestTemplate restTemplate;
	private final String baseUrl = "http://localhost:8082/interaction";
	
	InteractionGateway(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	
	public List<Interaction> get(Integer studentId, KnowledgeType type, Integer expertModuleId) {
		List<Interaction> list;
		String url = this.baseUrl + "/knowledge/" + studentId.toString() + "/" + type.toString() + "/" + expertModuleId.toString();
		try {
			ResponseEntity<InteractionDto[]> re = this.restTemplate.getForEntity(url, InteractionDto[].class);
			InteractionDto[] dtos = re.getBody();
			list = InteractionDto.convertAsList(Arrays.asList(dtos));
		} catch (ResourceAccessException e) {
			list = List.of();
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) list = List.of();
			else throw e;
		}
		
		return list;
	}
	
}
