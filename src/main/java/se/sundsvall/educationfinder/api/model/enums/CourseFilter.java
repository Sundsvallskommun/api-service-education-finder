package se.sundsvall.educationfinder.api.model.enums;

import se.sundsvall.educationfinder.api.converter.CourseFilterToStringConverter;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Course filter model", enumAsRef = true)
public enum CourseFilter {

	CREDITS,
	CATEGORY,
	SUBCATEGORY,
	PROVIDER,
	LEVEL,
	SCOPE,
	STUDY_LOCATION;

	@Override
	public String toString() {
		return new CourseFilterToStringConverter().convert(this);
	}
}
