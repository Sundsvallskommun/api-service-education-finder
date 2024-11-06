package se.sundsvall.educationfinder.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import se.sundsvall.educationfinder.api.model.CourseParameters;
import se.sundsvall.educationfinder.integration.db.CourseRepository;
import se.sundsvall.educationfinder.integration.db.model.CourseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.Sort.Direction.DESC;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

	@Mock
	private Page<CourseEntity> pageMock;

	@Mock
	private CourseRepository courseRepositoryMock;

	@InjectMocks
	private CourseService courseService;

	@Test
	void find() {
		var courseParameters = CourseParameters.create()
			.withCategories(List.of("category"))
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

}
