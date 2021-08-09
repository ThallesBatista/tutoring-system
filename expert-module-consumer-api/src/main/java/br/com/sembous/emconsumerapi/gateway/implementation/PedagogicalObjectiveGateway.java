package br.com.sembous.emconsumerapi.gateway.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.web.client.RestTemplate;

import br.com.sembous.emconsumerapi.dto.PedagogicalObjectiveDto;
import br.com.sembous.emconsumerapi.gateway.EntityGateway;
import br.com.sembous.emconsumerapi.gateway.UntilValue;
import br.com.sembous.emconsumerapi.model.PedagogicalObjective;

public final class PedagogicalObjectiveGateway implements EntityGateway<PedagogicalObjective> {

	private final String base = "http://localhost:8081/pedagogical-objective";
	private final GatewayUtil<PedagogicalObjective, PedagogicalObjectiveDto> gatewayUtil;
	
	public PedagogicalObjectiveGateway(RestTemplate restTemplate) {
		gatewayUtil = new GatewayUtil<>(PedagogicalObjective.class, PedagogicalObjectiveDto.class, restTemplate);
	}

	@Override
	public Optional<PedagogicalObjective> getOne(Integer id) {
		return gatewayUtil.getOneAux(UrlUtil.getOneUrl(base, id));
	}

	@Override
	public Optional<PedagogicalObjective> getOne(Integer id, UntilValue until) {
		return gatewayUtil.getOneAux(UrlUtil.getOneUrl(base, id, until));
	}

	@Override
	public Optional<List<PedagogicalObjective>> getAll(Integer id) {
		return gatewayUtil.getAllAux(UrlUtil.getAllUrl(base));
	}

	@Override
	public Optional<List<PedagogicalObjective>> getAll(Integer id, UntilValue until) {
		return gatewayUtil.getAllAux(UrlUtil.getAllUrl(base, until));
	}

}
