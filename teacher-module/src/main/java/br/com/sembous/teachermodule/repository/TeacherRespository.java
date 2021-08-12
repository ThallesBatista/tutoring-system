package br.com.sembous.teachermodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sembous.teachermodule.model.Teacher;

public interface TeacherRespository extends JpaRepository<Teacher, Integer> {

}
