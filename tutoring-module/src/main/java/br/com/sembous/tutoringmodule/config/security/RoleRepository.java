package br.com.sembous.tutoringmodule.config.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByName(RoleValue name);
}
