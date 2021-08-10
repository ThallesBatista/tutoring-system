package br.com.sembous.tutoringmodule.config.security;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Enumerated(EnumType.STRING)
	private RoleValue name;
	
	@Override
	public String getAuthority() {
		return this.name.toString();
	}

	public Integer getId() {
		return id;
	}
	
	public RoleValue getRole() {
		return this.name;
	}
//	public String getName() {
//		return name;
//	}	
}
