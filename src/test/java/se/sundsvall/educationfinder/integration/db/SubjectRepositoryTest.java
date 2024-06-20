package se.sundsvall.educationfinder.integration.db;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static se.sundsvall.educationfinder.integration.db.model.SubjectEntity_.CATEGORY;
import static se.sundsvall.educationfinder.integration.db.model.SubjectEntity_.CATEGORY_ID;
import static se.sundsvall.educationfinder.integration.db.model.SubjectEntity_.EDUCATION_FORM;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import se.sundsvall.educationfinder.integration.db.model.SubjectEntity;
import se.sundsvall.educationfinder.integration.db.model.projection.CategoryIdProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.CategoryProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.EducationFormProjection;
import se.sundsvall.educationfinder.integration.db.specification.SubjectSpecification;

/**
 * SubjectRepository tests.
 *
 * @see /src/test/resources/db/scripts/testdata.sql for data setup.
 */
@DataJpaTest
@Transactional
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
			.containsExactly("FHSK", "UOH", "YH");
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
	@MethodSource("specificationsProvider")
	void findBySpecification(final Map<String, Object> filter, final Integer matches) {
		SubjectSpecification subjectSpecification = (root1, query, criteriaBuilder) -> criteriaBuilder.conjunction();

		var specification = subjectSpecification.and((root, query, criteriaBuilder) -> buildPredicate(filter, root, criteriaBuilder));

		var test = subjectRepository.findAll(specification);

		assertThat(test).isNotNull();
		assertThat(test.size()).isEqualTo(matches);

	}

	private Predicate buildPredicate(Map<String, Object> filter, Root<SubjectEntity> root, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates = filter.entrySet().stream()
			.map(entry -> {
				String key = entry.getKey();
				Object value = entry.getValue();
				return criteriaBuilder.equal(root.get(key), value);
			})
			.toList();

		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}

	private static Stream<Arguments> specificationsProvider() {
		return Stream.of(
			// Search by category and education form expects 15 matches
			Arguments.of(Map.of(CATEGORY, "Konstnärliga utbildningar", EDUCATION_FORM, "YH"), 15),
			// Search by categoryId and education form expects 15 matches
			Arguments.of(Map.of(CATEGORY_ID, 8, EDUCATION_FORM, "YH"), 15),
			// Search by category and education form expects 25 matches
			Arguments.of(Map.of(CATEGORY, "Konstnärliga utbildningar", EDUCATION_FORM, "UOH"), 25),
			// Search with empty filter expects 1000 matches
			Arguments.of(Map.of(), 1000),
			// Search by category expects 75 matches
			Arguments.of(Map.of(CATEGORY, "Medicin och vård"), 75),
			// Search by categoryId expects 63 matches
			Arguments.of(Map.of(CATEGORY_ID, 3), 63),
			// Search by education form expects 283 matches
			Arguments.of(Map.of(EDUCATION_FORM, "YH"), 283));
	}

}
