package se.sundsvall.educationfinder.api.model.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static se.sundsvall.educationfinder.api.model.enums.CourseFilter.CATEGORY;
import static se.sundsvall.educationfinder.api.model.enums.CourseFilter.CREDITS;
import static se.sundsvall.educationfinder.api.model.enums.CourseFilter.LEVEL;
import static se.sundsvall.educationfinder.api.model.enums.CourseFilter.PROVIDER;
import static se.sundsvall.educationfinder.api.model.enums.CourseFilter.SCOPE;
import static se.sundsvall.educationfinder.api.model.enums.CourseFilter.STUDY_LOCATION;
import static se.sundsvall.educationfinder.api.model.enums.CourseFilter.SUBCATEGORY;

import org.junit.jupiter.api.Test;

class CourseFilterTest {

	@Test
	void enums() {
		assertThat(CourseFilter.values()).containsExactly(CREDITS, CATEGORY, SUBCATEGORY, PROVIDER, LEVEL, SCOPE, STUDY_LOCATION);
	}

	@Test
	void enumValues() {
		assertThat(CREDITS).hasToString("credits");
		assertThat(PROVIDER).hasToString("provider");
		assertThat(LEVEL).hasToString("level");
		assertThat(SCOPE).hasToString("scope");
		assertThat(CATEGORY).hasToString("category");
		assertThat(SUBCATEGORY).hasToString("subcategory");
		assertThat(STUDY_LOCATION).hasToString("studyLocation");
	}
}
