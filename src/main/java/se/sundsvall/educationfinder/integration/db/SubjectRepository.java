package se.sundsvall.educationfinder.integration.db;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import se.sundsvall.educationfinder.integration.db.model.SubjectEntity;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@CircuitBreaker(name = "subjectRepository")
public interface SubjectRepository extends ReadOnlyRepository<SubjectEntity, Long>, JpaSpecificationExecutor<SubjectEntity> {

}
