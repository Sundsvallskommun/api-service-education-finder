package se.sundsvall.educationfinder.service;

import static org.mockito.Mockito.verify;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.STUDY_LOCATION;
import static se.sundsvall.educationfinder.integration.db.model.SubjectEntity_.CATEGORY;
import static se.sundsvall.educationfinder.integration.db.model.SubjectEntity_.CATEGORY_ID;
import static se.sundsvall.educationfinder.integration.db.model.SubjectEntity_.EDUCATION_FORM;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import se.sundsvall.educationfinder.api.model.enums.StatisticsFilter;
import se.sundsvall.educationfinder.integration.db.CourseRepository;
import se.sundsvall.educationfinder.integration.db.SubjectRepository;
import se.sundsvall.educationfinder.integration.db.model.projection.CategoryIdProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.CategoryProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.EducationFormProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.StudyLocationProjection;

@ExtendWith(MockitoExtension.class)
class StatisticsServiceTest {

	@Mock
	private SubjectRepository subjectRepositoryMock;

	@Mock
	private CourseRepository courseRepositoryMock;

	@InjectMocks
	private StatisticsService statisticsService;

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
