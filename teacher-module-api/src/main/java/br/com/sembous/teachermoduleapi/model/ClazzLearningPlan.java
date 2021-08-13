package br.com.sembous.teachermoduleapi.model;

import java.util.Set;

public class ClazzLearningPlan {

	private Integer id;
	private Clazz clazz;
//	private ClazzLearningPlanPiece learningPlanGraph;
	private Set<Integer> studentsIds;
	private Double generalScore = Double.valueOf(0);
	private Integer numberOfStudentsDoing = Integer.valueOf(0);
	private Integer numberOfStudentsDone = Integer.valueOf(0);
	private Integer numberOfStudentsBlocked = Integer.valueOf(0);
	private Integer expertModuleId;
	private String name;
	
	public ClazzLearningPlan(Integer id, Double generalScore, Integer numberOfStudentsDoing, Integer numberOfStudentsDone, 
			Integer numberOfStudentsBlocked, Integer expertModuleId, String name) {
		this.id = id;
		this.generalScore = generalScore;
		this.numberOfStudentsDoing = numberOfStudentsDoing;
		this.numberOfStudentsDone = numberOfStudentsDone;
		this.numberOfStudentsBlocked = numberOfStudentsBlocked;
		this.expertModuleId = expertModuleId;
		this.name = name;
	}
	
	void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}
	
	public Integer getId() {
		return id;
	}
	public Clazz getClazz() {
		return clazz;
	}
	public Set<Integer> getStudentsIds() {
		return studentsIds;
	}
	public Double getGeneralScore() {
		return generalScore;
	}
	public Integer getNumberOfStudentsDoing() {
		return numberOfStudentsDoing;
	}
	public Integer getNumberOfStudentsDone() {
		return numberOfStudentsDone;
	}
	public Integer getNumberOfStudentsBlocked() {
		return numberOfStudentsBlocked;
	}
	public Integer getExpertModuleId() {
		return expertModuleId;
	}
	public String getName() {
		return name;
	}
}
