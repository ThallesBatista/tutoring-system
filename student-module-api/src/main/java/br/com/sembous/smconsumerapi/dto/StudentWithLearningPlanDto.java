package br.com.sembous.smconsumerapi.dto;

import java.time.Instant;

import br.com.sembous.smconsumerapi.model.Student;

public class StudentWithLearningPlanDto {

	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private LearningPlanDto learningPlan; //é opcional, tenho que checar se vem
	private Instant learningPlanUpdatedAt;
	
	public Student convert() {
		if (learningPlan==null) return new Student(id, firstName, lastName, email);
		return new Student(id, firstName, lastName, email, learningPlanUpdatedAt, learningPlan.convert());
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setLearningPlan(LearningPlanDto learningPlan) {
		this.learningPlan = learningPlan;
	}
	public void setLearningPlanUpdatedAt(Instant learningPlanUpdatedAt) {
		this.learningPlanUpdatedAt = learningPlanUpdatedAt;
	}
}
