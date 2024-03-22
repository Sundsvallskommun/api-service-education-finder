package se.sundsvall.educationfinder.integration.db.specification;

import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.CODE;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.CREDITS;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.EARLIEST_APPLICATION;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.END;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.LATEST_APPLICATION;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.LEVEL;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.NAME;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.NUMBER_OF_SEATS;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.PROVIDER;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.PROVIDER_URL;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.RECOMMENDED_INFORMATION;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.SCOPE;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.START;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.STUDY_LOCATION;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.SUBJECT_CODE;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.URL;

import org.springframework.data.jpa.domain.Specification;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import se.sundsvall.educationfinder.integration.db.model.CourseEntity;

@And(value = {
	@Spec(params = "code", path = CODE, spec = EqualIgnoreCase.class),
	@Spec(params = "name", path = NAME, spec = LikeIgnoreCase.class),
	@Spec(params = "url", path = URL, spec = LikeIgnoreCase.class),
	@Spec(params = "subjectCode", path = SUBJECT_CODE, spec = EqualIgnoreCase.class),
	@Spec(params = "provider", path = PROVIDER, spec = LikeIgnoreCase.class),
	@Spec(params = "providerUrl", path = PROVIDER_URL, spec = LikeIgnoreCase.class),
	@Spec(params = "level", path = LEVEL, spec = LikeIgnoreCase.class),
	@Spec(params = "credits", path = CREDITS, spec = Equal.class),
	@Spec(params = "scope", path = SCOPE, spec = Equal.class),
	@Spec(params = "studyLocation", path = STUDY_LOCATION, spec = LikeIgnoreCase.class),
	@Spec(params = "numberOfSeats", path = NUMBER_OF_SEATS, spec = Equal.class),
	@Spec(params = "start", path = START, spec = GreaterThanOrEqual.class),
	@Spec(params = "end", path = END, spec = LessThanOrEqual.class),
	@Spec(params = "earliestApplication", path = EARLIEST_APPLICATION, spec = GreaterThanOrEqual.class),
	@Spec(params = "latestApplication", path = LATEST_APPLICATION, spec = LessThanOrEqual.class),
	@Spec(params = "recommendedInformation", path = RECOMMENDED_INFORMATION, spec = LikeIgnoreCase.class)
})
public interface CourseSpecification extends Specification<CourseEntity> {
}
