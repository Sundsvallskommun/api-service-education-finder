package se.sundsvall.educationfinder.integration.db;

import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withCategory;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withCode;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withCredits;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withEarliestApplication;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withEarliestApplicationAfter;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withEarliestApplicationBefore;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withEnd;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withEndAfter;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withEndBefore;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withFreeTextSearch;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withLanguageOfInstruction;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withLatestApplication;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withLatestApplicationAfter;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withLatestApplicationBefore;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withLevelIn;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withLikeInformation;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withName;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withProvider;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withScopeIn;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withStart;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withStartAfter;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withStartBefore;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withStudyLocationIn;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withSubCategory;
import static se.sundsvall.educationfinder.integration.db.specification.StatisticsSpecification.withCategories;
import static se.sundsvall.educationfinder.integration.db.specification.StatisticsSpecification.withLevels;
import static se.sundsvall.educationfinder.integration.db.specification.StatisticsSpecification.withScopes;
import static se.sundsvall.educationfinder.integration.db.specification.StatisticsSpecification.withStudyLocations;
import static se.sundsvall.educationfinder.integration.db.specification.StatisticsSpecification.withSubCategories;
import static se.sundsvall.educationfinder.integration.db.specification.StatisticsSpecification.withinPeriod;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import se.sundsvall.educationfinder.api.model.CourseParameters;
import se.sundsvall.educationfinder.api.model.StatisticsParameters;
import se.sundsvall.educationfinder.integration.db.model.CourseEntity;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Transactional(readOnly = true)
@CircuitBreaker(name = "courseRepository")
public interface CourseRepository extends PagingAndSortingRepository<CourseEntity, Long>, JpaSpecificationExecutor<CourseEntity> {

	Optional<CourseEntity> findById(Long id);

	<P> List<P> findDistinctBy(Class<P> type, Sort sort);

	default Page<CourseEntity> findAllByCourseParameters(final CourseParameters parameters, final Pageable pageable) {
		var spec = Specification
			.where(withCode(parameters.getCode()))
			.and(withCategory(parameters.getCategory()))
			.and(withSubCategory(parameters.getSubcategory()))
			.and(withProvider(parameters.getProvider()))
			.and(withName(parameters.getName()))
			.and(withCredits(parameters.getCredits()))
			.and(withLanguageOfInstruction(parameters.getLanguageOfInstruction()))
			.and(withLikeInformation(parameters.getInformation()))
			.and(withStart(parameters.getStart()))
			.and(withStartAfter(parameters.getStart()))
			.and(withStartBefore(parameters.getStart()))
			.and(withEnd(parameters.getEnd()))
			.and(withEndAfter(parameters.getEnd()))
			.and(withEndBefore(parameters.getEnd()))
			.and(withEarliestApplication(parameters.getEarliestApplication()))
			.and(withEarliestApplicationAfter(parameters.getEarliestApplication()))
			.and(withEarliestApplicationBefore(parameters.getEarliestApplication()))
			.and(withLatestApplication(parameters.getLatestApplication()))
			.and(withLatestApplicationAfter(parameters.getLatestApplication()))
			.and(withLatestApplicationBefore(parameters.getLatestApplication()))
			.and(withScopeIn(parameters.getScopes()))
			.and(withStudyLocationIn(parameters.getStudyLocations()))
			.and(withLevelIn(parameters.getLevels()))
			.and(withFreeTextSearch(parameters.getSearchString()));

		return findAll((root, query, cb) -> {
			query.distinct(true);
			return spec.toPredicate(root, query, cb);
		}, pageable);
	}

	default List<CourseEntity> findAllByParameters(final StatisticsParameters parameters) {
		var spec = Specification
			.where(withStudyLocations(parameters.getStudyLocations()))
			.and(withLevels(parameters.getLevels()))
			.and(withScopes(parameters.getScopes()))
			.and(withCategories(parameters.getCategories()))
			.and(withSubCategories(parameters.getSubCategories()))
			.and(withinPeriod(parameters.getStartDate(), parameters.getEndDate()));

		return findAll((root, query, cb) -> {
			query.distinct(true);
			return spec.toPredicate(root, query, cb);
		});
	}
}
