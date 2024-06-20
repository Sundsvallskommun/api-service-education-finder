package se.sundsvall.educationfinder.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.Sort.Direction.DESC;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.CREDITS;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.LEVEL;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.PROVIDER;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.SCOPE;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.STUDY_LOCATION;
import static se.sundsvall.educationfinder.integration.db.model.SubjectEntity_.CATEGORY;
import static se.sundsvall.educationfinder.integration.db.model.SubjectEntity_.CATEGORY_ID;
import static se.sundsvall.educationfinder.integration.db.model.SubjectEntity_.EDUCATION_FORM;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import se.sundsvall.educationfinder.api.model.enums.CourseFilter;
import se.sundsvall.educationfinder.api.model.enums.SubjectFilter;
import se.sundsvall.educationfinder.integration.db.CourseRepository;
import se.sundsvall.educationfinder.integration.db.SubjectRepository;
import se.sundsvall.educationfinder.integration.db.model.CourseEntity;
import se.sundsvall.educationfinder.integration.db.model.projection.CategoryIdProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.CategoryProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.CreditsProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.EducationFormProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.LevelProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.ProviderProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.ScopeProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.StudyLocationProjection;
import se.sundsvall.educationfinder.integration.db.specification.CourseSpecification;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

	@Mock
	private Page<CourseEntity> pageMock;

	@Mock
	private CourseSpecification courseSpecificationMock;

	@Mock
	private CourseRepository courseRepositoryMock;

	@Mock
	private SubjectRepository subjectRepositoryMock;

	@InjectMocks
	private CourseService courseService;

	@Test
	void find() {

		// Arrange
		final var pageRequest = PageRequest.of(0, 10, Sort.by(DESC, "name"));

		when(pageMock.getContent()).thenReturn(List.of(CourseEntity.create()));
		when(pageMock.getPageable()).thenReturn(pageRequest);
		when(courseRepositoryMock.findAll(any(CourseSpecification.class), eq(pageRequest))).thenReturn(pageMock);

		// Act
		final var result = courseService.find(courseSpecificationMock, pageRequest);

		// Assert
		assertThat(result).isNotNull();
		verify(courseRepositoryMock).findAll(courseSpecificationMock, pageRequest);
	}

	@ParameterizedTest
	@MethodSource("findFilterValuesProvider")
	void findFilterValues(CourseFilter courseFilter, Class<?> projectionClass, String attributeName) {

		courseService.findFilterValues(courseFilter);

		// Assert
		verify(courseRepositoryMock).findDistinctBy(projectionClass, Sort.by(attributeName));
	}


	@ParameterizedTest
	@MethodSource("findStatisticsFilterValuesProvider")
	void findStatisticsFilterValues(SubjectFilter subjectFilter, Class<?> projectionClass, String attributeName) {

		courseService.findStatisticsFilterValues(subjectFilter);

		// Assert
		verify(subjectRepositoryMock).findDistinctBy(projectionClass, Sort.by(attributeName));
	}

	@Test
	void findSubjectFilterStudyLocationValues() {
		courseService.findStatisticsFilterValues(SubjectFilter.STUDY_LOCATION);
		verify(courseRepositoryMock).findDistinctBy(StudyLocationProjection.class, Sort.by(STUDY_LOCATION));
	}

	private static Stream<Arguments> findFilterValuesProvider() {
		return Stream.of(
			Arguments.of(CourseFilter.STUDY_LOCATION, StudyLocationProjection.class, STUDY_LOCATION),
			Arguments.of(CourseFilter.PROVIDER, ProviderProjection.class, PROVIDER),
			Arguments.of(CourseFilter.LEVEL, LevelProjection.class, LEVEL),
			Arguments.of(CourseFilter.SCOPE, ScopeProjection.class, SCOPE),
			Arguments.of(CourseFilter.CREDITS, CreditsProjection.class, CREDITS));
	}

	private static Stream<Arguments> findStatisticsFilterValuesProvider() {
		return Stream.of(
			Arguments.of(SubjectFilter.CATEGORY, CategoryProjection.class, CATEGORY),
			Arguments.of(SubjectFilter.CATEGORY_ID, CategoryIdProjection.class, CATEGORY_ID),
			Arguments.of(SubjectFilter.EDUCATION_FORM, EducationFormProjection.class, EDUCATION_FORM));
	}
}
