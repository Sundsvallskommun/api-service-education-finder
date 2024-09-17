package se.sundsvall.educationfinder.api.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static com.google.code.beanmatchers.BeanMatchers.registerValueGenerator;
import static java.time.LocalDate.now;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class StatisticsParametersTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), LocalDate.class);
	}

	@Test
	void testBean() {
		assertThat(StatisticsParameters.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		var categories = List.of("category");
		var categoryIds = List.of("categoryId");
		var levels = List.of("level");
		var studyLocations = List.of("studyLocation");
		var scopes = List.of("scope");
		var startDate = LocalDate.now();
		var endDate = LocalDate.now().plusDays(1);

		var bean = StatisticsParameters.create()
			.withCategories(categories)
			.withCategoryIds(categoryIds)
			.withLevels(levels)
			.withStudyLocations(studyLocations)
			.withScopes(scopes)
			.withStartDate(startDate)
			.withEndDate(endDate);

		Assertions.assertThat(bean).isNotNull().hasNoNullFieldsOrProperties();
		Assertions.assertThat(bean.getCategories()).isEqualTo(categories);
		Assertions.assertThat(bean.getCategoryIds()).isEqualTo(categoryIds);
		Assertions.assertThat(bean.getLevels()).isEqualTo(levels);
		Assertions.assertThat(bean.getStudyLocations()).isEqualTo(studyLocations);
		Assertions.assertThat(bean.getStartDate()).isEqualTo(startDate);
		Assertions.assertThat(bean.getEndDate()).isEqualTo(endDate);
		Assertions.assertThat(bean.getScopes()).isEqualTo(scopes);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		Assertions.assertThat(StatisticsParameters.create()).hasAllNullFieldsOrProperties();
		Assertions.assertThat(new StatisticsParameters()).hasAllNullFieldsOrProperties();
	}
}
