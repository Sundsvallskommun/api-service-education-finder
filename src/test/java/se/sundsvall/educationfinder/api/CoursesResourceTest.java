package se.sundsvall.educationfinder.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import se.sundsvall.educationfinder.Application;
import se.sundsvall.educationfinder.api.model.Course;
import se.sundsvall.educationfinder.api.model.PagedCoursesResponse;

@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@ActiveProfiles("junit")
class CoursesResourceTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void findAll() {

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path("/courses")
				.queryParam("size", "30")
				.build())
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(PagedCoursesResponse.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isNotNull();
		assertThat(response.getCourses()).hasSize(30);
		assertThat(response.getMetadata().getCount()).isEqualTo(30);
		assertThat(response.getMetadata().getLimit()).isEqualTo(30);
		assertThat(response.getMetadata().getPage()).isZero();
		assertThat(response.getMetadata().getTotalRecords()).isEqualTo(863);
		assertThat(response.getMetadata().getTotalPages()).isEqualTo(29);
	}

	@Test
	void findWithNoResults() {

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path("/courses")
				.queryParam("studyLocation", "Ibiza")
				.build())
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(PagedCoursesResponse.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isNotNull();
		assertThat(response.getCourses()).isEmpty();
		assertThat(response.getMetadata().getCount()).isZero();
		assertThat(response.getMetadata().getLimit()).isEqualTo(20);
		assertThat(response.getMetadata().getPage()).isZero();
		assertThat(response.getMetadata().getTotalRecords()).isZero();
		assertThat(response.getMetadata().getTotalPages()).isZero();
	}

	@Test
	void findAllOnSpecificStudyLocation() {

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path("/courses")
				.queryParam("studyLocation", "Härnösand")
				.build())
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(PagedCoursesResponse.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isNotNull();
		assertThat(response.getCourses())
			.hasSize(6)
			.extracting(Course::getStudyLocation).containsOnly("Härnösand");
		assertThat(response.getCourses()).hasSize(6);
		assertThat(response.getMetadata().getCount()).isEqualTo(6);
		assertThat(response.getMetadata().getLimit()).isEqualTo(20);
		assertThat(response.getMetadata().getPage()).isZero();
		assertThat(response.getMetadata().getTotalRecords()).isEqualTo(6);
		assertThat(response.getMetadata().getTotalPages()).isEqualTo(1);
	}

	@Test
	void findAllOnSpecificCredits() {

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path("/courses")
				.queryParam("credits", "325")
				.build())
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(PagedCoursesResponse.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isNotNull();
		assertThat(response.getCourses())
			.hasSize(2)
			.extracting(Course::getCredits).containsOnly(325.0);
		assertThat(response.getCourses()).hasSize(2);
		assertThat(response.getMetadata().getCount()).isEqualTo(2);
		assertThat(response.getMetadata().getLimit()).isEqualTo(20);
		assertThat(response.getMetadata().getPage()).isZero();
		assertThat(response.getMetadata().getTotalRecords()).isEqualTo(2);
		assertThat(response.getMetadata().getTotalPages()).isEqualTo(1);
	}

	@Test
	void findAllOnSpecificStudyLocationAndName() {

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path("/courses")
				.queryParam("studyLocation", "Härnösand")
				.queryParam("name", "Drifttekniker Kraft & Värme")
				.build())
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(PagedCoursesResponse.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isNotNull();
		assertThat(response.getCourses())
			.extracting(Course::getName, Course::getStudyLocation)
			.containsExactlyInAnyOrder(
				tuple("Drifttekniker Kraft & Värme", "Härnösand"),
				tuple("Drifttekniker Kraft & Värme", "Härnösand"));

		assertThat(response.getMetadata().getCount()).isEqualTo(2);
		assertThat(response.getMetadata().getLimit()).isEqualTo(20);
		assertThat(response.getMetadata().getPage()).isZero();
		assertThat(response.getMetadata().getTotalRecords()).isEqualTo(2);
		assertThat(response.getMetadata().getTotalPages()).isEqualTo(1);
	}

	@Test
	void findAllOnSpecificStudyLocationAndNameAndEndBefore() {

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path("/courses")
				.queryParam("studyLocation", "Sundsvall")
				.queryParam("name", "vardags")
				.queryParam("endBefore", "2024-02-28")
				.build())
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(PagedCoursesResponse.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isNotNull();
		assertThat(response.getCourses())
			.extracting(Course::getName, Course::getStudyLocation, Course::getEnd)
			.containsExactlyInAnyOrder(
				tuple("Vardagsolyckor", "Sundsvall", LocalDate.of(2023, 11, 17)),
				tuple("Vardagsolyckor", "Sundsvall", LocalDate.of(2023, 12, 22)));
		assertThat(response.getMetadata().getCount()).isEqualTo(2);
		assertThat(response.getMetadata().getLimit()).isEqualTo(20);
		assertThat(response.getMetadata().getPage()).isZero();
		assertThat(response.getMetadata().getTotalRecords()).isEqualTo(2);
		assertThat(response.getMetadata().getTotalPages()).isEqualTo(1);
	}

	@Test
	void findAllOnSpecificStudyLocationAndNameAndStartAfter() {

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path("/courses")
				.queryParam("studyLocation", "Sundsvall")
				.queryParam("name", "vardags")
				.queryParam("startAfter", "2020-01-01")
				.build())
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(PagedCoursesResponse.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isNotNull();
		assertThat(response.getCourses())
			.extracting(Course::getName, Course::getStudyLocation, Course::getStart)
			.containsExactlyInAnyOrder(
				tuple("Vardagsolyckor", "Sundsvall", LocalDate.of(2023, 10, 16)),
				tuple("Vardagsolyckor", "Sundsvall", LocalDate.of(2023, 10, 16)),
				tuple("Vardagsolyckor", "Sundsvall", LocalDate.of(2023, 10, 16)));
		assertThat(response.getMetadata().getCount()).isEqualTo(3);
		assertThat(response.getMetadata().getLimit()).isEqualTo(20);
		assertThat(response.getMetadata().getPage()).isZero();
		assertThat(response.getMetadata().getTotalRecords()).isEqualTo(3);
		assertThat(response.getMetadata().getTotalPages()).isEqualTo(1);
	}

	@Test
	void findFilterValuesForCredits() {

		// Act
		final var response = webTestClient.get()
			.uri("/courses/filters/credits/values")
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(String[].class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response)
			.hasSize(19)
			.containsExactly(
				"0.0",
				"50.0",
				"100.0",
				"150.0",
				"200.0",
				"300.0",
				"325.0",
				"350.0",
				"400.0",
				"415.0",
				"430.0",
				"450.0",
				"500.0",
				"600.0",
				"1000.0",
				"1200.0",
				"1400.0",
				"1450.0",
				"1500.0");
	}

	@Test
	void findFilterValuesForProvider() {

		// Act
		final var response = webTestClient.get()
			.uri("/courses/filters/provider/values")
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(String[].class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response)
			.hasSize(6)
			.containsExactly(
				"Härnösands kommun, Yrkeshögskolan",
				"Kramfors kommun, Yrkeshögskolan Höga kusten",
				"One Academy AB",
				"Sundsvalls Kommun",
				"Sundsvalls kommun, Vuxenutbildningen",
				"YH Akademin AB");
	}

	@Test
	void findFilterValuesForLevel() {

		// Act
		final var response = webTestClient.get()
			.uri("/courses/filters/level/values")
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(String[].class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response)
			.hasSize(5)
			.containsExactly(
				"grundläggande vuxenutbildning",
				"gymnasial vuxenutbildning",
				"Kommunal vuxenutbildning som anpassad utbildning på grundläggande nivå",
				"Kommunal vuxenutbildning som anpassad utbildning på gymnasial nivå",
				"yrkeshögskoleutbildning");
	}

	@Test
	void findFilterValuesForScope() {

		// Act
		final var response = webTestClient.get()
			.uri("/courses/filters/scope/values")
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(String[].class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response)
			.hasSize(4)
			.containsExactly("25.0", "50.0", "75.0", "100.0");
	}

	@Test
	void findFilterValuesForStudyLocation() {

		// Act
		final var response = webTestClient.get()
			.uri("/courses/filters/studyLocation/values")
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(String[].class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response)
			.hasSize(5)
			.containsExactly("HÄRNÖSAND", "KRAMFORS", "SUNDSVALL", "ÖRNSKÖLDSVIK", "ÖSTERSUND");
	}

	private static Stream<Arguments> queryParameters() {
		return Stream.of(
			Arguments.of("Sundsvall", "vuxenutbildning", "matematik", 101),
			Arguments.of("Sundsvall", "grundläggande", "kemi", 5),
			Arguments.of("Kramfors", "yrkeshögskoleutbildning", "möbelsnickare", 3));
	}

	@ParameterizedTest
	@MethodSource("queryParameters")
	void findAllOnSpecificStudyLocationAndLevelWithCustomSearchString(final String studyLocation, final String level, final String searchString, final Integer matches) {

		final var response = webTestClient.get()
			.uri(builder -> builder.path("/courses")
				.queryParam("studyLocation", studyLocation)
				.queryParam("level", level)
				.queryParam("searchString", searchString)
				.build())
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(PagedCoursesResponse.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getMetadata().getLimit()).isEqualTo(20);
		assertThat(response.getMetadata().getPage()).isZero();
		assertThat(response.getMetadata().getTotalRecords()).isEqualTo(matches.longValue());
		assertThat(response.getMetadata().getTotalPages()).isEqualTo((matches.longValue() / 20) + 1);
	}
}
