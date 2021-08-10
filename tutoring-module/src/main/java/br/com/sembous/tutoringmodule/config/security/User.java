package br.com.sembous.tutoringmodule.config.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public final class User implements UserDetails{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_roles",
	    joinColumns=@JoinColumn(name="user_id", referencedColumnName="id"),
	    inverseJoinColumns=@JoinColumn(name="role_id", referencedColumnName="id")
	    )
    private Set<Role> roles;
    
    private Integer foreignId;
    

    public User() {	}
    public User(String username, String password, Set<Role> roles, Integer foreignId) {
    	this.username = username;
    	this.password = password;
    	this.roles = roles;
    	this.foreignId = foreignId;
    }
    
    
	public Integer getId() {
		return id;
	}
	public Integer getForeignId() {
		return foreignId;
	}	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.unmodifiableSet(this.roles);
	}
	public Set<Role> getRoles(){
		return Collections.unmodifiableSet(this.roles);
	}
	@Override
	public String getPassword() {
		return this.password;
	}
	@Override
	public String getUsername() {
		return this.username;
	}
	
	

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}

}
