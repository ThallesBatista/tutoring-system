package br.com.sembous.teachermodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sembous.teachermodule.model.Clazz;

public interface ClazzRepository extends JpaRepository<Clazz, Integer> {

}
