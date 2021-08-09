package br.com.sembous.studentmodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sembous.studentmodule.model.LearningPlan;

public interface LearningPlanRepository extends JpaRepository<LearningPlan, Integer> {

}
