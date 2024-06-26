package se.sundsvall.educationfinder.integration.db.specification;

import static java.util.Objects.nonNull;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.CODE;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.END;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.START;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.STUDY_LOCATION;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import se.sundsvall.educationfinder.integration.db.model.CourseEntity;

public interface StatisticsSpecification {

	static Specification<CourseEntity> withCodes(final List<String> codes) {
		if (nonNull(codes)) {
			return (entity, query, criteriaBuilder) -> entity.get(CODE).in(codes);
		}
		return (entity, query, criteriaBuilder) -> criteriaBuilder.and();
	}

	static Specification<CourseEntity> withStudyLocation(final List<String> studyLocations) {
		return (entity, cq, cb) -> nonNull(studyLocations) ? entity.get(STUDY_LOCATION).in(studyLocations) : cb.and();
	}

	static Specification<CourseEntity> withinPeriod(final LocalDate startDate, final LocalDate endDate) {
		if (nonNull(startDate) && nonNull(endDate)) {
			return (entity, query, criteriaBuilder) -> {
				Predicate startPredicate = criteriaBuilder.lessThanOrEqualTo(entity.get(START), endDate);
				Predicate endPredicate = criteriaBuilder.greaterThanOrEqualTo(entity.get(END), startDate);
				return criteriaBuilder.and(startPredicate, endPredicate);
			};
		} else if (nonNull(startDate)) {
			return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(START), endDate);
		} else if (nonNull(endDate)) {
			return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(END), startDate);
		}
		return (root, query, criteriaBuilder) -> criteriaBuilder.and();
	}
}
