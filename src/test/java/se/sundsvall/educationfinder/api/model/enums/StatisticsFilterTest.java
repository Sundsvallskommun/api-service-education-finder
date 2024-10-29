package se.sundsvall.educationfinder.api.model.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.CATEGORY;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.END_DATE;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.LEVEL;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.SCOPE;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.START_DATE;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.STUDY_LOCATION;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.SUBCATEGORY;

import org.junit.jupiter.api.Test;

class StatisticsFilterTest {

	@Test
	void enums() {
		assertThat(StatisticsFilter.values()).containsExactly(LEVEL, SCOPE, CATEGORY, SUBCATEGORY, STUDY_LOCATION, START_DATE, END_DATE);
	}

	@Test
	void enumValues() {
		assertThat(LEVEL).hasToString("level");
		assertThat(SCOPE).hasToString("scope");
		assertThat(CATEGORY).hasToString("category");
		assertThat(SUBCATEGORY).hasToString("subcategory");
		assertThat(STUDY_LOCATION).hasToString("studyLocation");
		assertThat(START_DATE).hasToString("startDate");
		assertThat(END_DATE).hasToString("endDate");
	}
}
