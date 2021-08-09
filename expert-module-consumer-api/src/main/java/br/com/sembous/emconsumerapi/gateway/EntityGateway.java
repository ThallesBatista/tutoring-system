package br.com.sembous.emconsumerapi.gateway;

import java.util.List;
import java.util.Optional;

public interface EntityGateway <T> {

	Optional<T> getOne(Integer id);
	
	Optional<T> getOne(Integer id, UntilValue until);
	
	Optional<List<T>> getAll(Integer id);
	
	Optional<List<T>> getAll(Integer id, UntilValue until);
}
