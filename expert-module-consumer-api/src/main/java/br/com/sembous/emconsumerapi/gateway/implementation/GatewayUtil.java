package br.com.sembous.emconsumerapi.gateway.implementation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import br.com.sembous.emconsumerapi.dto.Dto;

final class GatewayUtil <T, U extends Dto<T>>{
	
	private RestTemplate restTemplate;
//	private Class<T> tClass;
	private Class<U> uClass;
	
	GatewayUtil(Class<T> t, Class<U> u, RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
//		tClass = t;
		uClass = u;
	}
	
	public Optional<T> getOneAux(String url) {
		T entity;		
		try {
			ResponseEntity<U> re = this.restTemplate.getForEntity(url, uClass);
			U dto = re.getBody();
			entity = dto.convert();
		} catch (ResourceAccessException e) {
			entity = null;
		} catch (HttpClientErrorException e) {
			entity = null;
		}		
		return Optional.ofNullable(entity);
	}
	
	
	public Optional<List<T>> getAllAux(String url) {
		List<T> entity;
		try {
			@SuppressWarnings("unchecked")
			Class<U[]> c = (Class<U[]>) Class.forName("[L" + uClass.getName() + ";");
			ResponseEntity<U[]> re = this.restTemplate.getForEntity(url, c);
			U[] dtos = re.getBody();
			if (dtos.length == 0) entity = null;
			else entity = Dto.convertList(Arrays.asList(dtos));
		} catch (ResourceAccessException e) {
			entity = null;
		} catch (HttpClientErrorException e) {
			entity = null;
		} catch (ClassNotFoundException e) {
			entity = null;
			e.printStackTrace();
		} catch (ClassCastException e) {
			entity = null;
			e.printStackTrace();
		}
		return Optional.ofNullable(entity);
	}
}
