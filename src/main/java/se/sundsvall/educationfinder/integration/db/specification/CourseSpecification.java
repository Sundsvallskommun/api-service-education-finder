package se.sundsvall.educationfinder.integration.db.specification;

import org.springframework.data.jpa.domain.Specification;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import se.sundsvall.educationfinder.api.model.Course;

@And(value = {
	@Spec(params = "code", path = "code", spec = EqualIgnoreCase.class),
	@Spec(params = "name", path = "name", spec = LikeIgnoreCase.class),
	@Spec(params = "educationProvider", path = "educationProvider", spec = LikeIgnoreCase.class),
	@Spec(params = "providerUrl", path = "providerUrl", spec = EqualIgnoreCase.class),
	@Spec(params = "level", path = "level", spec = EqualIgnoreCase.class),
	@Spec(params = "url", path = "url", spec = EqualIgnoreCase.class),
	@Spec(params = "credits", path = "credits", spec = EqualIgnoreCase.class),
	@Spec(params = "scope", path = "scope", spec = EqualIgnoreCase.class),
	@Spec(params = "subjectCode", path = "subjectCode", spec = EqualIgnoreCase.class),
	@Spec(params = "numberOfSeats", path = "numberOfSeats", spec = Equal.class),
	@Spec(params = "endDate", path = "endDate", spec = Equal.class),
	@Spec(params = "firstApplicationDate", path = "firstApplicationDate", spec = Equal.class),
	@Spec(params = "lastApplicationDate", path = "lastApplicationDate", spec = Equal.class)
})
public interface CourseSpecification extends Specification<Course> { // TODO: <---- Add real entity here
}
