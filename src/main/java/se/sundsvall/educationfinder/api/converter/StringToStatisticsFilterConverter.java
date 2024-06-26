package se.sundsvall.educationfinder.api.converter;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_UNDERSCORE;

import org.springframework.core.convert.converter.Converter;

import se.sundsvall.educationfinder.api.model.enums.StatisticsFilter;

public class StringToStatisticsFilterConverter implements Converter<String, StatisticsFilter> {

	@Override
	public StatisticsFilter convert(final String value) {
		return StatisticsFilter.valueOf(LOWER_CAMEL.to(UPPER_UNDERSCORE, value));
	}
}
