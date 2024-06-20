package se.sundsvall.educationfinder.integration.db.specification;

import static se.sundsvall.educationfinder.integration.db.model.SubjectEntity_.CATEGORY;
import static se.sundsvall.educationfinder.integration.db.model.SubjectEntity_.CATEGORY_ID;
import static se.sundsvall.educationfinder.integration.db.model.SubjectEntity_.EDUCATION_FORM;

import org.springframework.data.jpa.domain.Specification;

import se.sundsvall.educationfinder.integration.db.model.SubjectEntity;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@And({
	@Spec(params = "educationForm", path = EDUCATION_FORM, spec = EqualIgnoreCase.class),
	@Spec(params = "category", path = CATEGORY, spec = EqualIgnoreCase.class),
	@Spec(params = "categoryId", path = CATEGORY_ID, spec = Equal.class)
})
public interface SubjectSpecification extends Specification<SubjectEntity> {

}
