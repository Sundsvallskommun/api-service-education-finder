package se.sundsvall.educationfinder.integration.db.specification;

import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.CATEGORY;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.END;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.LEVEL;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.SCOPE;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.START;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.STUDY_LOCATION;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import se.sundsvall.educationfinder.integration.db.model.CourseEntity;

public interface StatisticsSpecification {

	SpecificationBuilder<CourseEntity> BUILDER = new SpecificationBuilder<>();

	static Specification<CourseEntity> withLevels(final List<String> levels) {
		return BUILDER.buildInFilter(LEVEL, levels);
	}

	static Specification<CourseEntity> withScopes(final List<String> scopes) {
		return BUILDER.buildInFilter(SCOPE, scopes);
	}

	static Specification<CourseEntity> withStudyLocations(final List<String> studyLocations) {
		return BUILDER.buildInFilter(STUDY_LOCATION, studyLocations);
	}

	static Specification<CourseEntity> withCategories(final List<String> categories) {
		return BUILDER.buildStartingWithIgnoreCaseFilter(CATEGORY, categories);
	}

	static Specification<CourseEntity> withSubCategories(final List<String> subCategories) {
		return BUILDER.buildEndingWithIgnoreCaseFilter(CATEGORY, subCategories);
	}

	static Specification<CourseEntity> withinPeriod(final LocalDate startDate, final LocalDate endDate) {
		return BUILDER.buildDateRangeFilter(START, END, startDate, endDate);
	}
}
