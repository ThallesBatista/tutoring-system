package br.com.sembous.emconsumerapi.gateway.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.web.client.RestTemplate;

import br.com.sembous.emconsumerapi.dto.ActivityDto;
import br.com.sembous.emconsumerapi.gateway.EntityGateway;
import br.com.sembous.emconsumerapi.gateway.UntilValue;
import br.com.sembous.emconsumerapi.model.Activity;

public class ActivityGateway implements EntityGateway<Activity> {

	private final String base = "http://localhost:8081/activity";
	private final GatewayUtil<Activity, ActivityDto> gatewayUtil;
	
	public ActivityGateway(RestTemplate restTemplate) {
		gatewayUtil = new GatewayUtil<>(Activity.class, ActivityDto.class, restTemplate);
	}

	@Override
	public Optional<Activity> getOne(Integer id) {
		return gatewayUtil.getOneAux(UrlUtil.getOneUrl(base, id));
	}

	@Override
	public Optional<Activity> getOne(Integer id, UntilValue until) {
		return gatewayUtil.getOneAux(UrlUtil.getOneUrl(base, id, until));
	}

	@Override
	public Optional<List<Activity>> getAll(Integer id) {
		return gatewayUtil.getAllAux(UrlUtil.getAllUrl(base));
	}

	@Override
	public Optional<List<Activity>> getAll(Integer id, UntilValue until) {
		return gatewayUtil.getAllAux(UrlUtil.getAllUrl(base, until));
	}

}
