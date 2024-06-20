package se.sundsvall.educationfinder.api.model.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static se.sundsvall.educationfinder.api.model.enums.SubjectFilter.CATEGORY;
import static se.sundsvall.educationfinder.api.model.enums.SubjectFilter.CATEGORY_ID;
import static se.sundsvall.educationfinder.api.model.enums.SubjectFilter.EDUCATION_FORM;

import org.junit.jupiter.api.Test;

class SubjectFilterTest {

	@Test
	void enums() {
		assertThat(SubjectFilter.values()).containsExactly(EDUCATION_FORM, CATEGORY, CATEGORY_ID);
	}

	@Test
	void enumValues() {
		assertThat(EDUCATION_FORM).hasToString("educationForm");
		assertThat(CATEGORY).hasToString("category");
		assertThat(CATEGORY_ID).hasToString("categoryId");
	}
}
