package se.sundsvall.educationfinder.integration.db.specification;

import static java.util.Objects.nonNull;
import static se.sundsvall.educationfinder.integration.db.model.SubjectEntity_.CATEGORY;
import static se.sundsvall.educationfinder.integration.db.model.SubjectEntity_.CATEGORY_ID;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import se.sundsvall.educationfinder.integration.db.model.SubjectEntity;

public interface SubjectSpecification {

	static Specification<SubjectEntity> withCategory(final List<String> categories) {
		return (entity, cq, cb) -> nonNull(categories) ? entity.get(CATEGORY).in(categories) : cb.and();
	}

	static Specification<SubjectEntity> withCategoryId(final List<String> categoryIds) {
		return (entity, cq, cb) -> nonNull(categoryIds) ? entity.get(CATEGORY_ID).in(categoryIds) : cb.and();
	}
}
