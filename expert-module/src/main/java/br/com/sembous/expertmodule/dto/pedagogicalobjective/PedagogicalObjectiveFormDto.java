package br.com.sembous.expertmodule.dto.pedagogicalobjective;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.sembous.expertmodule.model.PedagogicalObjective;

public class PedagogicalObjectiveFormDto {
	
	@NotNull @NotEmpty @NotBlank @Length(max = 20)
	private String name;
	@NotNull
	private Integer topicId;
	private Integer order;
	
	
	public PedagogicalObjective convert() {
		return new PedagogicalObjective(this.name);
	}
	

	public void setName(String name) {
		this.name = name;
	}
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
}
