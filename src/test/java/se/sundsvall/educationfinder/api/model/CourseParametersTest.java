package se.sundsvall.educationfinder.api.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToStringExcluding;
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
		final var code = "code";
		final var categories = List.of("category");
		final var subcategories = List.of("subcategory");
		final var name = "name";
		final var provider = "provider";
		final var credits = "credits";
		final var information = "information";
		final var languageOfInstruction = "languageOfInstruction";
		final var searchString = "searchString";
		final var start = LocalDate.now().plusDays(4);
		final var end = LocalDate.now().plusDays(1);
		final var earliestApplication = LocalDate.now().plusDays(2);
		final var latestApplication = LocalDate.now().plusDays(4);
		final var levels = List.of("level");
		final var studyLocations = List.of("studyLocation");
		final var scopes = List.of(100);
		final var placeOfStudyMunicipalityId = "2281";
		final var visitingAddressMunicipalityId = "2281";

		final var bean = CourseParameters.create()
			.withCode(code)
			.withCategories(categories)
			.withSubcategories(subcategories)
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
			.withScopes(scopes)
			.withPlaceOfStudyMunicipalityId(placeOfStudyMunicipalityId)
			.withVisitingAddressMunicipalityId(visitingAddressMunicipalityId);

		Assertions.assertThat(bean).isNotNull().hasNoNullFieldsOrPropertiesExcept("sortDirection", "page", "limit", "sortBy");
		Assertions.assertThat(bean.getCode()).isEqualTo(code);
		Assertions.assertThat(bean.getCategories()).isEqualTo(categories);
		Assertions.assertThat(bean.getSubcategories()).isEqualTo(subcategories);
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
		Assertions.assertThat(bean.getPlaceOfStudyMunicipalityId()).isEqualTo(placeOfStudyMunicipalityId);
		Assertions.assertThat(bean.getVisitingAddressMunicipalityId()).isEqualTo(visitingAddressMunicipalityId);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		Assertions.assertThat(CourseParameters.create()).hasAllNullFieldsOrPropertiesExcept("sortDirection", "page", "limit");
		Assertions.assertThat(new CourseParameters()).hasAllNullFieldsOrPropertiesExcept("sortDirection", "page", "limit");
	}
}
