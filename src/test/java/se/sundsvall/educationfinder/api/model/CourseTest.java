package se.sundsvall.educationfinder.api.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static com.google.code.beanmatchers.BeanMatchers.registerValueGenerator;
import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CourseTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), LocalDate.class);
	}

	@Test
	void testBean() {
		assertThat(Course.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {

		final var code = "code";
		final var credits = 50.0;
		final var provider = "provider";
		final var end = LocalDate.now().plusDays(1);
		final var earliestApplication = LocalDate.now().plusDays(2);
		final var latestApplication = LocalDate.now().plusDays(4);
		final var level = "level";
		final var name = "name";
		final var numberOfSeats = 1;
		final var providerUrl = "providerUrl";
		final var recommendedInformation = "recommendedInformation";
		final var scope = 100.0;
		final var start = LocalDate.now().plusDays(4);
		final var studyLocation = "studyLocation";
		final var subjectCode = "subjectCode";
		final var url = "url";

		final var bean = Course.create()
			.withCode(code)
			.withCredits(credits)
			.withProvider(provider)
			.withEnd(end)
			.withEarliestApplication(earliestApplication)
			.withLatestApplication(latestApplication)
			.withLevel(level)
			.withName(name)
			.withNumberOfSeats(numberOfSeats)
			.withProviderUrl(providerUrl)
			.withRecommendedInformation(recommendedInformation)
			.withScope(scope)
			.withStart(start)
			.withStudyLocation(studyLocation)
			.withSubjectCode(subjectCode)
			.withUrl(url);

		assertThat(bean).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(bean.getCode()).isEqualTo(code);
		assertThat(bean.getCredits()).isEqualTo(credits);
		assertThat(bean.getProvider()).isEqualTo(provider);
		assertThat(bean.getEnd()).isEqualTo(end);
		assertThat(bean.getEarliestApplication()).isEqualTo(earliestApplication);
		assertThat(bean.getLatestApplication()).isEqualTo(latestApplication);
		assertThat(bean.getLevel()).isEqualTo(level);
		assertThat(bean.getName()).isEqualTo(name);
		assertThat(bean.getNumberOfSeats()).isEqualTo(numberOfSeats);
		assertThat(bean.getProviderUrl()).isEqualTo(providerUrl);
		assertThat(bean.getRecommendedInformation()).isEqualTo(recommendedInformation);
		assertThat(bean.getScope()).isEqualTo(scope);
		assertThat(bean.getStart()).isEqualTo(start);
		assertThat(bean.getStudyLocation()).isEqualTo(studyLocation);
		assertThat(bean.getSubjectCode()).isEqualTo(subjectCode);
		assertThat(bean.getUrl()).isEqualTo(url);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(Course.create()).hasAllNullFieldsOrProperties();
		assertThat(new Course()).hasAllNullFieldsOrProperties();
	}
}
