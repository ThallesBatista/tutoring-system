package br.com.sembous.expertmodule.dto.activity;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.sembous.expertmodule.dto.ValueOfEnum;
import br.com.sembous.expertmodule.dto.content.ContentFormDto;
import br.com.sembous.expertmodule.model.Activity;
import br.com.sembous.expertmodule.model.ActivitySubtype;
import br.com.sembous.expertmodule.model.ActivityType;

public class ActivityUpdateFormDto {
	
	@NotNull @NotEmpty @NotBlank @Length(max = 20)
	private String name;
	@NotNull @ValueOfEnum(enumClass = ActivityType.class)
	private String type;
	@ValueOfEnum(enumClass = ActivitySubtype.class)
	private String subtype;
	@NotNull
	private Integer conceptId;
	private @Valid ContentFormDto content;
	private Integer order;
	
	public void update(Activity activity) {
		if (!this.name.equals(activity.getName())) activity.setName(this.name);
		if (!this.type.equals(activity.getType().toString())) activity.setType(ActivityType.valueOf(this.type));
		if (this.subtype!=null && !activity.getSubtype().toString().equals(this.subtype)) activity.setSubtype(ActivitySubtype.valueOf(this.subtype));
		if (this.content!=null) activity.updateContent(this.content.convert());
	}

	
	public Integer getConceptId() {
		return conceptId;
	}
	public Integer getOrder() {
		return order;
	}	
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public ContentFormDto getContent() {
		return content;
	}
	public String getSubtype() {
		return subtype;
	}


	public void setName(String name) {
		this.name = name;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setConceptId(Integer conceptId) {
		this.conceptId = conceptId;
	}
	public void setContent(ContentFormDto content) {
		this.content = content;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
}
