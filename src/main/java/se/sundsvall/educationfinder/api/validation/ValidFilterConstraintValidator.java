package se.sundsvall.educationfinder.api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ValidFilterConstraintValidator implements ConstraintValidator<ValidFilter, String> {

	private static final String ERROR_MESSAGE_TEMPLATE = "Given value %s is not valid, valid values are %s";
	private static final List<String> VALID_COURSE_VALUES = List.of("credits", "category", "subcategory", "provider", "level", "scope", "studyLocation");
	private static final List<String> VALID_STATISTICS_VALUES = List.of("level", "scope", "category", "subcategory", "studyLocation", "startDate", "endDate");
	private List<String> VALID_VALUES;

	@Override
	public void initialize(final ValidFilter constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
		switch (constraintAnnotation.type()) {
			case COURSE -> VALID_VALUES = VALID_COURSE_VALUES;
			case STATISTICS -> VALID_VALUES = VALID_STATISTICS_VALUES;
		}
	}

	@Override
	public boolean isValid(final String value, final ConstraintValidatorContext context) {
		if (value == null) {
			return false;
		}
		var valid = VALID_VALUES.contains(value);

		if (!valid) {
			useCustomMessageForValidation(context, ERROR_MESSAGE_TEMPLATE.formatted(value, VALID_VALUES));
		}

		return valid;
	}

	private void useCustomMessageForValidation(final ConstraintValidatorContext constraintContext, final String message) {
		constraintContext.disableDefaultConstraintViolation();
		constraintContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
	}
}
