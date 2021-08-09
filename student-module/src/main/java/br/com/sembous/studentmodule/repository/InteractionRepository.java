package br.com.sembous.studentmodule.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.sembous.studentmodule.model.Interaction;
import br.com.sembous.studentmodule.model.KnowledgeType;

public interface InteractionRepository extends JpaRepository<Interaction, Long>, JpaSpecificationExecutor<Interaction> {

//	List<Interaction> findByStudentId(Integer id, Pageable page);
	List<Interaction> findByStudentIdAndKnowledgePieceIdTypeAndKnowledgePieceIdExpertModuleId(Integer studentId, KnowledgeType type, Integer expertModuleId);
	
	
}
