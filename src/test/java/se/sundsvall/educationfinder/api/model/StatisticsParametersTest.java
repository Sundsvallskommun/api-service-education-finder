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
		var category = "category";
		var categoryId = "categoryId";
		var educationForm = "educationForm";
		var studyLocation = "studyLocation";
		var startDate = LocalDate.now();
		var endDate = LocalDate.now().plusDays(1);

		var bean = StatisticsParameters.create()
			.withCategory(category)
			.withCategoryId(categoryId)
			.withEducationForm(educationForm)
			.withStudyLocation(studyLocation)
			.withStartDate(startDate)
			.withEndDate(endDate);

		Assertions.assertThat(bean).isNotNull().hasNoNullFieldsOrProperties();
		Assertions.assertThat(bean.getCategory()).isEqualTo(category);
		Assertions.assertThat(bean.getCategoryId()).isEqualTo(categoryId);
		Assertions.assertThat(bean.getEducationForm()).isEqualTo(educationForm);
		Assertions.assertThat(bean.getStudyLocation()).isEqualTo(studyLocation);
		Assertions.assertThat(bean.getStartDate()).isEqualTo(startDate);
		Assertions.assertThat(bean.getEndDate()).isEqualTo(endDate);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		Assertions.assertThat(StatisticsParameters.create()).hasAllNullFieldsOrProperties();
		Assertions.assertThat(new StatisticsParameters()).hasAllNullFieldsOrProperties();
	}
}
