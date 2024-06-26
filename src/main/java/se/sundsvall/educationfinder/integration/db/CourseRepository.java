package se.sundsvall.educationfinder.integration.db;

import static se.sundsvall.educationfinder.integration.db.specification.StatisticsSpecification.withCodes;
import static se.sundsvall.educationfinder.integration.db.specification.StatisticsSpecification.withStudyLocation;
import static se.sundsvall.educationfinder.integration.db.specification.StatisticsSpecification.withinPeriod;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import se.sundsvall.educationfinder.api.model.StatisticsParameters;
import se.sundsvall.educationfinder.integration.db.model.CourseEntity;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@CircuitBreaker(name = "courseRepository")
public interface CourseRepository extends ReadOnlyRepository<CourseEntity, Long>, JpaSpecificationExecutor<CourseEntity> {

	default List<CourseEntity> findAllByParameters(final StatisticsParameters parameters, final List<String> codes) {
		var startDate = parameters.getStartDate();
		var endDate = parameters.getEndDate();
		var studyLocation = parameters.getStudyLocations();

		return this.findAll(withStudyLocation(studyLocation)
			.and(withCodes(codes))
			.and(withinPeriod(startDate, endDate)));
	}
}
