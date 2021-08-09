package br.com.sembous.expertmodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sembous.expertmodule.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Integer>{

}
