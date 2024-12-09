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

class StatisticsTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), LocalDate.class);
	}

	@Test
	void testBean() {
		assertThat(Statistics.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		var onGoingCourses = 2;
		var plannedCourses = 3;
		var finishedCourses = 4;
		var availableSeats = 55;
		var totalCapacity = 100;
		var studyLocations = List.of("studyLocations");
		var levels = List.of("educationForms");
		var scopes = List.of(25, 5, 100);
		var categories = List.of("categories");
		var subCategories = List.of("subCategories");
		var startDate = LocalDate.now();
		var endDate = LocalDate.now().plusDays(1);

		var bean = Statistics.create()
			.withOnGoingCourses(onGoingCourses)
			.withPlannedCourses(plannedCourses)
			.withFinishedCourses(finishedCourses)
			.withAvailableSeats(availableSeats)
			.withTotalCapacity(totalCapacity)
			.withStudyLocations(studyLocations)
			.withCategories(categories)
			.withScopes(scopes)
			.withSubCategories(subCategories)
			.withLevels(levels)
			.withStartDate(startDate)
			.withEndDate(endDate);

		Assertions.assertThat(bean).isNotNull().hasNoNullFieldsOrProperties();
		Assertions.assertThat(bean.getOnGoingCourses()).isEqualTo(onGoingCourses);
		Assertions.assertThat(bean.getPlannedCourses()).isEqualTo(plannedCourses);
		Assertions.assertThat(bean.getFinishedCourses()).isEqualTo(finishedCourses);
		Assertions.assertThat(bean.getAvailableSeats()).isEqualTo(availableSeats);
		Assertions.assertThat(bean.getTotalCapacity()).isEqualTo(totalCapacity);
		Assertions.assertThat(bean.getStudyLocations()).isEqualTo(studyLocations);
		Assertions.assertThat(bean.getLevels()).isEqualTo(levels);
		Assertions.assertThat(bean.getScopes()).isEqualTo(scopes);
		Assertions.assertThat(bean.getCategories()).isEqualTo(categories);
		Assertions.assertThat(bean.getSubCategories()).isEqualTo(subCategories);
		Assertions.assertThat(bean.getStartDate()).isEqualTo(startDate);
		Assertions.assertThat(bean.getEndDate()).isEqualTo(endDate);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		Assertions.assertThat(Statistics.create()).hasNoNullFieldsOrPropertiesExcept("startDate", "endDate");
		Assertions.assertThat(new Statistics()).hasNoNullFieldsOrPropertiesExcept("startDate", "endDate");
	}
}
