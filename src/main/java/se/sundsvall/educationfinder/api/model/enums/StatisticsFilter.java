package se.sundsvall.educationfinder.api.model.enums;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_UNDERSCORE;

import org.springframework.core.convert.converter.Converter;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Statistics filter model", enumAsRef = true)
public enum StatisticsFilter {

	EDUCATION_FORM,
	CATEGORY,
	CATEGORY_ID,
	STUDY_LOCATION,
	START_DATE,
	END_DATE;

	@Override
	public String toString() {
		return new StatisticsFilter.SubjectFilterConverter().convert(this);
	}

	public static class SubjectFilterConverter implements Converter<StatisticsFilter, String> {
		@Override
		public String convert(final StatisticsFilter source) {
			return UPPER_UNDERSCORE.to(LOWER_CAMEL, source.name());
		}
	}
}
