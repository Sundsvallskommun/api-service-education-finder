package se.sundsvall.educationfinder.service;

import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.STUDY_LOCATION;
import static se.sundsvall.educationfinder.integration.db.model.SubjectEntity_.CATEGORY;
import static se.sundsvall.educationfinder.integration.db.model.SubjectEntity_.CATEGORY_ID;
import static se.sundsvall.educationfinder.integration.db.model.SubjectEntity_.EDUCATION_FORM;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import se.sundsvall.educationfinder.api.model.enums.StatisticsFilter;
import se.sundsvall.educationfinder.integration.db.CourseRepository;
import se.sundsvall.educationfinder.integration.db.SubjectRepository;
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

	@Cacheable("subject-filters")
	public List<String> findStatisticsFilterValues(final StatisticsFilter statisticsFilter) {
		return switch (statisticsFilter) {
			case EDUCATION_FORM ->
				subjectRepository.findDistinctBy(EducationFormProjection.class, Sort.by(EDUCATION_FORM)).stream()
					.filter(Objects::nonNull)
					.map(EducationFormProjection::getEducationForm)
					.toList();
			case CATEGORY ->
				subjectRepository.findDistinctBy(CategoryProjection.class, Sort.by(CATEGORY)).stream()
					.filter(Objects::nonNull)
					.map(CategoryProjection::getCategory)
					.toList();
			case CATEGORY_ID ->
				subjectRepository.findDistinctBy(CategoryIdProjection.class, Sort.by(CATEGORY_ID)).stream()
					.filter(Objects::nonNull)
					.map(CategoryIdProjection::getCategoryId)
					.toList();
			case STUDY_LOCATION ->
				courseRepository.findDistinctBy(StudyLocationProjection.class, Sort.by(STUDY_LOCATION)).stream()
					.filter(Objects::nonNull)
					.map(StudyLocationProjection::getStudyLocation)
					.filter(StringUtils::isNotEmpty)
					.map(StringUtils::upperCase)
					.toList();
			case START_DATE, END_DATE -> List.of("Any date in this format: yyyy-MM-dd");
		};
	}
}
