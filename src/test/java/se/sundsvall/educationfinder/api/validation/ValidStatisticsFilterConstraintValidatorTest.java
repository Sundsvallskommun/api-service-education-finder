package se.sundsvall.educationfinder.api.validation;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidStatisticsFilterConstraintValidatorTest {

	@Mock
	private ConstraintValidatorContext constraintValidatorContextMock;

	@Mock
	private ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilderMock;

	@InjectMocks
	private ValidStatisticsFilterConstraintValidator validator;

	@ParameterizedTest
	@ValueSource(strings = {
		"level", "scope", "category", "subcategory", "studyLocation", "startDate", "endDate"
	})
	void validCourseFilter(final String statisticsFilter) {
		var valid = validator.isValid(statisticsFilter, constraintValidatorContextMock);
		assertThat(valid).isTrue();
	}

	@ParameterizedTest
	@ValueSource(strings = {
		"Invalid", "not-valid", "valid? naaah"
	})
	void invalidCourseFilter(final String statisticsFilter) {
		when(constraintValidatorContextMock.buildConstraintViolationWithTemplate(any())).thenReturn(constraintViolationBuilderMock);

		var valid = validator.isValid(statisticsFilter, constraintValidatorContextMock);
		assertThat(valid).isFalse();

		verify(constraintValidatorContextMock).disableDefaultConstraintViolation();
		verify(constraintValidatorContextMock).buildConstraintViolationWithTemplate("Given value %s is not valid, valid values are %s".formatted(statisticsFilter, ReflectionTestUtils.getField(validator, "VALID_VALUES")));
		verify(constraintViolationBuilderMock).addConstraintViolation();
	}

}
