package se.sundsvall.educationfinder.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import se.sundsvall.educationfinder.api.model.Course;
import se.sundsvall.educationfinder.integration.db.model.CourseEntity;

@ExtendWith(MockitoExtension.class)
class CourseMapperTest {

	private static final String CODE = "code";

	private static final BigDecimal CREDITS = new BigDecimal("50.0");

	private static final String PROVIDER = "provider";

	private static final LocalDate START = LocalDate.now();

	private static final LocalDate END = LocalDate.now().plusDays(1);

	private static final LocalDate EARLIEST_APPLICATION = LocalDate.now().plusDays(2);

	private static final LocalDate LATEST_APPLICATION = LocalDate.now().plusDays(1);

	private static final String LEVEL = "level";

	private static final String NAME = "name";

	private static final Integer NUMBER_OF_SEATS = 1;

	private static final String PROVIDER_URL = "providerUrl";

	private static final String INFORMATION = "information";

	private static final BigDecimal SCOPE = new BigDecimal("100.0");

	private static final String STUDY_LOCATION = "studyLocation";

	private static final String SUBJECT_CODE = "subjectCode";

	private static final String URL = "url";

	@Test
	void toCourse() {

		// Arrange
		final var entity = createCourseEntity();

		// Act
		final var result = CourseMapper.toCourse(entity);

		// Assert
		assertThat(result)
			.isNotNull()
			.isEqualTo(Course.create()
				.withCode(CODE)
				.withCredits(CREDITS.doubleValue())
				.withEarliestApplication(EARLIEST_APPLICATION)
				.withProvider(PROVIDER)
				.withEnd(END)
				.withLatestApplication(LATEST_APPLICATION)
				.withLevel(LEVEL)
				.withName(NAME)
				.withNumberOfSeats(NUMBER_OF_SEATS)
				.withProviderUrl(PROVIDER_URL)
				.withInformation(INFORMATION)
				.withScope(SCOPE.doubleValue())
				.withStart(START)
				.withStudyLocation(STUDY_LOCATION)
				.withSubjectCode(SUBJECT_CODE)
				.withUrl(URL));
	}

	@Test
	void toCourseWhenInputIsNull() {
		assertThat(CourseMapper.toCourse(null)).isNull();
	}

	@Test
	void toCourseList() {

		// Arrange
		final var entityList = List.of(createCourseEntity());

		// Act
		final var result = CourseMapper.toCourseList(entityList);

		// Assert
		assertThat(result)
			.isNotNull()
			.containsExactly(Course.create()
				.withCode(CODE)
				.withCredits(CREDITS.doubleValue())
				.withEarliestApplication(EARLIEST_APPLICATION)
				.withProvider(PROVIDER)
				.withEnd(END)
				.withLatestApplication(LATEST_APPLICATION)
				.withLevel(LEVEL)
				.withName(NAME)
				.withNumberOfSeats(NUMBER_OF_SEATS)
				.withProviderUrl(PROVIDER_URL)
				.withInformation(INFORMATION)
				.withScope(SCOPE.doubleValue())
				.withStart(START)
				.withStudyLocation(STUDY_LOCATION)
				.withSubjectCode(SUBJECT_CODE)
				.withUrl(URL));
	}

	@Test
	void toCourseListWhenInputIsNull() {
		assertThat(CourseMapper.toCourseList(null)).isNotNull().isEmpty();
	}

	@Test
	void toPagedCoursesResponse(@Mock final Page<CourseEntity> pageMock, @Mock final Pageable pageableMock) {

		final var entityList = List.of(createCourseEntity());

		when(pageableMock.getPageSize()).thenReturn(20);
		when(pageMock.getPageable()).thenReturn(pageableMock);
		when(pageMock.getContent()).thenReturn(entityList);
		when(pageMock.getNumberOfElements()).thenReturn(1);
		when(pageMock.getTotalElements()).thenReturn(1L);
		when(pageMock.getTotalPages()).thenReturn(1);

		// Act
		final var result = CourseMapper.toPagedCoursesResponse(pageMock);

		// Assert
		assertThat(result).isNotNull();
		assertThat(result.getMetadata()).isNotNull();
		assertThat(result.getMetadata().getCount()).isEqualTo(1);
		assertThat(result.getMetadata().getLimit()).isEqualTo(20);
		assertThat(result.getMetadata().getPage()).isZero();
		assertThat(result.getMetadata().getTotalPages()).isEqualTo(1);
		assertThat(result.getMetadata().getTotalRecords()).isEqualTo(1L);
		assertThat(result.getCourses())
			.isNotNull()
			.containsExactly(Course.create()
				.withCode(CODE)
				.withCredits(CREDITS.doubleValue())
				.withEarliestApplication(EARLIEST_APPLICATION)
				.withProvider(PROVIDER)
				.withEnd(END)
				.withLatestApplication(LATEST_APPLICATION)
				.withLevel(LEVEL)
				.withName(NAME)
				.withNumberOfSeats(NUMBER_OF_SEATS)
				.withProviderUrl(PROVIDER_URL)
				.withInformation(INFORMATION)
				.withScope(SCOPE.doubleValue())
				.withStart(START)
				.withStudyLocation(STUDY_LOCATION)
				.withSubjectCode(SUBJECT_CODE)
				.withUrl(URL));
	}

	@Test
	void toPagedCoursesResponseWhenInputIsNull() {
		assertThat(CourseMapper.toPagedCoursesResponse(null)).isNull();
	}

	private CourseEntity createCourseEntity() {
		return CourseEntity.create()
			.withCode(CODE)
			.withCredits(CREDITS)
			.withProvider(PROVIDER)
			.withEnd(END)
			.withEarliestApplication(EARLIEST_APPLICATION)
			.withLatestApplication(LATEST_APPLICATION)
			.withLevel(LEVEL)
			.withName(NAME)
			.withNumberOfSeats(NUMBER_OF_SEATS)
			.withProviderUrl(PROVIDER_URL)
			.withInformation(INFORMATION)
			.withScope(SCOPE)
			.withStart(START)
			.withStudyLocation(STUDY_LOCATION)
			.withSubjectCode(SUBJECT_CODE)
			.withUrl(URL);
	}

}
