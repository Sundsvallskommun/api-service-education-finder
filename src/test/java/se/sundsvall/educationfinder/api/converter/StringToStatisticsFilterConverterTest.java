package se.sundsvall.educationfinder.api.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import se.sundsvall.educationfinder.api.model.enums.StatisticsFilter;

@ExtendWith(MockitoExtension.class)
class StringToStatisticsFilterConverterTest {

	@InjectMocks
	private StringToStatisticsFilterConverter converter;

	@Test
	void convert() {

		// Arrange
		final var stringValueList = List.of("educationForm", "category", "categoryId", "studyLocation", "startDate", "endDate");

		// Act
		final var result = stringValueList.stream()
			.map(value -> converter.convert(value))
			.toList();

		// Assert
		assertThat(result).containsExactlyInAnyOrder(StatisticsFilter.values());
	}
}
