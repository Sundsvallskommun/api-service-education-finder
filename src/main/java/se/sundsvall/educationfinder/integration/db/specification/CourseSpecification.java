package se.sundsvall.educationfinder.integration.db.specification;

import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.CODE;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.CREDITS;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.EARLIEST_APPLICATION;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.END;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.LATEST_APPLICATION;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.LEVEL;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.NAME;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.PROVIDER;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.RECOMMENDED_INFORMATION;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.SCOPE;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.START;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.STUDY_LOCATION;

import org.springframework.data.jpa.domain.Specification;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThan;
import net.kaczmarzyk.spring.data.jpa.domain.LessThan;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import se.sundsvall.educationfinder.integration.db.model.CourseEntity;

@And(value = {
	@Spec(params = "code", path = CODE, spec = EqualIgnoreCase.class),
	@Spec(params = "name", path = NAME, spec = LikeIgnoreCase.class),
	@Spec(params = "provider", path = PROVIDER, spec = LikeIgnoreCase.class),
	@Spec(params = "level", path = LEVEL, spec = LikeIgnoreCase.class),
	@Spec(params = "credits", path = CREDITS, spec = Equal.class),
	@Spec(params = "scope", path = SCOPE, spec = Equal.class),
	@Spec(params = "studyLocation", path = STUDY_LOCATION, spec = LikeIgnoreCase.class),
	@Spec(params = "start", path = START, spec = Equal.class),
	@Spec(params = "startBefore", path = START, spec = LessThan.class),
	@Spec(params = "startAfter", path = START, spec = GreaterThan.class),
	@Spec(params = "end", path = END, spec = Equal.class),
	@Spec(params = "endBefore", path = END, spec = LessThan.class),
	@Spec(params = "endAfter", path = END, spec = GreaterThan.class),
	@Spec(params = "earliestApplication", path = EARLIEST_APPLICATION, spec = Equal.class),
	@Spec(params = "earliestApplicationBefore", path = EARLIEST_APPLICATION, spec = LessThan.class),
	@Spec(params = "earliestApplicationAfter", path = EARLIEST_APPLICATION, spec = GreaterThan.class),
	@Spec(params = "latestApplication", path = LATEST_APPLICATION, spec = Equal.class),
	@Spec(params = "latestApplicationBefore", path = LATEST_APPLICATION, spec = LessThan.class),
	@Spec(params = "latestApplicationAfter", path = LATEST_APPLICATION, spec = GreaterThan.class),
	@Spec(params = "recommendedInformation", path = RECOMMENDED_INFORMATION, spec = LikeIgnoreCase.class)
})
public interface CourseSpecification extends Specification<CourseEntity> {
}
