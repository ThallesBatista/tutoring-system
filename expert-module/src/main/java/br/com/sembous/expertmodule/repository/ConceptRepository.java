package br.com.sembous.expertmodule.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sembous.expertmodule.model.Concept;

public interface ConceptRepository extends JpaRepository<Concept, Integer> {

	@Query(value="SELECT c FROM Concept c "
			+ "LEFT JOIN FETCH c.notion "
			+ "LEFT JOIN FETCH c.children "
			+ "LEFT JOIN FETCH c.parent "
			+ "LEFT JOIN FETCH c.dependencies "
			//+ "LEFT JOIN FETCH c.dependents "
			+ "LEFT JOIN FETCH c.activities "
			+ "WHERE c.id = :id")
	Optional<Concept> findByIdComplete(@Param("id") Integer id);
	
//	@Query(value="SELECT c FROM Concept c "
//			+ "LEFT JOIN FETCH c.notion "
//			+ "LEFT JOIN FETCH c.children "
////			+ "LEFT JOIN FETCH c.parents "
//			+ "LEFT JOIN FETCH c.dependencies "
////			+ "LEFT JOIN FETCH c.dependents "
////			+ "LEFT JOIN FETCH c.activities "
//			+ "WHERE c.id = :id")
//	Optional<Concept> findByIdWithRelations(@Param("id") Integer id);
	
	
	@Query(value="SELECT c FROM Concept c "
			+ "LEFT JOIN FETCH c.notion "
			//+ "LEFT JOIN FETCH c.children "
			+ "LEFT JOIN FETCH c.parent "
			+ "LEFT JOIN FETCH c.dependencies "
			//+ "LEFT JOIN FETCH c.dependents "
			//+ "LEFT JOIN FETCH c.activities "
			+ "WHERE c.id = :id")
	Optional<Concept> findByIdWithNotionAndParentAndDependencies(@Param("id") Integer id);
	
	@Query(value = "SELECT c FROM Concept c LEFT JOIN FETCH c.children WHERE c.id = :id")
	Optional<Concept> findByIdWithChildren(@Param("id") Integer id);

	@Query(value = "SELECT c FROM Concept c LEFT JOIN FETCH c.activities WHERE c.id = :id")
	Optional<Concept> findByIdWithActivities(@Param("id")Integer conceptId);
	
//	@Query(value = "SELECT c FROM Concept c "
//			+ "LEFT JOIN FETCH c.activities "
//			+ "LEFT JOIN FETCH c.notion "
//			+ "LEFT JOIN FETCH c.children "
//			+ "LEFT JOIN FETCH c.parent "
//			+ "LEFT JOIN FETCH c.dependencies "
//			+ "LEFT JOIN FETCH c.activities "
//			+ "LEFT JOIN FETCH c.activities.concept "
//			+ "LEFT JOIN FETCH c.activities.content "
//			+ "WHERE c.id = :id")
//	Optional<Concept> findByIdWithAllTree(@Param("id")Integer pedagogicalObjectiveId);
	
	
}
