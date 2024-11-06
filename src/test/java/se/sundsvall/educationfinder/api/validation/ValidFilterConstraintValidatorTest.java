package se.sundsvall.educationfinder.api.validation;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidFilterConstraintValidatorTest {

	@Mock
	private ConstraintValidatorContext constraintValidatorContextMock;

	@Mock
	private ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilderMock;

	@InjectMocks
	private ValidFilterConstraintValidator validator;

	@ParameterizedTest
	@ValueSource(strings = {
		"credits", "category", "subcategory", "provider", "level", "scope", "studyLocation"
	})
	void validCourseFilter(final String courseFilter) {
		ReflectionTestUtils.setField(validator, "VALID_VALUES", List.of("credits", "category", "subcategory", "provider", "level", "scope", "studyLocation"));
		var valid = validator.isValid(courseFilter, constraintValidatorContextMock);
		assertThat(valid).isTrue();
	}

	@ParameterizedTest
	@ValueSource(strings = {
		"Invalid", "not-valid", "valid? naaah"
	})
	void invalidCourseFilter(final String courseFilter) {
		ReflectionTestUtils.setField(validator, "VALID_VALUES", List.of("credits", "category", "subcategory", "provider", "level", "scope", "studyLocation"));
		when(constraintValidatorContextMock.buildConstraintViolationWithTemplate(any())).thenReturn(constraintViolationBuilderMock);

		var valid = validator.isValid(courseFilter, constraintValidatorContextMock);
		assertThat(valid).isFalse();

		verify(constraintValidatorContextMock).disableDefaultConstraintViolation();
		verify(constraintValidatorContextMock).buildConstraintViolationWithTemplate("Given value %s is not valid, valid values are %s".formatted(courseFilter, ReflectionTestUtils.getField(validator, "VALID_VALUES")));
		verify(constraintViolationBuilderMock).addConstraintViolation();
	}

	@ParameterizedTest
	@ValueSource(strings = {
		"level", "scope", "category", "subcategory", "studyLocation", "startDate", "endDate"
	})
	void validStatisticsFilter(final String statisticsFilter) {
		ReflectionTestUtils.setField(validator, "VALID_VALUES", List.of("level", "scope", "category", "subcategory", "studyLocation", "startDate", "endDate"));
		var valid = validator.isValid(statisticsFilter, constraintValidatorContextMock);
		assertThat(valid).isTrue();
	}

	@ParameterizedTest
	@ValueSource(strings = {
		"Invalid", "not-valid", "valid? naaah"
	})
	void invalidStatisticsFilter(final String statisticsFilter) {
		ReflectionTestUtils.setField(validator, "VALID_VALUES", List.of("level", "scope", "category", "subcategory", "studyLocation", "startDate", "endDate"));
		when(constraintValidatorContextMock.buildConstraintViolationWithTemplate(any())).thenReturn(constraintViolationBuilderMock);

		var valid = validator.isValid(statisticsFilter, constraintValidatorContextMock);
		assertThat(valid).isFalse();

		verify(constraintValidatorContextMock).disableDefaultConstraintViolation();
		verify(constraintValidatorContextMock).buildConstraintViolationWithTemplate("Given value %s is not valid, valid values are %s".formatted(statisticsFilter, ReflectionTestUtils.getField(validator, "VALID_VALUES")));
		verify(constraintViolationBuilderMock).addConstraintViolation();
	}

}
