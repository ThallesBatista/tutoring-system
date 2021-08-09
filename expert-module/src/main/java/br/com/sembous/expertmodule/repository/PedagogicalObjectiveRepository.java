package br.com.sembous.expertmodule.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.sembous.expertmodule.model.PedagogicalObjective;

@Repository
public interface PedagogicalObjectiveRepository extends JpaRepository<PedagogicalObjective, Integer> {

	@Query(value = "SELECT p FROM PedagogicalObjective p LEFT JOIN FETCH p.notions WHERE p.id = :id")
	Optional<PedagogicalObjective> findByIdDetailed(@Param("id") Integer id);

	@Query(value = "SELECT p FROM PedagogicalObjective p LEFT JOIN FETCH p.notions WHERE p.id = :id")
	Optional<PedagogicalObjective> findByIdWtihNotion(@Param("id")Integer pedagogicalObjectiveId);

	List<PedagogicalObjective> findByTopicId(Integer topicId);

//	@Query(value = "SELECT p FROM PedagogicalObjective p "
//			+ "LEFT JOIN FETCH p.notions "
//			+ "LEFT JOIN FETCH p.notions.concepts "
//			+ "LEFT JOIN FETCH p.notions.concepts.activities "
//			+ "LEFT JOIN FETCH p.notions.concepts.notion "
//			+ "LEFT JOIN FETCH p.notions.concepts.children "
//			+ "LEFT JOIN FETCH p.notions.concepts.parent "
//			+ "LEFT JOIN FETCH p.notions.concepts.dependencies "
//			+ "LEFT JOIN FETCH p.notions.concepts.activities "
//			+ "LEFT JOIN FETCH p.notions.concepts.activities.content "
//			+ "WHERE p.id = :id")
//	Optional<PedagogicalObjective> findByIdWithAllTree(@Param("id")Integer pedagogicalObjectiveId);
}
