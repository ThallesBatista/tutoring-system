package br.com.sembous.teachermodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sembous.teachermodule.model.ClazzLearningPlan;

public interface ClazzLearningPlanRepository extends JpaRepository<ClazzLearningPlan, Integer> {

}
