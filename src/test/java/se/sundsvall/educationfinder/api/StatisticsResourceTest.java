package se.sundsvall.educationfinder.api;

import static java.util.Optional.ofNullable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.CATEGORY;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.CATEGORY_ID;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.END_DATE;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.LEVEL;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.START_DATE;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.STUDY_LOCATION;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import se.sundsvall.educationfinder.Application;
import se.sundsvall.educationfinder.api.model.Statistics;


@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@ActiveProfiles("junit")
class StatisticsResourceTest {

	private static final String BASE_PATH = "/2281/statistics";

	private static final String FILTER_PATH = BASE_PATH + "/filters/{statisticsFilter}/values";

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void findCategoryIdFilterValues() {
		final var response = webTestClient.get()
			.uri(builder -> builder.path(FILTER_PATH).build(Map.of("statisticsFilter", CATEGORY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(String[].class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull().containsExactlyInAnyOrder("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21");
	}

	@Test
	void findCategoryFilterValues() {
		final var response = webTestClient.get()
			.uri(builder -> builder.path(FILTER_PATH).build(Map.of("statisticsFilter", CATEGORY)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(String[].class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull().containsExactlyInAnyOrder("BYGG OCH ANLÄGGNING",
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
			"NATURBRUK",
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
	void findEducationFormFilterValues() {
		final var response = webTestClient.get()
			.uri(builder -> builder.path(FILTER_PATH).build(Map.of("statisticsFilter", LEVEL)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(String[].class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull().containsExactlyInAnyOrder(
			"GRUNDLÄGGANDE VUXENUTBILDNING",
			"GYMNASIAL VUXENUTBILDNING",
			"KOMMUNAL VUXENUTBILDNING SOM ANPASSAD UTBILDNING PÅ GRUNDLÄGGANDE NIVÅ",
			"KOMMUNAL VUXENUTBILDNING SOM ANPASSAD UTBILDNING PÅ GYMNASIAL NIVÅ",
			"YRKESHÖGSKOLEUTBILDNING");
	}

	@Test
	void findStudyLocationFilterValues() {
		final var response = webTestClient.get()
			.uri(builder -> builder.path(FILTER_PATH).build(Map.of("statisticsFilter", STUDY_LOCATION)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(String[].class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull().containsExactlyInAnyOrder("HÄRNÖSAND", "KRAMFORS", "SUNDSVALL", "ÖRNSKÖLDSVIK", "ÖSTERSUND");
	}

	@Test
	void findStartDateFilterValues() {
		final var response = webTestClient.get()
			.uri(builder -> builder.path(FILTER_PATH).build(Map.of("statisticsFilter", START_DATE)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(String[].class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull().containsExactlyInAnyOrder("Any date in this format: yyyy-MM-dd");
	}

	@Test
	void findEndDateFilterValues() {
		final var response = webTestClient.get()
			.uri(builder -> builder.path(FILTER_PATH).build(Map.of("statisticsFilter", END_DATE)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(String[].class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull().containsExactlyInAnyOrder("Any date in this format: yyyy-MM-dd");
	}

	@Test
	void getStatisticsByStudyLocation() {
		final var studyLocations = List.of("HÄRNÖSAND", "KRAMFORS", "SUNDSVALL");
		final var startDate = LocalDate.of(2023, 1, 1);
		final var endDate = LocalDate.of(2023, 8, 1);

		final var response = webTestClient.get()
			.uri(builder -> builder
				.path(BASE_PATH)
				.queryParams(createParameterMap(null, null, null, null, studyLocations, startDate, endDate))
				.build())
			.exchange()
			.expectStatus().isOk()
			.expectBody(Statistics.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getFinishedCourses()).isZero();
		assertThat(response.getOnGoingCourses()).isZero();
		assertThat(response.getPlannedCourses()).isZero();
		assertThat(response.getAvailableSeats()).isZero();
		assertThat(response.getTotalCapacity()).isZero();

		assertThat(response.getStartDate()).isEqualTo(startDate);
		assertThat(response.getEndDate()).isEqualTo(endDate);
		assertThat(response.getCategories()).isEmpty();
		assertThat(response.getCategoryIds()).isEmpty();
		assertThat(response.getScopes()).isEmpty();
		assertThat(response.getLevels()).isEmpty();
		assertThat(response.getStudyLocations()).containsExactlyElementsOf(studyLocations);
	}

	@Test
	void getStatisticsByDates() {
		final var startDate = LocalDate.of(2023, 1, 1);
		final var endDate = LocalDate.of(2024, 6, 1);

		final var response = webTestClient.get()
			.uri(builder -> builder
				.path(BASE_PATH)
				.queryParams(createParameterMap(null, null, null, null, null, startDate, endDate))
				.build())
			.exchange()
			.expectStatus().isOk()
			.expectBody(Statistics.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getFinishedCourses()).isEqualTo(772);
		assertThat(response.getPlannedCourses()).isEqualTo(802);
		assertThat(response.getOnGoingCourses()).isZero();
		assertThat(response.getAvailableSeats()).isZero();
		assertThat(response.getTotalCapacity()).isZero();

		assertThat(response.getStartDate()).isEqualTo(startDate);
		assertThat(response.getEndDate()).isEqualTo(endDate);
		assertThat(response.getCategories()).isEmpty();
		assertThat(response.getCategoryIds()).isEmpty();
		assertThat(response.getStudyLocations()).isEmpty();
		assertThat(response.getLevels()).isEmpty();
		assertThat(response.getScopes()).isEmpty();
	}

	@Test
	void getStatisticsByCategory() {
		final var categories = List.of("FRISK- OCH SKÖNHETSVÅRD", "FÖRBEREDANDE UTBILDNINGAR", "HANTVERK");
		final var startDate = LocalDate.of(2023, 1, 1);
		final var endDate = LocalDate.of(2024, 8, 1);

		final var response = webTestClient.get()
			.uri(builder -> builder
				.path(BASE_PATH)
				.queryParams(createParameterMap(categories, null, null, null, null, startDate, endDate))
				.build())
			.exchange()
			.expectStatus().isOk()
			.expectBody(Statistics.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getFinishedCourses()).isEqualTo(12);
		assertThat(response.getPlannedCourses()).isEqualTo(12);
		assertThat(response.getOnGoingCourses()).isZero();
		assertThat(response.getAvailableSeats()).isZero();
		assertThat(response.getTotalCapacity()).isZero();

		assertThat(response.getStartDate()).isEqualTo(startDate);
		assertThat(response.getEndDate()).isEqualTo(endDate);
		assertThat(response.getCategories()).containsExactlyElementsOf(categories);
		assertThat(response.getCategoryIds()).isEmpty();
		assertThat(response.getStudyLocations()).isEmpty();
		assertThat(response.getLevels()).isEmpty();
		assertThat(response.getScopes()).isEmpty();
	}

	@Test
	void getStatisticsByCategoryId() {
		final var categoryIds = List.of("1", "2", "3", "4", "5");
		final var startDate = LocalDate.of(2022, 1, 1);
		final var endDate = LocalDate.of(2024, 10, 1);

		final var response = webTestClient.get()
			.uri(builder -> builder
				.path(BASE_PATH)
				.queryParams(createParameterMap(null, categoryIds, null, null, null, startDate, endDate))
				.build())
			.exchange()
			.expectStatus().isOk()
			.expectBody(Statistics.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getFinishedCourses()).isEqualTo(156);
		assertThat(response.getPlannedCourses()).isEqualTo(156);
		assertThat(response.getOnGoingCourses()).isZero();
		assertThat(response.getAvailableSeats()).isZero();
		assertThat(response.getTotalCapacity()).isZero();

		assertThat(response.getStartDate()).isEqualTo(startDate);
		assertThat(response.getEndDate()).isEqualTo(endDate);
		assertThat(response.getCategoryIds()).containsExactlyElementsOf(categoryIds);
		assertThat(response.getCategories()).isEmpty();
		assertThat(response.getStudyLocations()).isEmpty();
		assertThat(response.getLevels()).isEmpty();
		assertThat(response.getScopes()).isEmpty();
	}

	@Test
	void getStatisticsByScopes() {
		final var scopes = List.of("25", "75", "100");
		final var startDate = LocalDate.of(2023, 3, 1);
		final var endDate = LocalDate.of(2024, 2, 1);

		final var response = webTestClient.get()
			.uri(builder -> builder
				.path(BASE_PATH)
				.queryParams(createParameterMap(null, null, scopes, null, null, startDate, endDate))
				.build())
			.exchange()
			.expectStatus().isOk()
			.expectBody(Statistics.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getFinishedCourses()).isEqualTo(276);
		assertThat(response.getPlannedCourses()).isEqualTo(533);
		assertThat(response.getOnGoingCourses()).isZero();
		assertThat(response.getAvailableSeats()).isZero();
		assertThat(response.getTotalCapacity()).isZero();

		assertThat(response.getStartDate()).isEqualTo(startDate);
		assertThat(response.getEndDate()).isEqualTo(endDate);
		assertThat(response.getScopes()).containsExactlyElementsOf(scopes);
		assertThat(response.getLevels()).isEmpty();
		assertThat(response.getCategoryIds()).isEmpty();
		assertThat(response.getCategories()).isEmpty();
		assertThat(response.getStudyLocations()).isEmpty();
	}

	@Test
	void getStatisticsByLevels() {
		final var levels = List.of("grundläggande vuxenutbildning", "gymnasial vuxenutbildning");
		final var startDate = LocalDate.of(2023, 3, 1);
		final var endDate = LocalDate.of(2024, 2, 1);

		final var response = webTestClient.get()
			.uri(builder -> builder
				.path(BASE_PATH)
				.queryParams(createParameterMap(null, null, null, levels, null, startDate, endDate))
				.build())
			.exchange()
			.expectStatus().isOk()
			.expectBody(Statistics.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getFinishedCourses()).isEqualTo(520);
		assertThat(response.getPlannedCourses()).isEqualTo(792);
		assertThat(response.getOnGoingCourses()).isZero();
		assertThat(response.getAvailableSeats()).isZero();
		assertThat(response.getTotalCapacity()).isZero();

		assertThat(response.getStartDate()).isEqualTo(startDate);
		assertThat(response.getEndDate()).isEqualTo(endDate);
		assertThat(response.getLevels()).containsExactlyElementsOf(levels);
		assertThat(response.getScopes()).isEmpty();
		assertThat(response.getCategoryIds()).isEmpty();
		assertThat(response.getCategories()).isEmpty();
		assertThat(response.getStudyLocations()).isEmpty();
	}

	private MultiValueMap<String, String> createParameterMap(final List<String> categories, final List<String> categoryIds,
		final List<String> scopes, final List<String> levels, final List<String> studyLocations, final LocalDate startDate, final LocalDate endDate) {
		final MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

		ofNullable(categories).ifPresent(p -> parameters.addAll("categories", p));
		ofNullable(categoryIds).ifPresent(p -> parameters.addAll("categoryIds", p));
		ofNullable(scopes).ifPresent(p -> parameters.addAll("scopes", p));
		ofNullable(levels).ifPresent(p -> parameters.addAll("levels", p));
		ofNullable(studyLocations).ifPresent(p -> parameters.addAll("studyLocations", p));
		ofNullable(startDate).ifPresent(p -> parameters.add("startDate", String.valueOf(p)));
		ofNullable(endDate).ifPresent(p -> parameters.add("endDate", String.valueOf(p)));

		return parameters;
	}

}
