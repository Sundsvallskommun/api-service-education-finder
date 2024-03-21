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

	private final static String CODE = "code";
	private final static BigDecimal CREDITS = new BigDecimal(50.0);
	private final static String PROVIDER = "provider";
	private final static LocalDate START = LocalDate.now();
	private final static LocalDate END = LocalDate.now().plusDays(1);
	private final static LocalDate EARLIEST_APPLICATION = LocalDate.now().plusDays(2);
	private final static LocalDate LATEST_APPLICATION = LocalDate.now().plusDays(1);
	private final static String LEVEL = "level";
	private final static String NAME = "name";
	private final static Integer NUMBER_OF_SEATS = 1;
	private final static String PROVIDER_URL = "providerUrl";
	private final static String RECOMMENDED_INFORMATION = "recommendedInformation";
	private final static BigDecimal SCOPE = new BigDecimal(100.0);
	private final static String STUDY_LOCATION = "studyLocation";
	private final static String SUBJECT_CODE = "subjectCode";
	private final static String URL = "url";

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
				.withRecommendedInformation(RECOMMENDED_INFORMATION)
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
				.withRecommendedInformation(RECOMMENDED_INFORMATION)
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
	void toPagedCoursesResponse(@Mock Page<CourseEntity> pageMock, @Mock Pageable pageableMock) {

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
				.withRecommendedInformation(RECOMMENDED_INFORMATION)
				.withScope(SCOPE.doubleValue())
				.withStart(START)
				.withStudyLocation(STUDY_LOCATION)
				.withSubjectCode(SUBJECT_CODE)
				.withUrl(URL));
	}

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
			.withRecommendedInformation(RECOMMENDED_INFORMATION)
			.withScope(SCOPE)
			.withStart(START)
			.withStudyLocation(STUDY_LOCATION)
			.withSubjectCode(SUBJECT_CODE)
			.withUrl(URL);
	}
}
