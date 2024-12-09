package se.sundsvall.educationfinder.service.mapper;

import static java.util.Collections.emptyList;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import se.sundsvall.dept44.models.api.paging.PagingMetaData;
import se.sundsvall.educationfinder.api.model.Course;
import se.sundsvall.educationfinder.api.model.PagedCoursesResponse;
import se.sundsvall.educationfinder.integration.db.model.CourseEntity;

public final class CourseMapper {

	private CourseMapper() {}

	public static List<Course> toCourseList(List<CourseEntity> courseEntityList) {
		return Optional.ofNullable(courseEntityList).orElse(emptyList()).stream()
			.map(CourseMapper::toCourse)
			.toList();
	}

	public static Course toCourse(CourseEntity courseEntity) {
		return Optional.ofNullable(courseEntity)
			.map(entity -> Course.create()
				.withCategory(Optional.ofNullable(entity.getCategory())
					.map(category -> category.split(" - ", 2)[0].trim())
					.orElse(null))
				.withSubcategory(Optional.ofNullable(entity.getCategory())
					.map(category -> category.split(" - ", 2)[1].trim())
					.orElse(null))
				.withId(entity.getId())
				.withCode(entity.getCode())
				.withCredits(toDouble(entity.getCredits()))
				.withEarliestApplication(entity.getEarliestApplication())
				.withProvider(entity.getProvider())
				.withEnd(entity.getEnd())
				.withLatestApplication(entity.getLatestApplication())
				.withLevel(entity.getLevel())
				.withName(entity.getName())
				.withNumberOfSeats(entity.getNumberOfSeats())
				.withProviderUrl(entity.getProviderUrl())
				.withInformation(entity.getInformation())
				.withScope(toDouble(entity.getScope()))
				.withStart(entity.getStart())
				.withStudyLocation(entity.getStudyLocation())
				.withSubjectCode(entity.getSubjectCode())
				.withLanguageOfInstruction(entity.getLanguageOfInstruction())
				.withUrl(entity.getUrl()))
			.orElse(null);
	}

	public static PagedCoursesResponse toPagedCoursesResponse(Page<CourseEntity> courseEntityPage) {
		return Optional.ofNullable(courseEntityPage)
			.map(page -> PagedCoursesResponse.create()
				.withCourses(toCourseList(page.getContent()))
				.withMetaData(PagingMetaData.create()
					.withPage(page.getPageable().getPageNumber())
					.withLimit(page.getPageable().getPageSize())
					.withCount(page.getNumberOfElements())
					.withTotalRecords(page.getTotalElements())
					.withTotalPages(page.getTotalPages())))
			.orElse(null);
	}

	private static Double toDouble(BigDecimal bigDecimal) {
		return Optional.ofNullable(bigDecimal)
			.map(BigDecimal::doubleValue)
			.orElse(null);
	}
}
