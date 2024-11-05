package se.sundsvall.educationfinder.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import se.sundsvall.educationfinder.api.model.Course;
import se.sundsvall.educationfinder.api.model.CourseParameters;
import se.sundsvall.educationfinder.api.model.PagedCoursesResponse;
import se.sundsvall.educationfinder.api.model.enums.CourseFilter;
import se.sundsvall.educationfinder.integration.db.CourseRepository;
import se.sundsvall.educationfinder.integration.db.model.projection.CategoryProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.CreditsProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.LevelProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.ProviderProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.ScopeProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.StudyLocationProjection;

import java.util.List;
import java.util.Objects;

import static org.zalando.problem.Status.NOT_FOUND;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.CATEGORY;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.CREDITS;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.LEVEL;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.PROVIDER;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.SCOPE;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.STUDY_LOCATION;
import static se.sundsvall.educationfinder.service.mapper.CourseMapper.toCourse;
import static se.sundsvall.educationfinder.service.mapper.CourseMapper.toPagedCoursesResponse;

@Service
public class CourseService {

	private final CourseRepository courseRepository;

	CourseService(final CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public PagedCoursesResponse find(final CourseParameters parameters) {
		return toPagedCoursesResponse(courseRepository.findAllByCourseParameters(parameters, PageRequest.of(parameters.getPage() - 1, parameters.getLimit(), parameters.sort())));
	}

	public Course findCourseById(final Long id) {
		var entity = courseRepository.findById(id)
			.orElseThrow(() -> Problem.valueOf(NOT_FOUND, "Course with id: %s not found".formatted(id)));
		return toCourse(entity);
	}

	@Cacheable("course-filters")
	public List<String> findFilterValues(final CourseFilter courseFilter) {
		return switch (courseFilter) {
			case CATEGORY -> courseRepository.findDistinctBy(CategoryProjection.class, Sort.by(CATEGORY)).stream()
				.filter(Objects::nonNull)
				.map(CategoryProjection::getCategory)
				.map(category -> {
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
			case STUDY_LOCATION -> courseRepository.findDistinctBy(StudyLocationProjection.class, Sort.by(STUDY_LOCATION)).stream()
				.filter(Objects::nonNull)
				.map(StudyLocationProjection::getStudyLocation)
				.filter(StringUtils::isNotEmpty)
				.map(StringUtils::upperCase)
				.toList();
			case PROVIDER -> courseRepository.findDistinctBy(ProviderProjection.class, Sort.by(PROVIDER)).stream()
				.filter(Objects::nonNull)
				.map(ProviderProjection::getProvider)
				.toList();
			case LEVEL -> courseRepository.findDistinctBy(LevelProjection.class, Sort.by(LEVEL)).stream()
				.filter(Objects::nonNull)
				.map(LevelProjection::getLevel)
				.toList();
			case SCOPE -> courseRepository.findDistinctBy(ScopeProjection.class, Sort.by(SCOPE)).stream()
				.filter(Objects::nonNull)
				.map(ScopeProjection::getScope)
				.toList();
			case CREDITS -> courseRepository.findDistinctBy(CreditsProjection.class, Sort.by(CREDITS)).stream()
				.filter(Objects::nonNull)
				.map(CreditsProjection::getCredits)
				.toList();
		};
	}
}
