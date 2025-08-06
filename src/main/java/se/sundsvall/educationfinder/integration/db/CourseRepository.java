package se.sundsvall.educationfinder.integration.db;

import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.distinct;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withCategories;
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
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withLevels;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withLikeInformation;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withName;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withProvider;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withScopes;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withStart;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withStartAfter;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withStartBefore;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withStudyLocationMunicipalityId;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withStudyLocations;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withSubCategories;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withVisitingAddressMunicipalityId;
import static se.sundsvall.educationfinder.integration.db.specification.CourseSpecification.withinPeriod;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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

@Transactional(readOnly = true)
@CircuitBreaker(name = "courseRepository")
public interface CourseRepository extends PagingAndSortingRepository<CourseEntity, Long>, JpaSpecificationExecutor<CourseEntity> {

	Optional<CourseEntity> findById(Long id);

	<P> List<P> findDistinctBy(Class<P> type, Sort sort);

	default Page<CourseEntity> findAllByCourseParameters(final CourseParameters parameters, final Pageable pageable) {
		return findAll(Specification.allOf(
			withCode(parameters.getCode()))
			.and(withProvider(parameters.getProvider()))
			.and(withName(parameters.getName()))
			.and(withCredits(parameters.getCredits()))
			.and(withLanguageOfInstruction(parameters.getLanguageOfInstruction()))
			.and(withStart(parameters.getStart()))
			.and(withStartAfter(parameters.getStartAfter()))
			.and(withStartBefore(parameters.getStartBefore()))
			.and(withEnd(parameters.getEnd()))
			.and(withEndAfter(parameters.getEndAfter()))
			.and(withEndBefore(parameters.getEndBefore()))
			.and(withEarliestApplication(parameters.getEarliestApplication()))
			.and(withEarliestApplicationAfter(parameters.getEarliestApplicationAfter()))
			.and(withEarliestApplicationBefore(parameters.getEarliestApplicationBefore()))
			.and(withLatestApplication(parameters.getLatestApplication()))
			.and(withLatestApplicationAfter(parameters.getLatestApplicationAfter()))
			.and(withLatestApplicationBefore(parameters.getLatestApplicationBefore()))
			.and(withCategories(parameters.getCategories()))
			.and(withSubCategories(parameters.getSubcategories()))
			.and(withScopes(parameters.getScopes()))
			.and(withStudyLocations(parameters.getStudyLocations()))
			.and(withLevels(parameters.getLevels()))
			.and(withLikeInformation(parameters.getInformation()))
			.and(withFreeTextSearch(parameters.getSearchString()))
			.and(withStudyLocationMunicipalityId(parameters.getPlaceOfStudyMunicipalityId()))
			.and(withVisitingAddressMunicipalityId(parameters.getVisitingAddressMunicipalityId()))
			.and(distinct()),
			pageable);
	}

	default List<CourseEntity> findAllByParameters(final StatisticsParameters parameters) {
		return findAll(Specification.allOf(
			withStudyLocations(parameters.getStudyLocations()))
			.and(withLevels(parameters.getLevels()))
			.and(withScopes(parameters.getScopes()))
			.and(withCategories(parameters.getCategories()))
			.and(withSubCategories(parameters.getSubCategories()))
			.and(withinPeriod(parameters.getStartDate(), parameters.getEndDate()))
			.and(distinct()));
	}
}
