package se.sundsvall.educationfinder.api.model.enums;

import se.sundsvall.educationfinder.api.converter.StatisticsFilterToStringConverter;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Statistics filter model", enumAsRef = true)
public enum StatisticsFilter {

	LEVEL,
	SCOPE,
	CATEGORY,
	SUBCATEGORY,
	STUDY_LOCATION,
	START_DATE,
	END_DATE;

	@Override
	public String toString() {
		return new StatisticsFilterToStringConverter().convert(this);
	}
}
