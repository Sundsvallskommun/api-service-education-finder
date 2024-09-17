package se.sundsvall.educationfinder.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.time.LocalDate;
import java.util.List;
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

	private static String PATH = "/2281/courses";

	private static Stream<Arguments> findAllStudyLocationProvider() {
		return Stream.of(
			Arguments.of(List.of("Sundsvall"), 843, 43),
			Arguments.of(List.of("Härnösand"), 6, 1),
			Arguments.of(List.of("Sundsvall", "Härnösand"), 849, 43));
	}

	private static Stream<Arguments> findAllScopeProvider() {
		return Stream.of(
			Arguments.of(List.of(25), 257, 13),
			Arguments.of(List.of(50), 270, 14),
			Arguments.of(List.of(25, 50), 527, 27));
	}

	private static Stream<Arguments> findAllLevelProvider() {
		return Stream.of(
			Arguments.of(List.of("grundläggande vuxenutbildning"), 144, 8),
			Arguments.of(List.of("gymnasial vuxenutbildning"), 648, 33),
			Arguments.of(List.of("grundläggande vuxenutbildning", "gymnasial vuxenutbildning"), 792, 40));
	}

	private static Stream<Arguments> queryParameters() {
		return Stream.of(
			Arguments.of("Sundsvall", "grundläggande vuxenutbildning", "matematik", 22),
			Arguments.of("Sundsvall", "grundläggande vuxenutbildning", "kemi", 4),
			Arguments.of("Sundsvall", "gymnasial vuxenutbildning", "psy", 9));
	}

	@Test
	void findAll() {

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
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
			.uri(builder -> builder.path(PATH)
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

	@ParameterizedTest
	@MethodSource("findAllStudyLocationProvider")
	void findAllOnStudyLocation(List<String> studyLocations, int expectedRecords, int expectedPages) {
		// Act
		var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("studyLocation", studyLocations)
				.build())
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(PagedCoursesResponse.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isNotNull();
		assertThat(response.getCourses()).hasSizeLessThanOrEqualTo(20);
		assertThat(response.getMetadata().getLimit()).isEqualTo(20);
		assertThat(response.getMetadata().getTotalRecords()).isEqualTo(expectedRecords);
		assertThat(response.getMetadata().getTotalPages()).isEqualTo(expectedPages);
		assertThat(response.getMetadata().getPage()).isZero();
	}

	@ParameterizedTest
	@MethodSource("findAllLevelProvider")
	void findAllOnLevel(List<String> levels, int expectedRecords, int expectedPages) {
		// Act
		var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("level", levels)
				.build())
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(PagedCoursesResponse.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isNotNull();
		assertThat(response.getCourses()).hasSizeLessThanOrEqualTo(20);
		assertThat(response.getMetadata().getLimit()).isEqualTo(20);
		assertThat(response.getMetadata().getTotalRecords()).isEqualTo(expectedRecords);
		assertThat(response.getMetadata().getTotalPages()).isEqualTo(expectedPages);
		assertThat(response.getMetadata().getPage()).isZero();
	}

	@ParameterizedTest
	@MethodSource("findAllScopeProvider")
	void findAllOnScope(List<Integer> scopes, int expectedRecords, int expectedPages) {
		// Act
		var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("scope", scopes)
				.build())
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(PagedCoursesResponse.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isNotNull();
		assertThat(response.getCourses()).hasSizeLessThanOrEqualTo(20);
		assertThat(response.getMetadata().getLimit()).isEqualTo(20);
		assertThat(response.getMetadata().getTotalRecords()).isEqualTo(expectedRecords);
		assertThat(response.getMetadata().getTotalPages()).isEqualTo(expectedPages);
		assertThat(response.getMetadata().getPage()).isZero();
	}

	@Test
	void findAllOnSpecificCredits() {

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
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
			.uri(builder -> builder.path(PATH)
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
			.uri(builder -> builder.path(PATH)
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
			.uri(builder -> builder.path(PATH)
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
			.uri(PATH + "/filters/credits/values")
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
			.uri(PATH + "/filters/provider/values")
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
			.uri(PATH + "/filters/level/values")
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
			.uri(PATH + "/filters/scope/values")
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
			.uri(PATH + "/filters/studyLocation/values")
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


	@ParameterizedTest
	@MethodSource("queryParameters")
	void findAllOnSpecificStudyLocationAndLevelWithCustomSearchString(final String studyLocation, final String level, final String searchString, final Integer matches) {

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
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

	@Test
	void findByCourseId() {
		final var response = webTestClient.get()
			.uri(PATH + "/6032")
			.exchange()
			.expectStatus().isOk()
			.expectBody(Course.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo(6032);
		assertThat(response.getStudyLocation()).isEqualTo("Sundsvall");
		assertThat(response.getProvider()).isEqualTo("Sundsvalls Kommun");
		assertThat(response.getProviderUrl()).isEqualTo("http://sundsvall.se/vuxenutbildning");
		assertThat(response.getCode()).isEqualTo("PEDBAS0");
		assertThat(response.getName()).isEqualTo("Barns lärande och växande");
		assertThat(response.getLevel()).isEqualTo("gymnasial vuxenutbildning");
	}
}
