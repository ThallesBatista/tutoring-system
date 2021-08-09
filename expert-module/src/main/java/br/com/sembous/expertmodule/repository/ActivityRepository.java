package br.com.sembous.expertmodule.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sembous.expertmodule.model.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {

	@Query(value="SELECT a FROM Activity a "
			+ "LEFT JOIN FETCH a.content "
			+ "WHERE a.id = :id")
	Optional<Activity> findByIdWithContents(@Param("id") Integer id);

	@Query(value="SELECT a FROM Activity a "
			+ "LEFT JOIN FETCH a.content "
			+ "LEFT JOIN FETCH a.concept "
			+ "WHERE a.id = :id")
	Optional<Activity> findByIdDetailed(@Param("id") Integer id);

}
