package se.sundsvall.educationfinder.api.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import se.sundsvall.educationfinder.api.model.enums.CourseFilter;

@ExtendWith(MockitoExtension.class)
class CourseFilterToStringConverterTest {

	@InjectMocks
	private CourseFilterToStringConverter converter;

	@Test
	void convert() {

		// Arrange
		final var enumValueList = List.of(CourseFilter.values());

		// Act
		final var result = enumValueList.stream()
			.map(value -> converter.convert(value))
			.toList();

		// Assert
		assertThat(result).containsExactlyInAnyOrder("credits", "provider", "category", "subcategory", "level", "scope", "studyLocation");
	}
}
