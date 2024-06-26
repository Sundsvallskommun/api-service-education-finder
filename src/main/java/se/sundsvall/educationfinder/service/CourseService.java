package se.sundsvall.educationfinder.service;

import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.CREDITS;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.LEVEL;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.PROVIDER;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.SCOPE;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.STUDY_LOCATION;
import static se.sundsvall.educationfinder.service.mapper.CourseMapper.toPagedCoursesResponse;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import se.sundsvall.educationfinder.api.model.PagedCoursesResponse;
import se.sundsvall.educationfinder.api.model.enums.CourseFilter;
import se.sundsvall.educationfinder.integration.db.CourseRepository;
import se.sundsvall.educationfinder.integration.db.model.projection.CreditsProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.LevelProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.ProviderProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.ScopeProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.StudyLocationProjection;
import se.sundsvall.educationfinder.integration.db.specification.CourseSpecification;

@Service
public class CourseService {

	private final CourseRepository courseRepository;

	CourseService(final CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public PagedCoursesResponse find(CourseSpecification specification, Pageable pageable) {
		return toPagedCoursesResponse(courseRepository.findAll(specification, pageable));
	}

	@Cacheable("course-filters")
	public List<String> findFilterValues(CourseFilter courseFilter) {
		return switch (courseFilter) {
			case STUDY_LOCATION ->
				courseRepository.findDistinctBy(StudyLocationProjection.class, Sort.by(STUDY_LOCATION)).stream()
					.filter(Objects::nonNull)
					.map(StudyLocationProjection::getStudyLocation)
					.filter(StringUtils::isNotEmpty)
					.map(StringUtils::upperCase)
					.toList();
			case PROVIDER ->
				courseRepository.findDistinctBy(ProviderProjection.class, Sort.by(PROVIDER)).stream()
					.filter(Objects::nonNull)
					.map(ProviderProjection::getProvider)
					.toList();
			case LEVEL ->
				courseRepository.findDistinctBy(LevelProjection.class, Sort.by(LEVEL)).stream()
					.filter(Objects::nonNull)
					.map(LevelProjection::getLevel)
					.toList();
			case SCOPE ->
				courseRepository.findDistinctBy(ScopeProjection.class, Sort.by(SCOPE)).stream()
					.filter(Objects::nonNull)
					.map(ScopeProjection::getScope)
					.toList();
			case CREDITS ->
				courseRepository.findDistinctBy(CreditsProjection.class, Sort.by(CREDITS)).stream()
					.filter(Objects::nonNull)
					.map(CreditsProjection::getCredits)
					.toList();
		};
	}
}
