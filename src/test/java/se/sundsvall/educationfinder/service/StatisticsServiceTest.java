package se.sundsvall.educationfinder.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.STUDY_LOCATION;
import static se.sundsvall.educationfinder.integration.db.model.SubjectEntity_.CATEGORY;
import static se.sundsvall.educationfinder.integration.db.model.SubjectEntity_.CATEGORY_ID;
import static se.sundsvall.educationfinder.integration.db.model.SubjectEntity_.EDUCATION_FORM;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

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
import se.sundsvall.educationfinder.api.model.enums.StatisticsFilter;
import se.sundsvall.educationfinder.integration.db.CourseRepository;
import se.sundsvall.educationfinder.integration.db.SubjectRepository;
import se.sundsvall.educationfinder.integration.db.model.CourseEntity;
import se.sundsvall.educationfinder.integration.db.model.projection.CategoryIdProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.CategoryProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.EducationFormProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.StudyLocationProjection;

@ExtendWith(value = {MockitoExtension.class, ResourceLoaderExtension.class})
class StatisticsServiceTest {

	@Mock
	private SubjectRepository subjectRepositoryMock;

	@Mock
	private CourseRepository courseRepositoryMock;

	@InjectMocks
	private StatisticsService statisticsService;

	private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

	@Test
	void calculateStatistics(@Load(value = "mockdata/courses.json", as = Load.ResourceType.STRING) String value) throws IOException {
		var courses = objectMapper.readValue(value, new TypeReference<List<CourseEntity>>() {
		});

		var parameters = new StatisticsParameters().withStartDate(LocalDate.of(2023, 1, 1)).withEndDate(LocalDate.of(2023, 12, 31));

		var result = statisticsService.calculateStatistics(parameters, courses);

		assertThat(result.getAvailableSeats()).isEqualTo(137);
		assertThat(result.getOnGoingCourses()).isEqualTo(3);
		assertThat(result.getPlannedCourses()).isEqualTo(18);
		assertThat(result.getFinishedCourses()).isEqualTo(4);
	}

	@ParameterizedTest
	@MethodSource("findStatisticsFilterValuesProvider")
	void findStatisticsFilterValues(StatisticsFilter statisticsFilter, Class<?> projectionClass, String attributeName) {

		statisticsService.findStatisticsFilterValues(statisticsFilter);

		// Assert
		verify(subjectRepositoryMock).findDistinctBy(projectionClass, Sort.by(attributeName));
	}

	@Test
	void findStatisticsFilterStudyLocationValues() {
		statisticsService.findStatisticsFilterValues(StatisticsFilter.STUDY_LOCATION);
		verify(courseRepositoryMock).findDistinctBy(StudyLocationProjection.class, Sort.by(STUDY_LOCATION));
	}

	private static Stream<Arguments> findStatisticsFilterValuesProvider() {
		return Stream.of(
			Arguments.of(StatisticsFilter.CATEGORY, CategoryProjection.class, CATEGORY),
			Arguments.of(StatisticsFilter.CATEGORY_ID, CategoryIdProjection.class, CATEGORY_ID),
			Arguments.of(StatisticsFilter.EDUCATION_FORM, EducationFormProjection.class, EDUCATION_FORM));
	}

}
