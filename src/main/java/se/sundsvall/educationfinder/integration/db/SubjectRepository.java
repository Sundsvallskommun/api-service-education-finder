package se.sundsvall.educationfinder.integration.db;

import static se.sundsvall.educationfinder.integration.db.specification.SubjectSpecification.withCategory;
import static se.sundsvall.educationfinder.integration.db.specification.SubjectSpecification.withCategoryId;
import static se.sundsvall.educationfinder.integration.db.specification.SubjectSpecification.withEducationForm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import se.sundsvall.educationfinder.api.model.StatisticsParameters;
import se.sundsvall.educationfinder.integration.db.model.SubjectEntity;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@CircuitBreaker(name = "subjectRepository")
public interface SubjectRepository extends ReadOnlyRepository<SubjectEntity, Long>, JpaSpecificationExecutor<SubjectEntity> {

	default List<SubjectEntity> findAllByParameters(final StatisticsParameters parameters) {
		var educationForms = parameters.getEducationForms();
		var categories = parameters.getCategories();
		var categoryIds = parameters.getCategoryIds();

		return this.findAll(withEducationForm(educationForms)
			.and(withCategory(categories))
			.and(withCategoryId(categoryIds)));
	}

}
