package se.sundsvall.educationfinder.integration.db.specification;

import org.springframework.data.jpa.domain.Specification;
import se.sundsvall.educationfinder.integration.db.model.CourseEntity;

import java.time.LocalDate;
import java.util.List;

import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.CATEGORY;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.CODE;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.CREDITS;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.EARLIEST_APPLICATION;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.END;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.INFORMATION;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.LANGUAGE_OF_INSTRUCTION;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.LATEST_APPLICATION;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.LEVEL;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.NAME;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.PROVIDER;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.SCOPE;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.START;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.STUDY_LOCATION;

public interface CourseSpecification {

	SpecificationBuilder<CourseEntity> BUILDER = new SpecificationBuilder<>();

	// In filters
	static Specification<CourseEntity> withScopeIn(final List<String> scopes) {
		return BUILDER.buildInFilter(SCOPE, scopes);
	}

	static Specification<CourseEntity> withStudyLocationIn(final List<String> studyLocations) {
		return BUILDER.buildInFilter(STUDY_LOCATION, studyLocations);
	}

	static Specification<CourseEntity> withLevelIn(final List<String> levels) {
		return BUILDER.buildInFilter(LEVEL, levels);
	}

	// Equal ignore case filters
	static Specification<CourseEntity> withName(final String name) {
		return BUILDER.buildEqualsIgnoreCaseFilter(NAME, name);
	}

	static Specification<CourseEntity> withProvider(final String provider) {
		return BUILDER.buildEqualsIgnoreCaseFilter(PROVIDER, provider);
	}

	static Specification<CourseEntity> withCode(final String code) {
		return BUILDER.buildEqualsIgnoreCaseFilter(CODE, code);
	}

	static Specification<CourseEntity> withLanguageOfInstruction(final String languageOfInstruction) {
		return BUILDER.buildEqualsIgnoreCaseFilter(LANGUAGE_OF_INSTRUCTION, languageOfInstruction);
	}

	// Starting with ignore case filters
	static Specification<CourseEntity> withCategory(final String category) {
		return BUILDER.buildStartingWithIgnoreCaseFilter(CATEGORY, category);
	}

	// Ending with ignore case filters
	static Specification<CourseEntity> withSubCategory(final String category) {
		return BUILDER.buildEndingWithIgnoreCaseFilter(CATEGORY, category);
	}

	// Like ignore case filters
	static Specification<CourseEntity> withLikeInformation(final String information) {
		return BUILDER.buildLikeIgnoreCaseFilter(INFORMATION, information);
	}

	// Equal filters
	static Specification<CourseEntity> withCredits(final String credits) {
		return BUILDER.buildEqualFilter(CREDITS, credits);
	}

	static Specification<CourseEntity> withStart(final LocalDate start) {
		return BUILDER.buildEqualFilter(START, start);
	}

	static Specification<CourseEntity> withEnd(final LocalDate end) {
		return BUILDER.buildEqualFilter(END, end);
	}

	static Specification<CourseEntity> withEarliestApplication(final LocalDate earliestApplication) {
		return BUILDER.buildEqualFilter(EARLIEST_APPLICATION, earliestApplication);
	}

	static Specification<CourseEntity> withLatestApplication(final LocalDate latestApplication) {
		return BUILDER.buildEqualFilter(LATEST_APPLICATION, latestApplication);
	}

	// Date is equal or before filters
	static Specification<CourseEntity> withStartBefore(final LocalDate start) {
		return BUILDER.buildDateIsEqualOrBeforeFilter(START, start);
	}

	static Specification<CourseEntity> withEndBefore(final LocalDate end) {
		return BUILDER.buildDateIsEqualOrBeforeFilter(END, end);
	}

	static Specification<CourseEntity> withEarliestApplicationBefore(final LocalDate earliestApplication) {
		return BUILDER.buildDateIsEqualOrBeforeFilter(EARLIEST_APPLICATION, earliestApplication);
	}

	static Specification<CourseEntity> withLatestApplicationBefore(final LocalDate latestApplication) {
		return BUILDER.buildDateIsEqualOrBeforeFilter(LATEST_APPLICATION, latestApplication);
	}

	// Date is equal or after filters
	static Specification<CourseEntity> withStartAfter(final LocalDate start) {
		return BUILDER.buildDateIsEqualOrAfterFilter(START, start);
	}

	static Specification<CourseEntity> withEndAfter(final LocalDate end) {
		return BUILDER.buildDateIsEqualOrAfterFilter(END, end);
	}

	static Specification<CourseEntity> withEarliestApplicationAfter(final LocalDate earliestApplication) {
		return BUILDER.buildDateIsEqualOrAfterFilter(EARLIEST_APPLICATION, earliestApplication);
	}

	static Specification<CourseEntity> withLatestApplicationAfter(final LocalDate latestApplication) {
		return BUILDER.buildDateIsEqualOrAfterFilter(LATEST_APPLICATION, latestApplication);
	}

	static Specification<CourseEntity> withFreeTextSearch(String searchString) {
		return BUILDER.buildFreeTextSearch(List.of(CODE, NAME, INFORMATION, CATEGORY.split(" - ", 2)[0]), searchString);
	}

	static Specification<CourseEntity> distinct() {
		return BUILDER.distinct();
	}

}
