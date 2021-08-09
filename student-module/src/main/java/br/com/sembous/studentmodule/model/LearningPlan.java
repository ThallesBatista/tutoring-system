package br.com.sembous.studentmodule.model;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "learning_plans")
public class LearningPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Student student;
	
	@OneToOne(orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
	private LearningPlanPiece learningPlanGraph;
	
	@Column(name = "expert_module_id")
	private Integer expertModuleId;
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "learningPlan", orphanRemoval = true, cascade = CascadeType.PERSIST)
//	private Set<LearningPlanPiece> learningPlanPieces = new HashSet<>();
	
	private String name;
	
	private Instant updatedAt;
	
	
	
	public LearningPlan() { }
	public LearningPlan(LearningPlanPiece pedagogicalObjectivePiece) {
		if (!pedagogicalObjectivePiece.getType().equals(KnowledgeType.PEDAGOGICAL_OBJECTIVE)) 
			throw new IllegalArgumentException("Learning Plan Piece must be of type PEDAGOGICAL_OBJECTIVE");
		this.name = pedagogicalObjectivePiece.getName();
		this.updatedAt = Instant.now();
		this.expertModuleId = pedagogicalObjectivePiece.getExpertModuleId();
		this.learningPlanGraph = pedagogicalObjectivePiece;
		
//		List<LearningPlanPiece> flatnedList = ModelUtil.learningPlanOrderListFlatter(pedagogicalObjectivePiece);
//		flatnedList.stream().forEach(this::addLearningPlanPiece);
	}
	
	
	Set<LearningPlanPiece> updateLearningPlan(LearningPlanPiece pedagogicalObjectivePiece) {
		if (!pedagogicalObjectivePiece.getType().equals(KnowledgeType.PEDAGOGICAL_OBJECTIVE)) 
			throw new IllegalArgumentException("Learning Plan Piece must be of type PEDAGOGICAL_OBJECTIVE");
		
		if (!pedagogicalObjectivePiece.getExpertModuleId().equals(this.expertModuleId))
			throw new IllegalArgumentException("The PedagogicalObjective of the Learning Plan cannot be changed, try to create another Learning Plan");
		
		List<LearningPlanPiece> lpps = ModelUtil.learningPlanOrderListFlatter(this.learningPlanGraph);
		List<LearningPlanPiece> newLpps = ModelUtil.learningPlanOrderListFlatter(pedagogicalObjectivePiece);
		Set<LearningPlanPiece> piecesToRemove = lpps.stream()
				.filter(p -> !newLpps.stream().anyMatch(n -> LearningPlanPiece.compareWithoutId(p, n)) && !p.getStatus().equals(KnowledgeStatus.DONE))
				.collect(Collectors.toUnmodifiableSet());
		this.learningPlanGraph = pedagogicalObjectivePiece;
		this.updatedAt = Instant.now();
		return piecesToRemove;
	}
	Set<LearningPlanPiece> updateLearningPlanActivity(LearningPlanPiece piece) {
		if (!KnowledgeType.getActivityTypes().contains(piece.getType())) {
//			new IllegalArgumentException("Piece must be an Activity").printStackTrace();
			return Set.of();
		}
		
		Optional<LearningPlanPiece> optional = this.findPiece(piece);
		if (optional.isEmpty()) {
//			new IllegalArgumentException("Piece doesn't exist in this Learning Plan").printStackTrace();
			return Set.of();
		}
		LearningPlanPiece lpp = optional.get();
		
		Boolean lppUpdated = lpp.updateStatus(piece.getStatus(), piece.getScore());
		if (!lppUpdated) return Set.of();
		Set<LearningPlanPiece> updatedPieces = new HashSet<>();
		updatedPieces.add(lpp);

		return this.refresh(updatedPieces);
	}
	
	
	
	void setStudent(Student student) {
		this.student = student;
	}
	
	
	
	public Integer getExpertModuleId() {
		return expertModuleId;
	}
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Instant getUpdatedAt() {
		return updatedAt;
	}
	public Student getStudent() {
		return student;
	}
	public LearningPlanPiece getLearningPlanGraph() {
		return learningPlanGraph;
	}
	public Double getAvgScore() {
		return this.learningPlanGraph.getScore();
	}
	
	
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LearningPlan other = (LearningPlan) obj;
		
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		
		return true;
	}
//	private void addLearningPlanPiece(LearningPlanPiece piece) {
//		this.learningPlanPieces.add(piece);
//		piece.setLearningPlan(this);
//	}
	private Optional<LearningPlanPiece> findPiece(LearningPlanPiece piece) {
		List<LearningPlanPiece> lpps = ModelUtil.learningPlanOrderListFlatter(this.learningPlanGraph);
		return lpps.stream().filter(p -> LearningPlanPiece.compareWithoutId(p, piece)).findAny();
	}
	private Set<LearningPlanPiece> refresh(Set<LearningPlanPiece> updatedPieces) {
		List<LearningPlanPiece> activities = ModelUtil.learningPlanActivitiesListOrdered(this.learningPlanGraph);
		this.updateLearningPlanRecursive(activities, updatedPieces);
		return updatedPieces;
	}
	private void updateLearningPlanRecursive(List<LearningPlanPiece> childList, Set<LearningPlanPiece> updatedPieces) {
		Set<LearningPlanPiece> fatherSet = childList.stream().filter(p -> {return p.getFatherLPP()!=null;})
				.map(p -> p.getFatherLPP().orElse(null)).collect(Collectors.toSet());
		if (fatherSet.isEmpty()) return;
		
		fatherSet.forEach(f -> {
			boolean allChildrenAreDone = true;
			boolean hasChildBlocked = false;
			for (LearningPlanPiece c : f.getChildLPP()) {
				if (!c.getStatus().equals(KnowledgeStatus.DONE)) allChildrenAreDone = false;
				if (c.getStatus().equals(KnowledgeStatus.BLOCKED)) hasChildBlocked = true;
			}
			
//			Set<KnowledgeType> evaluationActivityTypes = KnowledgeType.getEvaluationActivityTypes();
//			double sum = f.getChildLPP().stream()
//					.filter(c -> {return (evaluationActivityTypes.contains(c.getType())&&c.getStatus().equals(KnowledgeStatus.DONE));})
//					.mapToDouble(LearningPlanPiece::getScore).sum();
//			long count = f.getChildLPP().stream()
//					.filter(c -> {return (evaluationActivityTypes.contains(c.getType())&&c.getStatus().equals(KnowledgeStatus.DONE));})
//					.count();
			Set<KnowledgeType> nonEvaluationActivityTypes = KnowledgeType.getNonEvaluationActivityTypes();
			double sum = f.getChildLPP().stream()
					.filter(c -> {return (!nonEvaluationActivityTypes.contains(c.getType())&&c.getStatus().equals(KnowledgeStatus.DONE));})
					.mapToDouble(LearningPlanPiece::getScore).sum();
			long count = f.getChildLPP().stream()
					.filter(c -> {return (!nonEvaluationActivityTypes.contains(c.getType())&&c.getStatus().equals(KnowledgeStatus.DONE));})
					.count();
			Double fatherAvgScore;
			if (count>0) fatherAvgScore = Double.valueOf(sum/count);
			else fatherAvgScore = Double.valueOf(0);
			
			Boolean updated;
			if (hasChildBlocked) updated = f.updateStatus(KnowledgeStatus.BLOCKED, fatherAvgScore);
			else if (allChildrenAreDone) updated = f.updateStatus(KnowledgeStatus.DONE, fatherAvgScore);
			else updated = f.updateStatus(KnowledgeStatus.TO_DO, fatherAvgScore);
			
			if (updated) updatedPieces.add(f);
		});
		
		List<LearningPlanPiece> fatherList = fatherSet.stream().collect(Collectors.toList());
		this.updateLearningPlanRecursive(fatherList, updatedPieces);
	}
	
	
	
	static Boolean compareWithoutId(LearningPlan p1, LearningPlan p2) {
		return (p1.getExpertModuleId().equals(p2.getExpertModuleId()));
	}
}
