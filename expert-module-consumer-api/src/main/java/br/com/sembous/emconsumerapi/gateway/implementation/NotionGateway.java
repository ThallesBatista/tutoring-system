package br.com.sembous.emconsumerapi.gateway.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.web.client.RestTemplate;

import br.com.sembous.emconsumerapi.dto.NotionDto;
import br.com.sembous.emconsumerapi.gateway.EntityGateway;
import br.com.sembous.emconsumerapi.gateway.UntilValue;
import br.com.sembous.emconsumerapi.model.Notion;

public class NotionGateway implements EntityGateway<Notion> {

	private final String base = "http://localhost:8081/notion";
	private final GatewayUtil<Notion, NotionDto> gatewayUtil;
	
	public NotionGateway(RestTemplate restTemplate) {
		gatewayUtil = new GatewayUtil<>(Notion.class, NotionDto.class, restTemplate);
	}

	@Override
	public Optional<Notion> getOne(Integer id) {
		return gatewayUtil.getOneAux(UrlUtil.getOneUrl(base, id));
	}

	@Override
	public Optional<Notion> getOne(Integer id, UntilValue until) {
		return gatewayUtil.getOneAux(UrlUtil.getOneUrl(base, id, until));
	}

	@Override
	public Optional<List<Notion>> getAll(Integer id) {
		return gatewayUtil.getAllAux(UrlUtil.getAllUrl(base));
	}

	@Override
	public Optional<List<Notion>> getAll(Integer id, UntilValue until) {
		return gatewayUtil.getAllAux(UrlUtil.getAllUrl(base, until));
	}

}
