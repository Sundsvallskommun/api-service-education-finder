package se.sundsvall.educationfinder.integration.db;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.CREDITS;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.LEVEL;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.PROVIDER;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.SCOPE;
import static se.sundsvall.educationfinder.integration.db.model.CourseEntity_.STUDY_LOCATION;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import se.sundsvall.educationfinder.integration.db.model.CourseEntity;
import se.sundsvall.educationfinder.integration.db.model.projection.CreditsProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.LevelProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.ProviderProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.ScopeProjection;
import se.sundsvall.educationfinder.integration.db.model.projection.StudyLocationProjection;

/**
 * DocumentRepository tests.
 *
 * @see /src/test/resources/db/testdata-junit.sql for data setup.
 */
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace = NONE)
@ActiveProfiles("junit")
class CourseRepositoryTest {

	@Autowired
	private CourseRepository courseRepository;

	@Test
	void findDistinctByStudyLocation() {

		// Act
		final var result = courseRepository.findDistinctBy(StudyLocationProjection.class, Sort.by(STUDY_LOCATION));

		// Assert
		assertThat(result)
			.extracting(StudyLocationProjection::getStudyLocation)
			.containsExactly("Härnösand", "Kramfors", "Sundsvall", "Örnsköldsvik", "Östersund");
	}

	@Test
	void findDistinctByProvider() {

		// Act
		final var result = courseRepository.findDistinctBy(ProviderProjection.class, Sort.by(PROVIDER));

		// Assert
		assertThat(result)
			.extracting(ProviderProjection::getProvider)
			.containsExactly(
				"Härnösands kommun, Yrkeshögskolan",
				"Kramfors kommun, Yrkeshögskolan Höga kusten",
				"One Academy AB",
				"Sundsvalls Kommun",
				"Sundsvalls kommun, Vuxenutbildningen",
				"YH Akademin AB");
	}

	@Test
	void findDistinctByLevel() {

		// Act
		final var result = courseRepository.findDistinctBy(LevelProjection.class, Sort.by(LEVEL));

		// Assert
		assertThat(result)
			.extracting(LevelProjection::getLevel)
			.containsExactly(
				"grundläggande vuxenutbildning",
				"gymnasial vuxenutbildning",
				"Kommunal vuxenutbildning som anpassad utbildning på grundläggande nivå",
				"Kommunal vuxenutbildning som anpassad utbildning på gymnasial nivå",
				"yrkeshögskoleutbildning");
	}

	@Test
	void findDistinctByScope() {

		// Act
		final var result = courseRepository.findDistinctBy(ScopeProjection.class, Sort.by(SCOPE));

		// Assert
		assertThat(result)
			.extracting(ScopeProjection::getScope)
			.containsExactly("25.0", "50.0", "75.0", "100.0");
	}

	@Test
	void findDistinctByCredits() {

		// Act
		final var result = courseRepository.findDistinctBy(CreditsProjection.class, Sort.by(CREDITS));

		// Assert
		assertThat(result)
			.extracting(CreditsProjection::getCredits)
			.containsExactly(
				"0.0",
				"50.0",
				"100.0",
				"150.0",
				"200.0",
				"300.0",
				"325.0",
				"350.0",
				"400.0",
				"415.0",
				"430.0",
				"450.0",
				"500.0",
				"600.0",
				"1000.0",
				"1200.0",
				"1400.0",
				"1450.0",
				"1500.0");
	}

	@Test
	void findBySpecification() {

		// Arrange
		final var searchWord = "sundsvall";
		final var pageRequest = PageRequest.of(0, 20, Sort.unsorted());

		// Act
		final var result = courseRepository.findAll((entity, cq, cb) -> cb.like(cb.lower(entity.get(STUDY_LOCATION)), searchWord), pageRequest);

		// Assert
		assertThat(result).isNotNull();
		assertThat(result.getPageable().getPageSize()).isEqualTo(20);
		assertThat(result.getNumberOfElements()).isEqualTo(20);
		assertThat(result.getTotalElements()).isEqualTo(843);
		assertThat(result.getTotalPages()).isEqualTo(43);
		assertThat(result.getContent())
			.hasSize(20)
			.extracting(CourseEntity::getStudyLocation)
			.allMatch(searchWord::equalsIgnoreCase);
	}
}
