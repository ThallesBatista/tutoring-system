package br.com.sembous.emconsumerapi.dto;

import java.util.List;
import java.util.stream.Collectors;

public interface Dto<T> {

	T convert();
	
	static <T> List<T> convertList(List<Dto<T>> dtos) {
		return dtos.stream().map(dto -> dto.convert()).collect(Collectors.toList());
	}
}
