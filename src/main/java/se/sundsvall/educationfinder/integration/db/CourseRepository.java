package se.sundsvall.educationfinder.integration.db;

import static se.sundsvall.educationfinder.integration.db.specification.StatisticsSpecification.withCodes;
import static se.sundsvall.educationfinder.integration.db.specification.StatisticsSpecification.withLevels;
import static se.sundsvall.educationfinder.integration.db.specification.StatisticsSpecification.withScopes;
import static se.sundsvall.educationfinder.integration.db.specification.StatisticsSpecification.withStudyLocations;
import static se.sundsvall.educationfinder.integration.db.specification.StatisticsSpecification.withinPeriod;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import se.sundsvall.educationfinder.api.model.StatisticsParameters;
import se.sundsvall.educationfinder.integration.db.model.CourseEntity;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@CircuitBreaker(name = "courseRepository")
public interface CourseRepository extends ReadOnlyRepository<CourseEntity, Long>, JpaSpecificationExecutor<CourseEntity> {

	default List<CourseEntity> findAllByParametersAndCode(final StatisticsParameters parameters, final List<String> codes) {
		var startDate = parameters.getStartDate();
		var endDate = parameters.getEndDate();
		var studyLocations = parameters.getStudyLocations();
		var levels = parameters.getLevels();
		var scopes = parameters.getScopes();

		return this.findAll(withStudyLocations(studyLocations)
			.and(withLevels(levels))
			.and(withScopes(scopes))
			.and(withCodes(codes))
			.and(withinPeriod(startDate, endDate)));
	}
}
