package se.sundsvall.educationfinder.api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({
	ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE
})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidStatisticsFilterConstraintValidator.class)
public @interface ValidStatisticsFilter {
	String message() default "";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
