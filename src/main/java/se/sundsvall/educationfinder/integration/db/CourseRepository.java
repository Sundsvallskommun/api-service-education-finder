package se.sundsvall.educationfinder.integration.db;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import se.sundsvall.educationfinder.integration.db.model.CourseEntity;

@CircuitBreaker(name = "courseRepository")
public interface CourseRepository extends ReadOnlyRepository<CourseEntity, Long>, JpaSpecificationExecutor<CourseEntity> {
}
