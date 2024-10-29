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

	private static final String PATH = "/2281/courses";

	@Autowired
	private WebTestClient webTestClient;

	private static Stream<Arguments> findAllStudyLocationProvider() {
		return Stream.of(
			Arguments.of(List.of("Sundsvall"), 1068, 54),
			Arguments.of(List.of("Härnösand"), 45, 3),
			Arguments.of(List.of("Sundsvall", "Härnösand"), 1113, 56));
	}

	private static Stream<Arguments> findAllScopeProvider() {
		return Stream.of(
			Arguments.of(List.of(25), 310, 16),
			Arguments.of(List.of(50), 367, 19),
			Arguments.of(List.of(25, 50), 677, 34));
	}

	private static Stream<Arguments> findAllLevelProvider() {
		return Stream.of(
			Arguments.of(List.of("grundläggande vuxenutbildning"), 146, 8),
			Arguments.of(List.of("gymnasial vuxenutbildning"), 782, 40),
			Arguments.of(List.of("grundläggande vuxenutbildning", "gymnasial vuxenutbildning"), 928, 47));
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
				.queryParam("limit", "30")
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
		assertThat(response.getMetadata().getTotalRecords()).isEqualTo(1561);
		assertThat(response.getMetadata().getTotalPages()).isEqualTo(53);
	}

	@Test
	void findWithNoResults() {

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("studyLocations", "Ibiza")
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
		assertThat(response.getMetadata().getLimit()).isEqualTo(100);
		assertThat(response.getMetadata().getPage()).isZero();
		assertThat(response.getMetadata().getTotalRecords()).isZero();
		assertThat(response.getMetadata().getTotalPages()).isZero();
	}

	@ParameterizedTest
	@MethodSource("findAllStudyLocationProvider")
	void findAllOnStudyLocation(final List<String> studyLocations, final int expectedRecords, final int expectedPages) {
		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("studyLocations", studyLocations)
				.queryParam("limit", "20")
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
	void findAllOnLevel(final List<String> levels, final int expectedRecords, final int expectedPages) {
		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("levels", levels)
				.queryParam("limit", "20")
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
	void findAllOnScope(final List<Integer> scopes, final int expectedRecords, final int expectedPages) {
		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("scopes", scopes)
				.queryParam("limit", "20")
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
				.queryParam("limit", "25")
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
			.extracting(Course::getCredits).containsOnly(325.0, 325.0, 325.0, 325.0, 325.0, 325.0);
		assertThat(response.getMetadata().getCount()).isEqualTo(6);
		assertThat(response.getMetadata().getLimit()).isEqualTo(25);
		assertThat(response.getMetadata().getPage()).isZero();
		assertThat(response.getMetadata().getTotalRecords()).isEqualTo(6);
		assertThat(response.getMetadata().getTotalPages()).isEqualTo(1);
	}

	@Test
	void findAllOnSpecificStudyLocationAndName() {

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("studyLocation", "Härnösand")
				.queryParam("name", "Drifttekniker Kraft & Värme")
				.queryParam("limit", "25")
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

		assertThat(response.getMetadata().getLimit()).isEqualTo(25);
		assertThat(response.getMetadata().getCount()).isEqualTo(2);
		assertThat(response.getMetadata().getPage()).isZero();
		assertThat(response.getMetadata().getTotalRecords()).isEqualTo(2);
		assertThat(response.getMetadata().getTotalPages()).isEqualTo(1);
	}

	@Test
	void findAllOnSpecificStudyLocationAndNameAndEndBefore() {

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("studyLocations", "Sundsvall")
				.queryParam("name", "Vardagsolyckor")
				.queryParam("endBefore", "2024-02-28")
				.queryParam("limit", "20")
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
				tuple("Vardagsolyckor", "Sundsvall", LocalDate.of(2023, 11, 17)),
				tuple("Vardagsolyckor", "Sundsvall", LocalDate.of(2023, 12, 22)),
				tuple("Vardagsolyckor", "Sundsvall", LocalDate.of(2023, 12, 22)),
				tuple("Vardagsolyckor", "Sundsvall", LocalDate.of(2024, 3, 1)),
				tuple("Vardagsolyckor", "Sundsvall", LocalDate.of(2024, 3, 1)));
		assertThat(response.getMetadata().getLimit()).isEqualTo(20);
		assertThat(response.getMetadata().getCount()).isEqualTo(6);
		assertThat(response.getMetadata().getPage()).isZero();
		assertThat(response.getMetadata().getTotalRecords()).isEqualTo(6);
		assertThat(response.getMetadata().getTotalPages()).isEqualTo(1);
	}

	@Test
	void findAllOnSpecificStudyLocationAndNameAndStartAfter() {

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("studyLocation", "Sundsvall")
				.queryParam("name", "Vardagsolyckor")
				.queryParam("startAfter", "2020-01-01")
				.queryParam("limit", "20")
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
				tuple("Vardagsolyckor", "Sundsvall", LocalDate.of(2023, 10, 16)),
				tuple("Vardagsolyckor", "Sundsvall", LocalDate.of(2023, 10, 16)),
				tuple("Vardagsolyckor", "Sundsvall", LocalDate.of(2023, 10, 16)),
				tuple("Vardagsolyckor", "Sundsvall", LocalDate.of(2023, 10, 16)));
		assertThat(response.getMetadata().getLimit()).isEqualTo(20);
		assertThat(response.getMetadata().getCount()).isEqualTo(6);
		assertThat(response.getMetadata().getPage()).isZero();
		assertThat(response.getMetadata().getTotalRecords()).isEqualTo(6);
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
			.hasSize(22)
			.containsExactly(
				"0.0",
				"15.0",
				"50.0",
				"60.0",
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
				"700.0",
				"1000.0",
				"1200.0",
				"1400.0",
				"1450.0",
				"1500.0");
	}

	@Test
	void findFilterValuesForCategory() {
		final var response = webTestClient.get()
			.uri(PATH + "/filters/category/values")
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(String[].class)
			.returnResult()
			.getResponseBody();

		assertThat(response)
			.hasSize(20)
			.containsExactlyInAnyOrder("BYGG OCH ANLÄGGNING",
				"DATA OCH IT",
				"EKONOMI, MARKNADSFÖRING OCH ADMINISTRATION",
				"FRISK- OCH SKÖNHETSVÅRD",
				"FÖRBEREDANDE UTBILDNINGAR",
				"HANTVERK",
				"HOTELL, RESTAURANG OCH TURISM",
				"INFORMATION OCH MEDIA",
				"KONSTNÄRLIGA UTBILDNINGAR",
				"KULTUR OCH HUMANISTISKA ÄMNEN",
				"MEDICIN OCH VÅRD",
				"NATURVETENSKAP",
				"SAMHÄLLSVETENSKAP OCH JURIDIK",
				"SPRÅK",
				"SÄKERHET, FÖRSVAR OCH RÄDDNINGSTJÄNST",
				"TEKNIK",
				"TILLVERKNING OCH UNDERHÅLL",
				"TRANSPORT",
				"UNDERVISNING OCH IDROTT",
				"ÖVRIGA KURSER OCH TVÄRVETENSKAP");
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
			.hasSize(22)
			.containsExactlyInAnyOrder(
				"Blue Peak AB",
				"Föreningen Mellansels folkhögskola",
				"Hermods AB Umeå",
				"Hola folkhögskola",
				"Härnösands folkhögskola",
				"Härnösands kommun, Yrkeshögskolan",
				"ITH, Institutet För Tillämpad Hydraulik",
				"Järnakademien Ångermanland",
				"Klart Skepp Marinteknik AB",
				"Kramfors kommun, Yrkeshögskolan Höga kusten",
				"Lexicon Yrkeshögskola AB",
				"Mellansels folkhögskola",
				"One Academy AB",
				"ProTrain Utbildning AB",
				"Sollefteå kommun - Reveljen",
				"Sundsvalls Kommun",
				"Sundsvalls kommun, Vuxenutbildningen",
				"TCC Sverige AB",
				"YH Akademin AB",
				"Ålsta folkhögskola",
				"Ålsta folkhögskola, filial Sundsvall",
				"Örnsköldsviks folkhögskola");
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
			.hasSize(6)
			.containsExactly("folkhögskola",
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
			.hasSize(13)
			.containsExactlyInAnyOrder("BODEN",
				"FRÄNSTA",
				"HELSINGBORG",
				"HÄRNÖSAND",
				"KRAMFORS",
				"MELLANSEL",
				"NYLAND",
				"SKELLEFTEÅ",
				"SOLLEFTEÅ",
				"SUNDSVALL",
				"UMEÅ",
				"ÖRNSKÖLDSVIK",
				"ÖSTERSUND");
	}


	@ParameterizedTest
	@MethodSource("queryParameters")
	void findAllOnSpecificStudyLocationAndLevelWithCustomSearchString(final String studyLocation, final String level, final String searchString, final Integer matches) {

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("studyLocations", studyLocation)
				.queryParam("levels", level)
				.queryParam("searchString", searchString)
				.queryParam("limit", "20")
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
	}

	@Test
	void findByCourseId() {
		final var response = webTestClient.get()
			.uri(PATH + "/2769")
			.exchange()
			.expectStatus().isOk()
			.expectBody(Course.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo(2769);
		assertThat(response.getStudyLocation()).isEqualTo("Sundsvall");
		assertThat(response.getProvider()).isEqualTo("Sundsvalls Kommun");
		assertThat(response.getProviderUrl()).isEqualTo("http://sundsvall.se/vuxenutbildning");
		assertThat(response.getCode()).isEqualTo("SVESVE01");
		assertThat(response.getName()).isEqualTo("Svenska 1 Kväll");
		assertThat(response.getCategory()).isEqualTo("Språk");
		assertThat(response.getSubcategory()).isEqualTo("Svenska");
		assertThat(response.getLevel()).isEqualTo("gymnasial vuxenutbildning");
	}

	@Test
	void findCourses() {
		var response = webTestClient.get()
			.uri(uriBuilder -> uriBuilder.path(PATH)
				.queryParam("category", "NATURVETENSKAP")
				.queryParam("limit", "20")
				.build())
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(PagedCoursesResponse.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getCourses()).hasSize(20).extracting(Course::getCategory).contains("Naturvetenskap");
		assertThat(response.getMetadata().getCount()).isEqualTo(20);
		assertThat(response.getMetadata().getLimit()).isEqualTo(20);
		assertThat(response.getMetadata().getPage()).isZero();
		assertThat(response.getMetadata().getTotalRecords()).isEqualTo(145);
	}

}
