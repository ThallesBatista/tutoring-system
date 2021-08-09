package br.com.sembous.studentmodule.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public final class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Embedded
	private PersonalInformations personalInformations = new PersonalInformations();
	@Embedded
	private Preferences preferences = new Preferences();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.PERSIST)
	private List<Interaction> interactions = new ArrayList<>();
	@Embedded
	private KnowledgeDoneManager knowledgeDoneManager;
	@Embedded
	private LearningPlanManager learningPlanManager;
	
	
	public Student() {}
	public Student(String firstName, String lastName, String email) {
		this.getPersonalInformations().setFirstName(firstName);
		this.getPersonalInformations().setLastName(lastName);
		this.getPersonalInformations().setEmail(email);
	}
	
	
	
	public LearningPlan addLearningPlan(LearningPlanPiece piece) {
		this.getKnowledgeManager().updateNewLearningPlan(piece);
		LearningPlan learningPlan = new LearningPlan(piece);
		this.getLearningPlanManager().addLearningPlan(learningPlan, this);
		return learningPlan;
	}
	public void updateLearningPlan(Integer learningPlanId, LearningPlanPiece piece) {
		Optional<LearningPlan> optional = this.getLearningPlanManager().getLearningPlan(learningPlanId);
		if (optional.isEmpty()) return;
		this.getKnowledgeManager().updateNewLearningPlan(piece);
		Set<LearningPlanPiece> piecesToRemove = optional.get().updateLearningPlan(piece);
		this.getKnowledgeManager().remove(piecesToRemove);
	}
	public void updateLearningPlanActivity(Integer learningPlanId, LearningPlanPiece piece) {
		Optional<LearningPlan> optional = this.getLearningPlanManager().getLearningPlan(learningPlanId);
		if (optional.isEmpty()) return;
		Set<LearningPlanPiece> updatedPieces = optional.get().updateLearningPlanActivity(piece);
		this.getKnowledgeManager().update(updatedPieces, this);
	}
	
	public void addInteraction(Interaction interaction) {
		this.interactions.add(interaction);
		interaction.setStudent(this);
	}
	
	
	
	public Integer getId() {
		return id;
	}	
	public PersonalInformations getPersonalInformations() {
		return personalInformations;
	}	
	public Preferences getPreferences() {
		return preferences;
	}
	public KnowledgeDoneManager getKnowledgeManager() {
		return this.knowledgeDoneManager;
	}
	public LearningPlanManager getLearningPlanManager() {
		return learningPlanManager;
	}
	public List<Interaction> getInteractions() {
		return Collections.unmodifiableList(this.interactions);
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
		Student other = (Student) obj;
		
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		
		return true;
	}
}
