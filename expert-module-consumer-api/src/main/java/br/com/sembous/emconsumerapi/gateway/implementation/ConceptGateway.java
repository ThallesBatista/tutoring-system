package br.com.sembous.emconsumerapi.gateway.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.web.client.RestTemplate;

import br.com.sembous.emconsumerapi.dto.ConceptDto;
import br.com.sembous.emconsumerapi.gateway.EntityGateway;
import br.com.sembous.emconsumerapi.gateway.UntilValue;
import br.com.sembous.emconsumerapi.model.Concept;

public class ConceptGateway implements EntityGateway<Concept> {

	private final String base = "http://localhost:8081/concept";
	private final GatewayUtil<Concept, ConceptDto> gatewayUtil;
	
	public ConceptGateway(RestTemplate restTemplate) {
		gatewayUtil = new GatewayUtil<>(Concept.class, ConceptDto.class, restTemplate);
	}

	@Override
	public Optional<Concept> getOne(Integer id) {
		return gatewayUtil.getOneAux(UrlUtil.getOneUrl(base, id));
	}

	@Override
	public Optional<Concept> getOne(Integer id, UntilValue until) {
		return gatewayUtil.getOneAux(UrlUtil.getOneUrl(base, id, until));
	}

	@Override
	public Optional<List<Concept>> getAll(Integer id) {
		return gatewayUtil.getAllAux(UrlUtil.getAllUrl(base));
	}

	@Override
	public Optional<List<Concept>> getAll(Integer id, UntilValue until) {
		return gatewayUtil.getAllAux(UrlUtil.getAllUrl(base, until));
	}

}
