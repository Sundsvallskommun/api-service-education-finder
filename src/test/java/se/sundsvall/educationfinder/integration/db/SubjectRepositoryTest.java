package se.sundsvall.educationfinder.integration.db;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static se.sundsvall.educationfinder.integration.db.model.SubjectEntity_.CATEGORY;
import static se.sundsvall.educationfinder.integration.db.model.SubjectEntity_.CATEGORY_ID;
import static se.sundsvall.educationfinder.integration.db.model.SubjectEntity_.EDUCATION_FORM;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import se.sundsvall.educationfinder.api.model.StatisticsParameters;
import se.sundsvall.educationfinder.integration.db.model.projection.CategoryIdProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.CategoryProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.EducationFormProjection;

/**
 * SubjectRepository tests.
 *
 * @see /src/test/resources/db/scripts/testdata.sql for data setup.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@ActiveProfiles("junit")
class SubjectRepositoryTest {

	@Autowired
	private SubjectRepository subjectRepository;

	@Test
	void findDistinctByEducationForm() {
		// Act
		final var result = subjectRepository.findDistinctBy(EducationFormProjection.class, Sort.by(EDUCATION_FORM));

		// Assert
		assertThat(result)
			.extracting(EducationFormProjection::getEducationForm)
			.containsExactly("AUB", "FHSK", "SV", "UOH", "YH");
	}

	@Test
	void findDistinctByCategory() {
		// Act
		final var result = subjectRepository.findDistinctBy(CategoryProjection.class, Sort.by(CATEGORY));

		// Assert
		assertThat(result)
			.extracting(CategoryProjection::getCategory)
			.containsExactly("Bygg och anläggning",
				"Data och IT",
				"Ekonomi, marknadsföring och administration",
				"Frisk- och skönhetsvård",
				"Förberedande utbildningar",
				"Hantverk",
				"Hotell, restaurang och turism",
				"Information och media",
				"Konstnärliga utbildningar",
				"Kultur och humanistiska ämnen",
				"Medicin och vård",
				"Naturbruk",
				"Naturvetenskap",
				"Samhällsvetenskap och juridik",
				"Språk",
				"Säkerhet, försvar och räddningstjänst",
				"Teknik",
				"Tillverkning och underhåll",
				"Transport",
				"Undervisning och idrott",
				"Övriga kurser och tvärvetenskap");
	}

	@Test
	void findDistinctByCategoryId() {
		// Act
		final var result = subjectRepository.findDistinctBy(CategoryIdProjection.class, Sort.by(CATEGORY_ID));

		// Assert
		assertThat(result)
			.extracting(CategoryIdProjection::getCategoryId)
			.containsExactly("1",
				"2",
				"3",
				"4",
				"5",
				"6",
				"7",
				"8",
				"9",
				"10",
				"11",
				"12",
				"13",
				"14",
				"15",
				"16",
				"17",
				"18",
				"19",
				"20",
				"21");
	}

	@ParameterizedTest
	@MethodSource("findAllByParametersArguments")
	void findAllByParameters(final StatisticsParameters parameters, final Integer matches) {
		var result = subjectRepository.findAllByParameters(parameters);

		assertThat(result).hasSize(matches);
	}

	private static Stream<Arguments> findAllByParametersArguments() {
		return Stream.of(
			Arguments.of(StatisticsParameters.create(), 3154),
			Arguments.of(StatisticsParameters.create().withCategories(List.of("Medicin och vård")), 193),
			Arguments.of(StatisticsParameters.create().withCategoryIds(List.of("3")), 218));
	}
}
