package br.com.sembous.emconsumerapi.gateway.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.web.client.RestTemplate;

import br.com.sembous.emconsumerapi.dto.TopicDto;
import br.com.sembous.emconsumerapi.gateway.EntityGateway;
import br.com.sembous.emconsumerapi.gateway.UntilValue;
import br.com.sembous.emconsumerapi.model.Topic;

public class TopicGateway implements EntityGateway<Topic> {

	private final String base = "http://localhost:8081/topic";
//	private final RestTemplate restTemplate;
	private final GatewayUtil<Topic, TopicDto> gatewayUtil;
	
	public TopicGateway(RestTemplate restTemplate) {
//		this.restTemplate = restTemplate;
		gatewayUtil = new GatewayUtil<>(Topic.class, TopicDto.class, restTemplate);
	}
	
	@Override
	public Optional<Topic> getOne(Integer id) {
		return gatewayUtil.getOneAux(UrlUtil.getOneUrl(base, id));
	}

	@Override
	public Optional<Topic> getOne(Integer id, UntilValue until) {
		return gatewayUtil.getOneAux(UrlUtil.getOneUrl(base, id, until));
	}

	@Override
	public Optional<List<Topic>> getAll(Integer id) {
		return gatewayUtil.getAllAux(UrlUtil.getAllUrl(base));
	}

	@Override
	public Optional<List<Topic>> getAll(Integer id, UntilValue until) {
		return gatewayUtil.getAllAux(UrlUtil.getAllUrl(base, until));
	}
	
	
//	private Optional<Topic> getOneAux(String url) {
//		Topic entity;
//		try {
//			ResponseEntity<TopicDto> re = this.restTemplate.getForEntity(url, TopicDto.class);
//			TopicDto dto = re.getBody();
//			entity = dto.convert();
//		} catch (ResourceAccessException e) {
//			entity = null;
//		} catch (HttpClientErrorException e) {
//			entity = null;
//		}		
//		return Optional.ofNullable(entity);
//	}
//	
//	private Optional<List<Topic>> getAllAux(String url) {
//		List<Topic> entity;
//		try {
//			ResponseEntity<TopicDto[]> re = this.restTemplate.getForEntity(url, TopicDto[].class);
//			TopicDto[] dtos = re.getBody();
//			if (dtos.length == 0) entity = null;
//			else entity = TopicDto.convertList(Arrays.asList(dtos));
//		} catch (ResourceAccessException e) {
//			entity = null;
//		} catch (HttpClientErrorException e) {
//			entity = null;
//		}		
//		return Optional.ofNullable(entity);
//	}
}
