package se.sundsvall.educationfinder.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.Sort.Direction.DESC;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import se.sundsvall.educationfinder.integration.db.CourseRepository;
import se.sundsvall.educationfinder.integration.db.model.CourseEntity;
import se.sundsvall.educationfinder.integration.db.specification.CourseSpecification;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

	@Mock
	private Page<CourseEntity> pageMock;

	@Mock
	private CourseSpecification courseSpecificationMock;

	@Mock
	private CourseRepository courseRepositoryMock;

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
}
