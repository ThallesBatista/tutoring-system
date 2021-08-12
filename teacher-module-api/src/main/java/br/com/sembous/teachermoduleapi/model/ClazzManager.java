package br.com.sembous.teachermoduleapi.model;

import java.util.Collections;
import java.util.Set;

public class ClazzManager {

	private Set<Clazz> classes;
	
	public ClazzManager() {	}
	public ClazzManager(Set<Clazz> classes) {	
		this.classes = classes;
	}
	
	public Set<Clazz> getClasses() {
		return Collections.unmodifiableSet(classes);
	}	
}
