package br.com.sembous.expertmodule.dto.activity;

import java.util.List;
import java.util.stream.Collectors;

import br.com.sembous.expertmodule.model.Activity;

public class ActivityDto {
	
	private Integer id;
	private String name;
	
	public ActivityDto(Activity activity) {
		this.id = activity.getId();
		this.name = activity.getName();
	}
	

	public static List<ActivityDto> convert(List<Activity> activities) {
		return activities.stream().map(ActivityDto::new).collect(Collectors.toList());
	}
	

	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}
