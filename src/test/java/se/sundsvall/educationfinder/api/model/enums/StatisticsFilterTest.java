package se.sundsvall.educationfinder.api.model.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.CATEGORY;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.CATEGORY_ID;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.END_DATE;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.LEVEL;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.SCOPE;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.START_DATE;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.STUDY_LOCATION;

import org.junit.jupiter.api.Test;

class StatisticsFilterTest {

	@Test
	void enums() {
		assertThat(StatisticsFilter.values()).containsExactly(LEVEL, SCOPE, CATEGORY, CATEGORY_ID, STUDY_LOCATION, START_DATE, END_DATE);
	}

	@Test
	void enumValues() {
		assertThat(LEVEL).hasToString("level");
		assertThat(SCOPE).hasToString("scope");
		assertThat(CATEGORY).hasToString("category");
		assertThat(CATEGORY_ID).hasToString("categoryId");
		assertThat(STUDY_LOCATION).hasToString("studyLocation");
		assertThat(START_DATE).hasToString("startDate");
		assertThat(END_DATE).hasToString("endDate");
	}
}
