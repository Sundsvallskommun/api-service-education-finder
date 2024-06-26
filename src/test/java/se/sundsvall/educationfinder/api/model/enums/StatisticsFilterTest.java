package se.sundsvall.educationfinder.api.model.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.CATEGORY;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.CATEGORY_ID;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.EDUCATION_FORM;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.END_DATE;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.START_DATE;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.STUDY_LOCATION;

import org.junit.jupiter.api.Test;

class StatisticsFilterTest {
	
	@Test
	void enums() {
		assertThat(StatisticsFilter.values()).containsExactly(EDUCATION_FORM, CATEGORY, CATEGORY_ID, STUDY_LOCATION, START_DATE, END_DATE);
	}

	@Test
	void enumValues() {
		assertThat(EDUCATION_FORM).hasToString("educationForm");
		assertThat(CATEGORY).hasToString("category");
		assertThat(CATEGORY_ID).hasToString("categoryId");
		assertThat(STUDY_LOCATION).hasToString("studyLocation");
		assertThat(START_DATE).hasToString("startDate");
		assertThat(END_DATE).hasToString("endDate");
	}
}
