package se.sundsvall.educationfinder.api;

import static java.util.Optional.ofNullable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.CATEGORY;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.CATEGORY_ID;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.EDUCATION_FORM;
import static se.sundsvall.educationfinder.api.model.enums.StatisticsFilter.END_DATE;
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

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void findCategoryIdFilterValues() {
		final var response = webTestClient.get()
			.uri(builder -> builder.path("/statistics/filters/{statisticsFilter}/values").build(Map.of("statisticsFilter", CATEGORY_ID.name())))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(String[].class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response).containsExactlyInAnyOrder("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21");
	}

	@Test
	void findCategoryFilterValues() {
		final var response = webTestClient.get()
			.uri(builder -> builder.path("/statistics/filters/{statisticsFilter}/values").build(Map.of("statisticsFilter", CATEGORY.name())))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(String[].class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response).containsExactlyInAnyOrder("BYGG OCH ANLÄGGNING",
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
		var response = webTestClient.get()
			.uri(builder -> builder.path("/statistics/filters/{statisticsFilter}/values").build(Map.of("statisticsFilter", EDUCATION_FORM.name())))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(String[].class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response).containsExactlyInAnyOrder("AUB", "FHSK", "SV", "UOH", "YH");
	}

	@Test
	void findStudyLocationFilterValues() {
		final var response = webTestClient.get()
			.uri(builder -> builder.path("/statistics/filters/{statisticsFilter}/values").build(Map.of("statisticsFilter", STUDY_LOCATION.name())))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(String[].class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response).containsExactlyInAnyOrder("HÄRNÖSAND", "KRAMFORS", "SUNDSVALL", "ÖRNSKÖLDSVIK", "ÖSTERSUND");
	}

	@Test
	void findStartDateFilterValues() {
		final var response = webTestClient.get()
			.uri(builder -> builder.path("/statistics/filters/{statisticsFilter}/values").build(Map.of("statisticsFilter", START_DATE.name())))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(String[].class)
			.returnResult()
			.getResponseBody();
		assertThat(response).isNotNull();
		assertThat(response).containsExactlyInAnyOrder("Any date in this format: yyyy-MM-dd");
	}

	@Test
	void findEndDateFilterValues() {
		final var response = webTestClient.get()
			.uri(builder -> builder.path("/statistics/filters/{statisticsFilter}/values").build(Map.of("statisticsFilter", END_DATE.name())))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(String[].class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response).containsExactlyInAnyOrder("Any date in this format: yyyy-MM-dd");
	}


	@Test
	void getStatisticsByStudyLocation() {
		var studyLocation = "sundsvall";
		var startDate = LocalDate.of(2023, 1, 1);
		var endDate = LocalDate.of(2023, 8, 1);

		final var response = webTestClient.get()
			.uri(builder -> builder
				.path("/statistics")
				.queryParams(createParameterMap(null, null, null, List.of(studyLocation), startDate, endDate))
				.build())
			.exchange()
			.expectStatus().isOk()
			.expectBody(Statistics.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getFinishedCourses()).isEqualTo(0);
		assertThat(response.getOnGoingCourses()).isEqualTo(0);
		assertThat(response.getPlannedCourses()).isEqualTo(0);
		assertThat(response.getAvailableSeats()).isEqualTo(0);
		assertThat(response.getTotalCapacity()).isEqualTo(0);

		assertThat(response.getStartDate()).isEqualTo(startDate);
		assertThat(response.getEndDate()).isEqualTo(endDate);
		assertThat(response.getCategories()).isEmpty();
		assertThat(response.getCategoryIds()).isEmpty();
		assertThat(response.getEducationForms()).isEmpty();
		assertThat(response.getStudyLocations()).containsExactly(studyLocation);
	}

	@Test
	void getStatisticsByDates() {
		var startDate = LocalDate.of(2023, 1, 1);
		var endDate = LocalDate.of(2024, 6, 1);

		final var response = webTestClient.get()
			.uri(builder -> builder
				.path("/statistics")
				.queryParams(createParameterMap(null, null, null, null, startDate, endDate))
				.build())
			.exchange()
			.expectStatus().isOk()
			.expectBody(Statistics.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getFinishedCourses()).isEqualTo(772);
		assertThat(response.getOnGoingCourses()).isEqualTo(0);
		assertThat(response.getPlannedCourses()).isEqualTo(792);
		assertThat(response.getAvailableSeats()).isEqualTo(0);
		assertThat(response.getTotalCapacity()).isEqualTo(0);

		assertThat(response.getStartDate()).isEqualTo(startDate);
		assertThat(response.getEndDate()).isEqualTo(endDate);
		assertThat(response.getCategories()).isEmpty();
		assertThat(response.getCategoryIds()).isEmpty();
		assertThat(response.getStudyLocations()).isEmpty();
		assertThat(response.getEducationForms()).isEmpty();
	}

	@Test
	void getStatisticsByCategory() {
		var category = "Data och IT";
		var startDate = LocalDate.of(2023, 1, 1);
		var endDate = LocalDate.of(2024, 8, 1);

		final var response = webTestClient.get()
			.uri(builder -> builder
				.path("/statistics")
				.queryParams(createParameterMap(List.of(category), null, null, null, startDate, endDate))
				.build())
			.exchange()
			.expectStatus().isOk()
			.expectBody(Statistics.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getFinishedCourses()).isEqualTo(47);
		assertThat(response.getOnGoingCourses()).isEqualTo(0);
		assertThat(response.getPlannedCourses()).isEqualTo(47);
		assertThat(response.getAvailableSeats()).isEqualTo(0);
		assertThat(response.getTotalCapacity()).isEqualTo(0);

		assertThat(response.getStartDate()).isEqualTo(startDate);
		assertThat(response.getEndDate()).isEqualTo(endDate);
		assertThat(response.getCategories()).containsExactly(category);
		assertThat(response.getCategoryIds()).isEmpty();
		assertThat(response.getStudyLocations()).isEmpty();
		assertThat(response.getEducationForms()).isEmpty();
	}

	@Test
	void getStatisticsByCategoryId() {
		var categoryId = "1";
		var startDate = LocalDate.of(2022, 1, 1);
		var endDate = LocalDate.of(2024, 10, 1);

		final var response = webTestClient.get()
			.uri(builder -> builder
				.path("/statistics")
				.queryParams(createParameterMap(null, List.of(categoryId), null, null, startDate, endDate))
				.build())
			.exchange()
			.expectStatus().isOk()
			.expectBody(Statistics.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getFinishedCourses()).isEqualTo(18);
		assertThat(response.getOnGoingCourses()).isEqualTo(0);
		assertThat(response.getPlannedCourses()).isEqualTo(18);
		assertThat(response.getAvailableSeats()).isEqualTo(0);
		assertThat(response.getTotalCapacity()).isEqualTo(0);

		assertThat(response.getStartDate()).isEqualTo(startDate);
		assertThat(response.getEndDate()).isEqualTo(endDate);
		assertThat(response.getCategoryIds()).containsExactly(categoryId);
		assertThat(response.getCategories()).isEmpty();
		assertThat(response.getStudyLocations()).isEmpty();
		assertThat(response.getEducationForms()).isEmpty();
	}

	@Test
	void getStatisticsByEducationForm() {
		var educationForm = "SV";
		var startDate = LocalDate.of(2023, 3, 1);
		var endDate = LocalDate.of(2024, 2, 1);

		final var response = webTestClient.get()
			.uri(builder -> builder
				.path("/statistics")
				.queryParams(createParameterMap(null, null, List.of(educationForm), null, startDate, endDate))
				.build())
			.exchange()
			.expectStatus().isOk()
			.expectBody(Statistics.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getFinishedCourses()).isEqualTo(520);
		assertThat(response.getOnGoingCourses()).isEqualTo(0);
		assertThat(response.getPlannedCourses()).isEqualTo(792);
		assertThat(response.getAvailableSeats()).isEqualTo(0);
		assertThat(response.getTotalCapacity()).isEqualTo(0);

		assertThat(response.getStartDate()).isEqualTo(startDate);
		assertThat(response.getEndDate()).isEqualTo(endDate);
		assertThat(response.getEducationForms()).containsExactly(educationForm);
		assertThat(response.getCategoryIds()).isEmpty();
		assertThat(response.getCategories()).isEmpty();
		assertThat(response.getStudyLocations()).isEmpty();
	}

	private MultiValueMap<String, String> createParameterMap(final List<String> categories, final List<String> categoryIds,
		final List<String> educationForms, final List<String> studyLocations, final LocalDate startDate, final LocalDate endDate) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

		ofNullable(categories).ifPresent(p -> parameters.addAll("categories", p));
		ofNullable(categoryIds).ifPresent(p -> parameters.addAll("categoryIds", p));
		ofNullable(educationForms).ifPresent(p -> parameters.addAll("educationForms", p));
		ofNullable(studyLocations).ifPresent(p -> parameters.addAll("studyLocations", p));
		ofNullable(startDate).ifPresent(p -> parameters.add("startDate", String.valueOf(p)));
		ofNullable(endDate).ifPresent(p -> parameters.add("endDate", String.valueOf(p)));

		return parameters;
	}

}
