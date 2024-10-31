package se.sundsvall.educationfinder.service;

import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.CATEGORY;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.LEVEL;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.SCOPE;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.STUDY_LOCATION;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import se.sundsvall.educationfinder.api.model.Statistics;
import se.sundsvall.educationfinder.api.model.StatisticsParameters;
import se.sundsvall.educationfinder.api.model.enums.StatisticsFilter;
import se.sundsvall.educationfinder.integration.db.CourseRepository;
import se.sundsvall.educationfinder.integration.db.model.CourseEntity;
import se.sundsvall.educationfinder.integration.db.model.projection.CategoryProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.LevelProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.ScopeProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.StudyLocationProjection;

@Service
public class StatisticsService {

	private final CourseRepository courseRepository;

	public StatisticsService(final CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public Statistics getStatisticsByParameters(final StatisticsParameters parameters) {
		final var courses = courseRepository.findAllByParameters(parameters);
		return calculateStatistics(parameters, courses);
	}

	public Statistics calculateStatistics(final StatisticsParameters parameters, final List<CourseEntity> courses) {
		final var ongoingCourses = courses.stream()
			.filter(course -> course.getStart().isBefore(parameters.getStartDate()) && course.getEnd().isAfter(parameters.getEndDate()))
			.count();

		final var plannedCourses = courses.stream()
			.filter(course -> course.getStart().isAfter(parameters.getStartDate()) && course.getStart().isBefore(parameters.getEndDate()))
			.count();

		final var finishedCourses = courses.stream()
			.filter(course -> course.getEnd().isAfter(parameters.getStartDate()) && course.getEnd().isBefore(parameters.getEndDate()))
			.count();

		final var availableSeats = courses.stream()
			.filter(course -> course.getStart().isAfter(parameters.getStartDate()) && course.getStart().isBefore(parameters.getEndDate()))
			.filter(course -> course.getNumberOfSeats() != null)
			.mapToInt(CourseEntity::getNumberOfSeats)
			.sum();

		final var totalCapacity = courses.stream()
			.filter(course -> course.getNumberOfSeats() != null)
			.mapToInt(CourseEntity::getNumberOfSeats)
			.sum();

		return new Statistics()
			.withOnGoingCourses((int) ongoingCourses)
			.withPlannedCourses((int) plannedCourses)
			.withFinishedCourses((int) finishedCourses)
			.withTotalCapacity(totalCapacity)
			.withAvailableSeats(availableSeats)
			.withStartDate(parameters.getStartDate())
			.withEndDate(parameters.getEndDate())
			.withStudyLocations(parameters.getStudyLocations())
			.withScopes(parameters.getScopes())
			.withLevels(parameters.getLevels())
			.withSubCategories(parameters.getSubCategories())
			.withCategories(parameters.getCategories());
	}

	@Cacheable("statistics-filters")
	public List<String> findStatisticsFilterValues(final StatisticsFilter statisticsFilter) {
		return switch (statisticsFilter) {
			case CATEGORY -> courseRepository.findDistinctBy(CategoryProjection.class, Sort.by(CATEGORY)).stream()
				.filter(Objects::nonNull)
				.map(CategoryProjection::getCategory).map(category -> {
					var parts = category.split(" - ", 2);
					return parts[0].trim();
				})
				.distinct()
				.filter(StringUtils::isNotEmpty)
				.map(StringUtils::upperCase)
				.toList();
			case SUBCATEGORY -> courseRepository.findDistinctBy(CategoryProjection.class, Sort.by(CATEGORY)).stream()
				.filter(Objects::nonNull)
				.map(CategoryProjection::getCategory)
				.map(category -> {
					var parts = category.split(" - ", 2);
					return parts.length > 1 ? parts[1].trim() : "";
				})
				.distinct()
				.filter(StringUtils::isNotEmpty)
				.map(StringUtils::upperCase)
				.toList();
			case SCOPE -> courseRepository.findDistinctBy(ScopeProjection.class, Sort.by(SCOPE)).stream()
				.filter(Objects::nonNull)
				.map(ScopeProjection::getScope)
				.filter(StringUtils::isNotEmpty)
				.map(StringUtils::upperCase)
				.toList();
			case LEVEL -> courseRepository.findDistinctBy(LevelProjection.class, Sort.by(LEVEL)).stream()
				.filter(Objects::nonNull)
				.map(LevelProjection::getLevel)
				.filter(StringUtils::isNotEmpty)
				.map(StringUtils::upperCase)
				.toList();
			case STUDY_LOCATION -> courseRepository.findDistinctBy(StudyLocationProjection.class, Sort.by(STUDY_LOCATION)).stream()
				.filter(Objects::nonNull)
				.map(StudyLocationProjection::getStudyLocation)
				.filter(StringUtils::isNotEmpty)
				.map(StringUtils::upperCase)
				.toList();
			case START_DATE, END_DATE -> List.of("Any date in this format: yyyy-MM-dd");
		};
	}

}
