package se.sundsvall.educationfinder.api.converter;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_UNDERSCORE;

import org.springframework.core.convert.converter.Converter;

import se.sundsvall.educationfinder.api.model.enums.CourseFilter;

public class CourseFilterToStringConverter implements Converter<CourseFilter, String> {

	@Override
	public String convert(CourseFilter value) {
		return UPPER_UNDERSCORE.to(LOWER_CAMEL, value.name());
	}
}
