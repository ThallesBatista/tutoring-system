package br.com.sembous.expertmodule.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.sembous.expertmodule.model.Notion;

@Repository
public interface NotionRepository extends JpaRepository<Notion, Integer> {
	
	@Query(value = "SELECT n FROM Notion n LEFT JOIN FETCH n.pedagogicalObjective WHERE n.id = :id")
	Optional<Notion> findByIdWithPedagogicalObjective(Integer id);
	
	@Query(value = "SELECT n FROM Notion n LEFT JOIN FETCH n.concepts LEFT JOIN FETCH n.pedagogicalObjective WHERE n.id = :id")
	Optional<Notion> findByIdDetailed(@Param("id") Integer id);
	
	@Query(value = "SELECT n FROM Notion n LEFT JOIN FETCH n.concepts WHERE n.id = :id")
	Optional<Notion> findByIdWithConcepts(Integer id);
	
//	@Query(value = "SELECT n FROM Notion n "
//			+ "LEFT JOIN FETCH n.concepts "
//			+ "LEFT JOIN FETCH n.concepts.activities "
//			+ "LEFT JOIN FETCH n.concepts.notion "
//			+ "LEFT JOIN FETCH n.concepts.children "
//			+ "LEFT JOIN FETCH n.concepts.parent "
//			+ "LEFT JOIN FETCH n.concepts.dependencies "
//			+ "LEFT JOIN FETCH n.concepts.activities "
//			+ "LEFT JOIN FETCH n.concepts.activities.content "
//			+ "WHERE n.id = :id")
//	Optional<Notion> findByIdWithAllTree(@Param("id")Integer pedagogicalObjectiveId);
}
