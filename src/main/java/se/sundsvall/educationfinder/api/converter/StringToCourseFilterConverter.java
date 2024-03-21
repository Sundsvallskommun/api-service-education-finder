package se.sundsvall.educationfinder.api.converter;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_UNDERSCORE;

import org.springframework.core.convert.converter.Converter;

import se.sundsvall.educationfinder.api.model.enums.CourseFilter;

public class StringToCourseFilterConverter implements Converter<String, CourseFilter> {

	@Override
	public CourseFilter convert(String value) {
		return CourseFilter.valueOf(LOWER_CAMEL.to(UPPER_UNDERSCORE, value));
	}
}
