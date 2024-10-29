package se.sundsvall.educationfinder.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.Sort.Direction.DESC;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.CATEGORY;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.CREDITS;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.LEVEL;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.PROVIDER;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.SCOPE;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.STUDY_LOCATION;

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

import se.sundsvall.educationfinder.api.model.CourseParameters;
import se.sundsvall.educationfinder.api.model.enums.CourseFilter;
import se.sundsvall.educationfinder.integration.db.CourseRepository;
import se.sundsvall.educationfinder.integration.db.model.CourseEntity;
import se.sundsvall.educationfinder.integration.db.model.projection.CategoryProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.CreditsProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.LevelProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.ProviderProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.ScopeProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.StudyLocationProjection;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

	private static final String SUBCATEGORY = "subcategory";

	@Mock
	private Page<CourseEntity> pageMock;

	@Mock
	private CourseRepository courseRepositoryMock;

	@InjectMocks
	private CourseService courseService;

	private static Stream<Arguments> findFilterValuesProvider() {
		return Stream.of(
			Arguments.of(CourseFilter.STUDY_LOCATION, StudyLocationProjection.class, STUDY_LOCATION),
			Arguments.of(CourseFilter.CATEGORY, CategoryProjection.class, CATEGORY),
			Arguments.of(CourseFilter.SUBCATEGORY, CategoryProjection.class, CATEGORY),
			Arguments.of(CourseFilter.PROVIDER, ProviderProjection.class, PROVIDER),
			Arguments.of(CourseFilter.LEVEL, LevelProjection.class, LEVEL),
			Arguments.of(CourseFilter.SCOPE, ScopeProjection.class, SCOPE),
			Arguments.of(CourseFilter.CREDITS, CreditsProjection.class, CREDITS));
	}

	@Test
	void find() {
		var courseParameters = CourseParameters.create()
			.withCategory("category")
			.withLimit(10)
			.withPage(1)
			.withSortBy(List.of("name"))
			.withSortDirection(DESC);
		var pageRequest = PageRequest.of(courseParameters.getPage() - 1, courseParameters.getLimit(), courseParameters.sort());

		when(pageMock.getContent()).thenReturn(List.of(CourseEntity.create()));
		when(pageMock.getPageable()).thenReturn(pageRequest);
		when(courseRepositoryMock.findAllByCourseParameters(any(CourseParameters.class), eq(pageRequest))).thenReturn(pageMock);

		var result = courseService.find(courseParameters);

		assertThat(result).isNotNull();
		assertThat(result.getCourses()).hasSize(1);
		verify(courseRepositoryMock).findAllByCourseParameters(courseParameters, pageRequest);
	}

	@ParameterizedTest
	@MethodSource("findFilterValuesProvider")
	void findFilterValues(final CourseFilter courseFilter, final Class<?> projectionClass, final String attributeName) {
		
		courseService.findFilterValues(courseFilter);

		verify(courseRepositoryMock).findDistinctBy(projectionClass, Sort.by(attributeName));
	}


}
