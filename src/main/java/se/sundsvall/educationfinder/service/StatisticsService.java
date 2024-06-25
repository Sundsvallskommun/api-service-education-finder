package se.sundsvall.educationfinder.service;

import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.STUDY_LOCATION;
import static se.sundsvall.educationfinder.integration.db.model.SubjectEntity_.CATEGORY;
import static se.sundsvall.educationfinder.integration.db.model.SubjectEntity_.CATEGORY_ID;
import static se.sundsvall.educationfinder.integration.db.model.SubjectEntity_.EDUCATION_FORM;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import se.sundsvall.educationfinder.api.model.Statistics;
import se.sundsvall.educationfinder.api.model.StatisticsParameters;
import se.sundsvall.educationfinder.api.model.enums.StatisticsFilter;
import se.sundsvall.educationfinder.integration.db.CourseRepository;
import se.sundsvall.educationfinder.integration.db.SubjectRepository;
import se.sundsvall.educationfinder.integration.db.model.CourseEntity;
import se.sundsvall.educationfinder.integration.db.model.SubjectEntity;
import se.sundsvall.educationfinder.integration.db.model.projection.CategoryIdProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.CategoryProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.EducationFormProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.StudyLocationProjection;

@Service
public class StatisticsService {

	private final CourseRepository courseRepository;

	private final SubjectRepository subjectRepository;

	public StatisticsService(final CourseRepository courseRepository, final SubjectRepository subjectRepository) {
		this.courseRepository = courseRepository;
		this.subjectRepository = subjectRepository;
	}

	public Statistics getStatisticsByParameters(final StatisticsParameters parameters) {
		var subjects = subjectRepository.findAllByParameters(parameters);

		var codes = subjects.stream()
			.map(SubjectEntity::getSubjectCode)
			.distinct()
			.toList();

		var courses = courseRepository.findAllByParameters(parameters, codes);

		return calculateStatistics(parameters, courses);

	}

	public Statistics calculateStatistics(final StatisticsParameters parameters, final List<CourseEntity> courses) {
		var ongoingCourses = courses.stream()
			.filter(course -> course.getStart().isBefore(parameters.getStartDate()) && course.getEnd().isAfter(parameters.getEndDate()))
			.count();

		var plannedCourses = courses.stream()
			.filter(course -> course.getStart().isAfter(parameters.getStartDate()) && course.getStart().isBefore(parameters.getEndDate()))
			.count();

		var finishedCourses = courses.stream()
			.filter(course -> course.getEnd().isAfter(parameters.getStartDate()) && course.getEnd().isBefore(parameters.getEndDate()))
			.count();

		var availableSeats = courses.stream()
			.filter(course -> course.getStart().isAfter(parameters.getStartDate()) && course.getStart().isBefore(parameters.getEndDate()))
			.filter(course -> course.getNumberOfSeats() != null)
			.mapToInt(CourseEntity::getNumberOfSeats)
			.sum();

		var totalCapacity = courses.stream()
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
			.withEducationForms(parameters.getEducationForms())
			.withCategories(parameters.getCategories())
			.withCategoryIds(parameters.getCategoryIds());
	}

	@Cacheable("subject-filters")
	public List<String> findStatisticsFilterValues(final StatisticsFilter statisticsFilter) {
		return switch (statisticsFilter) {
			case EDUCATION_FORM ->
				subjectRepository.findDistinctBy(EducationFormProjection.class, Sort.by(EDUCATION_FORM)).stream()
					.map(EducationFormProjection::getEducationForm)
					.filter(StringUtils::isNotEmpty)
					.map(StringUtils::upperCase)
					.toList();
			case CATEGORY ->
				subjectRepository.findDistinctBy(CategoryProjection.class, Sort.by(CATEGORY)).stream()
					.map(CategoryProjection::getCategory)
					.filter(StringUtils::isNotEmpty)
					.map(StringUtils::upperCase)
					.toList();
			case CATEGORY_ID ->
				subjectRepository.findDistinctBy(CategoryIdProjection.class, Sort.by(CATEGORY_ID)).stream()
					.map(CategoryIdProjection::getCategoryId)
					.filter(StringUtils::isNotEmpty)
					.map(StringUtils::upperCase)
					.toList();
			case STUDY_LOCATION ->
				courseRepository.findDistinctBy(StudyLocationProjection.class, Sort.by(STUDY_LOCATION)).stream()
					.map(StudyLocationProjection::getStudyLocation)
					.filter(StringUtils::isNotEmpty)
					.map(StringUtils::upperCase)
					.toList();
			case START_DATE, END_DATE -> List.of("Any date in this format: yyyy-MM-dd");
		};
	}
}
