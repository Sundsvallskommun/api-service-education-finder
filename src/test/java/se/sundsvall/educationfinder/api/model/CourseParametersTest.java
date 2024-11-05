package se.sundsvall.educationfinder.api.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToStringExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static com.google.code.beanmatchers.BeanMatchers.registerValueGenerator;
import static java.time.LocalDate.now;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

class CourseParametersTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), LocalDate.class);
	}

	@Test
	void testBean() {
		assertThat(CourseParameters.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToStringExcluding("sortDirection", "page", "limit", "sortBy")));
	}

	@Test
	void testBuilderMethods() {

		var code = "code";
		var category = "category";
		var subcategory = "subcategory";
		var name = "name";
		var provider = "provider";
		var credits = "credits";
		var information = "information";
		var languageOfInstruction = "languageOfInstruction";
		var searchString = "searchString";
		var start = LocalDate.now().plusDays(4);
		var end = LocalDate.now().plusDays(1);
		var earliestApplication = LocalDate.now().plusDays(2);
		var latestApplication = LocalDate.now().plusDays(4);
		var levels = List.of("level");
		var studyLocations = List.of("studyLocation");
		var scopes = List.of("scope");

		var bean = CourseParameters.create()
			.withCode(code)
			.withCategory(category)
			.withSubcategory(subcategory)
			.withName(name)
			.withProvider(provider)
			.withCredits(credits)
			.withInformation(information)
			.withLanguageOfInstruction(languageOfInstruction)
			.withSearchString(searchString)
			.withStart(start)
			.withStartAfter(start)
			.withStartBefore(start)
			.withEnd(end)
			.withEndAfter(end)
			.withEndBefore(end)
			.withEarliestApplication(earliestApplication)
			.withEarliestApplicationAfter(earliestApplication)
			.withEarliestApplicationBefore(earliestApplication)
			.withLatestApplication(latestApplication)
			.withLatestApplicationAfter(latestApplication)
			.withLatestApplicationBefore(latestApplication)
			.withLevels(levels)
			.withStudyLocations(studyLocations)
			.withScopes(scopes);

		Assertions.assertThat(bean).isNotNull().hasNoNullFieldsOrPropertiesExcept("sortDirection", "page", "limit", "sortBy");
		Assertions.assertThat(bean.getCode()).isEqualTo(code);
		Assertions.assertThat(bean.getCategory()).isEqualTo(category);
		Assertions.assertThat(bean.getSubcategory()).isEqualTo(subcategory);
		Assertions.assertThat(bean.getName()).isEqualTo(name);
		Assertions.assertThat(bean.getProvider()).isEqualTo(provider);
		Assertions.assertThat(bean.getCredits()).isEqualTo(credits);
		Assertions.assertThat(bean.getInformation()).isEqualTo(information);
		Assertions.assertThat(bean.getLanguageOfInstruction()).isEqualTo(languageOfInstruction);
		Assertions.assertThat(bean.getSearchString()).isEqualTo(searchString);
		Assertions.assertThat(bean.getStart()).isEqualTo(start);
		Assertions.assertThat(bean.getStartAfter()).isEqualTo(start);
		Assertions.assertThat(bean.getStartBefore()).isEqualTo(start);
		Assertions.assertThat(bean.getEnd()).isEqualTo(end);
		Assertions.assertThat(bean.getEndAfter()).isEqualTo(end);
		Assertions.assertThat(bean.getEndBefore()).isEqualTo(end);
		Assertions.assertThat(bean.getEarliestApplication()).isEqualTo(earliestApplication);
		Assertions.assertThat(bean.getEarliestApplicationAfter()).isEqualTo(earliestApplication);
		Assertions.assertThat(bean.getEarliestApplicationBefore()).isEqualTo(earliestApplication);
		Assertions.assertThat(bean.getLatestApplication()).isEqualTo(latestApplication);
		Assertions.assertThat(bean.getLatestApplicationAfter()).isEqualTo(latestApplication);
		Assertions.assertThat(bean.getLatestApplicationBefore()).isEqualTo(latestApplication);
		Assertions.assertThat(bean.getLevels()).isEqualTo(levels);
		Assertions.assertThat(bean.getStudyLocations()).isEqualTo(studyLocations);
		Assertions.assertThat(bean.getScopes()).isEqualTo(scopes);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		Assertions.assertThat(CourseParameters.create()).hasAllNullFieldsOrPropertiesExcept("sortDirection", "page", "limit");
		Assertions.assertThat(new CourseParameters()).hasAllNullFieldsOrPropertiesExcept("sortDirection", "page", "limit");
	}
}
