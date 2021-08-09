package br.com.sembous.expertmodule.dto.notion;

import java.util.List;
import java.util.stream.Collectors;

import br.com.sembous.expertmodule.model.Notion;

public class NotionDto {
	
	private Integer id;
	private String name;

	public NotionDto(Notion notion) {
		this.id = notion.getId();
		this.name = notion.getName();
	}
	

	public static List<NotionDto> convert(List<Notion> notions) {
		return notions.stream().map(NotionDto::new).collect(Collectors.toList());
	}
	

	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}
