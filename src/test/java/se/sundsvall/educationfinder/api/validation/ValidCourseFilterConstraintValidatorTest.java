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
class ValidCourseFilterConstraintValidatorTest {

	@Mock
	private ConstraintValidatorContext constraintValidatorContextMock;

	@Mock
	private ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilderMock;

	@InjectMocks
	private ValidCourseFilterConstraintValidator validator;

	@ParameterizedTest
	@ValueSource(strings = {
		"credits", "category", "subcategory", "provider", "level", "scope", "studyLocation"
	})
	void validCourseFilter(String courseFilter) {
		var valid = validator.isValid(courseFilter, constraintValidatorContextMock);
		assertThat(valid).isTrue();
	}

	@ParameterizedTest
	@ValueSource(strings = {
		"Invalid", "not-valid", "valid? naaah"
	})
	void invalidCourseFilter(String courseFilter) {
		when(constraintValidatorContextMock.buildConstraintViolationWithTemplate(any())).thenReturn(constraintViolationBuilderMock);

		var valid = validator.isValid(courseFilter, constraintValidatorContextMock);
		assertThat(valid).isFalse();

		verify(constraintValidatorContextMock).disableDefaultConstraintViolation();
		verify(constraintValidatorContextMock).buildConstraintViolationWithTemplate("Given value %s is not valid, valid values are %s".formatted(courseFilter, ReflectionTestUtils.getField(validator, "VALID_VALUES")));
		verify(constraintViolationBuilderMock).addConstraintViolation();
	}

}
