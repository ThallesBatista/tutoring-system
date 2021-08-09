package br.com.sembous.studentmodule.repository.specifications;

import java.time.Instant;
import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.data.jpa.domain.Specification;

import br.com.sembous.studentmodule.model.Interaction;
import br.com.sembous.studentmodule.model.InteractionType;
import br.com.sembous.studentmodule.model.Student;

public class InteractionSpecification {

	public static Specification<Interaction> student(Student student) {
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("student"), student);
	}

	public static Specification<Interaction> createdAtGreaterThan(Instant instant) {
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("createdAt"), instant);
	}

	public static Specification<Interaction> typeEquals(InteractionType type) {
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("type"), type);
	}

	public static Specification<Interaction> typeIn(Collection<InteractionType> types) {
		return (root, criteriaQuery, criteriaBuilder) -> {
			In<InteractionType> inClause = criteriaBuilder.in(root.get("type"));
			types.stream().forEach(inClause::value);
			return inClause;
		};
	}
}
