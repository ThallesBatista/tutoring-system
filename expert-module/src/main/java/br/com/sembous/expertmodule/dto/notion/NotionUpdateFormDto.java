package br.com.sembous.expertmodule.dto.notion;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.sembous.expertmodule.model.Notion;

public class NotionUpdateFormDto {
	
	@NotNull @NotEmpty @NotBlank @Length(max = 20)
	private String name;
	@NotNull
	private Integer pedagogicalObjectiveId;
	private Integer order;
	
	public void update(Notion notion) {
		if (!this.name.equals(notion.getName())) notion.setName(this.name);
	}
	

	public void setName(String name) {
		this.name = name;
	}	
	public void setPedagogicalObjectiveId(Integer pedagogicalObjectiveId) {
		this.pedagogicalObjectiveId = pedagogicalObjectiveId;
	}
	public Integer getPedagogicalObjectiveId() {
		return pedagogicalObjectiveId;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public Integer getOrder() {
		return this.order;
	}
}
