package br.com.sembous.studentmodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sembous.studentmodule.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

//	@Query(value = "SELECT s FROM Student s LEFT JOIN FETCH s.knowledge.knowledgeDone LEFT JOIN FETCH s.knowledge.learningPlan WHERE s.id = :id")
//	Optional<Student> findByIdDetailed(@Param("id") Integer id);
//	@Query(value = "SELECT s FROM Student s LEFT JOIN FETCH s.knowledge.learningPlan WHERE s.id = :id")
//	Optional<Student> findByIdWithLearningPlan(@Param("id") Integer id);
//	
//	@Query(value = "SELECT s FROM Student s LEFT JOIN FETCH s.knowledge.knowledgeDone WHERE s.id = :id")
//	Optional<Student> findByIdWithKnowledgeDone(@Param("id") Integer id);

//	@Query(value = "SELECT s FROM Student s LEFT JOIN FETCH s.interactions WHERE s.id = :id")
//	Optional<Student> findByIdWithInteractions(Integer id);

}
