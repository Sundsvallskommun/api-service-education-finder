package se.sundsvall.educationfinder.api.model.enums;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_UNDERSCORE;

import org.springframework.core.convert.converter.Converter;

public enum SubjectFilter {

	EDUCATION_FORM,
	CATEGORY,
	CATEGORY_ID,
	STUDY_LOCATION,
	START_DATE,
	END_DATE;

	@Override
	public String toString() {
		return new SubjectFilterConverter().convert(this);
	}

	public static class SubjectFilterConverter implements Converter<SubjectFilter, String> {
		@Override
		public String convert(final SubjectFilter source) {
			return UPPER_UNDERSCORE.to(LOWER_CAMEL, source.name());
		}
	}
}
