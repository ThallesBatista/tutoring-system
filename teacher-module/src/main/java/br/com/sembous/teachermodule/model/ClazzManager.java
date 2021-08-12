package br.com.sembous.teachermodule.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Embeddable
public class ClazzManager {

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<Clazz> classes = new HashSet<>();
	
	public Set<Clazz> getClasses() {
		return Collections.unmodifiableSet(classes);
	}
}
