package br.com.sembous.expertmodule.dto.concept;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.sembous.expertmodule.model.Concept;

public class ConceptFormDto {

	@NotNull @NotEmpty @NotBlank @Length(max = 20)
	private String name;
	private Integer notionId;
	private Integer childOf;
	private Integer order;
	private Set<Integer> dependentOf = new HashSet<>();

	
	public Concept convert() {
		return new Concept(this.name);
	}
	

	public void setName(String name) {
		this.name = name;
	}
	public Optional<Integer> getNotionId() {
		return Optional.ofNullable(notionId);
	}
	public void setNotionId(Integer notionId) {
		this.notionId = notionId;
	}
	public Optional<Integer> getChildOf() {
		return Optional.ofNullable(childOf);
	}
	public void setChildOf(Integer childOf) {
		this.childOf = childOf;
	}
	public Set<Integer> getDependentOf() {
		return dependentOf;
	}
	public void setDependentOf(Set<Integer> dependentOf) {
		this.dependentOf = dependentOf;
	}
	public Optional<Integer> getOrder() {
		return Optional.ofNullable(order);
	}
	public void setOrder(Integer order) {
		this.order = order;
	}	
}
