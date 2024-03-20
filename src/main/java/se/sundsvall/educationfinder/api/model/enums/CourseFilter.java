package se.sundsvall.educationfinder.api.model.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import se.sundsvall.educationfinder.api.converter.CourseFilterToStringConverter;

@Schema(description = "Course filter model", enumAsRef = true)
public enum CourseFilter {

	CREDITS,
	PROVIDER,
	LEVEL,
	SCOPE,
	STUDY_LOCATION;

	@Override
	public String toString() {
		return new CourseFilterToStringConverter().convert(this);
	}
}
