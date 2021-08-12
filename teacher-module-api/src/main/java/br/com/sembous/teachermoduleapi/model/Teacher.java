package br.com.sembous.teachermoduleapi.model;

public class Teacher {

	private Integer id;
	private PersonalInformations personalInformations;
//	private ClazzManager clazzManager;
	
	public Teacher(String firstName, String lastName, String email) {
		this.personalInformations = new PersonalInformations(firstName, lastName, email);
	}
	public Teacher(Integer id, String firstName, String lastName, String email) {
		this.id = id;
		this.personalInformations = new PersonalInformations(firstName, lastName, email);
	}
	
	public Integer getId() {
		return id;
	}
	public PersonalInformations getPersonalInformations() {
		return personalInformations;
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
		Teacher other = (Teacher) obj;
		
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		
		return true;
	}
}
