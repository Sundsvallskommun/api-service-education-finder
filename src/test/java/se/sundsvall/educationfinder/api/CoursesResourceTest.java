package se.sundsvall.educationfinder.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
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
	void findAllOnSpecificStudyLocationAndNameAndEndLessThan() {

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path("/courses")
				.queryParam("studyLocation", "Sundsvall")
				.queryParam("name", "vardags")
				.queryParam("end", "2024-02-28")
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
				tuple("Vardagsrutiner och vardagsmiljö", "Sundsvall", null),
				tuple("Vardagsteknik", "Sundsvall", null),
				tuple("Vardagsmatematik del 2", "Sundsvall", null),
				tuple("Vardagsolyckor", "Sundsvall", LocalDate.of(2023, 11, 17)),
				tuple("Vardagsolyckor", "Sundsvall", LocalDate.of(2023, 12, 22)));
		assertThat(response.getMetadata().getCount()).isEqualTo(5);
		assertThat(response.getMetadata().getLimit()).isEqualTo(20);
		assertThat(response.getMetadata().getPage()).isZero();
		assertThat(response.getMetadata().getTotalRecords()).isEqualTo(5);
		assertThat(response.getMetadata().getTotalPages()).isEqualTo(1);
	}

	@Test
	void findAllOnSpecificStudyLocationAndNameAndStartGreaterThan() {

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path("/courses")
				.queryParam("studyLocation", "Sundsvall")
				.queryParam("name", "vardags")
				.queryParam("start", "2020-01-01")
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
				tuple("Vardagsrutiner och vardagsmiljö", "Sundsvall", null),
				tuple("Vardagsteknik", "Sundsvall", null),
				tuple("Vardagsmatematik del 2", "Sundsvall", null),
				tuple("Vardagsolyckor", "Sundsvall", LocalDate.of(2023, 10, 16)),
				tuple("Vardagsolyckor", "Sundsvall", LocalDate.of(2023, 10, 16)),
				tuple("Vardagsolyckor", "Sundsvall", LocalDate.of(2023, 10, 16)));
		assertThat(response.getMetadata().getCount()).isEqualTo(6);
		assertThat(response.getMetadata().getLimit()).isEqualTo(20);
		assertThat(response.getMetadata().getPage()).isZero();
		assertThat(response.getMetadata().getTotalRecords()).isEqualTo(6);
		assertThat(response.getMetadata().getTotalPages()).isEqualTo(1);
	}
}
