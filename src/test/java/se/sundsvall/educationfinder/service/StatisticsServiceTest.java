package se.sundsvall.educationfinder.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import se.sundsvall.dept44.test.annotation.resource.Load;
import se.sundsvall.dept44.test.extension.ResourceLoaderExtension;
import se.sundsvall.educationfinder.api.model.StatisticsParameters;
import se.sundsvall.educationfinder.integration.db.CourseRepository;
import se.sundsvall.educationfinder.integration.db.model.CourseEntity;
import se.sundsvall.educationfinder.integration.db.model.projection.LevelProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.ScopeProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.StudyLocationProjection;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.LEVEL;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.SCOPE;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.STUDY_LOCATION;

@ExtendWith(value = {
	MockitoExtension.class, ResourceLoaderExtension.class
})
class StatisticsServiceTest {

	private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

	@Mock
	private CourseRepository courseRepositoryMock;

	@InjectMocks
	private StatisticsService statisticsService;

	private static Stream<Arguments> findStatisticsCourseFilterValuesProvider() {
		return Stream.of(
			Arguments.of("level", LevelProjection.class, LEVEL),
			Arguments.of("studyLocation", StudyLocationProjection.class, STUDY_LOCATION),
			Arguments.of("scope", ScopeProjection.class, SCOPE));
	}

	@Test
	void calculateStatistics(@Load(value = "mockdata/courses.json", as = Load.ResourceType.STRING) final String value) throws IOException {
		final var courses = objectMapper.readValue(value, new TypeReference<List<CourseEntity>>() {

		});

		final var parameters = new StatisticsParameters().withStartDate(LocalDate.of(2023, 1, 1)).withEndDate(LocalDate.of(2023, 12, 31));

		final var result = statisticsService.calculateStatistics(parameters, courses);

		assertThat(result.getAvailableSeats()).isEqualTo(137);
		assertThat(result.getOnGoingCourses()).isEqualTo(3);
		assertThat(result.getPlannedCourses()).isEqualTo(18);
		assertThat(result.getFinishedCourses()).isEqualTo(4);
	}

	@ParameterizedTest
	@MethodSource("findStatisticsCourseFilterValuesProvider")
	void findStatisticsCourseFilterValues(final String attribute, final Class<?> projectionClass, final String attributeName) {

		statisticsService.findStatisticsFilterValues(attribute);

		// Assert
		verify(courseRepositoryMock).findDistinctBy(projectionClass, Sort.by(attributeName));
	}

}
