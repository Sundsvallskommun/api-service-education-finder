package se.sundsvall.educationfinder.integration.db.specification;

import static java.util.Objects.nonNull;

import jakarta.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationBuilder<T> {

	/**
	 * Method builds an equal filter if value is not null. If value is null, method returns an always-true predicate
	 * (meaning no filtering will be applied for sent in attribute)
	 *
	 * @param  attribute name that will be used in filter
	 * @param  value     value (or null) to compare against
	 * @return           Specification<T> matching sent in comparison
	 */
	public Specification<T> buildEqualFilter(final String attribute, final Object value) {
		return (entity, cq, cb) -> nonNull(value) ? cb.equal(entity.get(attribute), value) : cb.and();
	}

	/**
	 * Method builds an equal or null filter if value is not null. If value is null, method returns an always-true predicate
	 * (meaning no filtering will be applied for sent in attribute)
	 *
	 * @param  attribute name that will be used in filter
	 * @param  value     value (or null) to compare against
	 * @return           Specification<T> matching sent in comparison
	 */
	public Specification<T> buildEqualOrNullFilter(final String attribute, final Object value) {
		return (entity, cq, cb) -> {
			if (value != null) {
				return cb.or(
					cb.equal(entity.get(attribute), value),
					cb.isNull(entity.get(attribute)));
			}
			return cb.and();
		};
	}

	/**
	 * Method builds an equal ignore case filter if value is not null. If value is null, method returns an always-true
	 * predicate (meaning no filtering will be applied for sent in attribute)
	 *
	 * @param  attribute name that will be used in filter
	 * @param  value     value (or null) to compare against
	 * @return           Specification<T> matching sent in comparison
	 */
	public Specification<T> buildEqualsIgnoreCaseFilter(final String attribute, final String value) {
		return (entity, cq, cb) -> nonNull(value) ? cb.equal(cb.lower(entity.get(attribute)), value.toLowerCase()) : cb.and();
	}

	/**
	 * Method builds a starting with ignore case filter that accepts a list of values. If values are null or empty, method
	 * returns an always-true predicate (meaning no filtering will be applied for sent in attribute)
	 *
	 * @param  attribute name that will be used in filter
	 * @param  values    list of values to compare against
	 * @return           Specification<T> matching sent in comparison
	 */
	public Specification<T> buildStartingWithIgnoreCaseFilter(final String attribute, final List<String> values) {
		return (entity, cq, cb) -> {
			if (values == null || values.isEmpty()) {
				return cb.and();
			}
			List<Predicate> predicates = new ArrayList<>();
			for (String value : values) {
				if (value != null && !value.trim().isEmpty()) {
					String pattern = value.trim().toLowerCase() + "%";
					predicates.add(cb.like(cb.lower(cb.trim(entity.get(attribute))), pattern));
				}
			}

			if (predicates.isEmpty()) {
				return cb.and();
			}

			return cb.or(predicates.toArray(new Predicate[0]));
		};
	}

	/**
	 * Method builds an ending with ignore case filter that accepts a list of values. If values are null or empty, method
	 * returns an always-true predicate (meaning no filtering will be applied for sent in attribute)
	 *
	 * @param  attribute name that will be used in filter
	 * @param  values    value (or null) to compare against
	 * @return           Specification<T> matching sent in comparison
	 */
	public Specification<T> buildEndingWithIgnoreCaseFilter(final String attribute, final List<String> values) {
		return (entity, cq, cb) -> {
			if (values == null || values.isEmpty()) {
				return cb.and();
			}

			List<Predicate> predicates = new ArrayList<>();
			for (String value : values) {
				if (value != null && !value.trim().isEmpty()) {
					String pattern = "%" + value.trim().toLowerCase();
					predicates.add(cb.like(cb.lower(cb.trim(entity.get(attribute))), pattern));
				}
			}

			if (predicates.isEmpty()) {
				return cb.and();
			}

			return cb.or(predicates.toArray(new Predicate[0]));
		};
	}

	/**
	 * Method builds a like ignore case filter if value is not null. If value is null, method returns an always-true
	 * predicate (meaning no filtering will be applied for sent in attribute)
	 *
	 * @param  attribute name that will be used in filter
	 * @param  value     value (or null) to compare against
	 * @return           Specification<T> matching sent in comparison
	 */
	public Specification<T> buildLikeIgnoreCaseFilter(final String attribute, final String value) {
		return (entity, cq, cb) -> nonNull(value)
			? cb.like(cb.lower(entity.get(attribute)), "%" + value.toLowerCase() + "%")
			: null;
	}

	/**
	 * Method builds a filter depending on sent in time stamps. If both values are null, method returns an always-true
	 * predicate (meaning no filtering will be applied for sent in attribute)
	 *
	 * @param  attribute name that will be used in filter
	 * @param  value     value (or null) to compare against
	 * @return           Specification<T> matching sent in comparison
	 */
	public Specification<T> buildDateIsEqualOrBeforeFilter(final String attribute, final LocalDate value) {
		return (entity, cq, cb) -> nonNull(value) ? cb.lessThanOrEqualTo(entity.get(attribute), value) : cb.and();
	}

	/**
	 * Method builds a filter depending on sent in time stamps. Matches dates that is equal, before or null. If both values
	 * are null, method returns an always-true predicate (meaning no filtering will be applied for sent in attribute)
	 *
	 * @param  attribute name that will be used in filter
	 * @param  value     value (or null) to compare against
	 * @return           Specification<T> matching sent in comparison
	 */
	public Specification<T> buildDateIsEqualOrBeforeOrNullFilter(final String attribute, final LocalDate value) {
		return (entity, cq, cb) -> {
			if (value != null) {
				return cb.or(
					cb.lessThanOrEqualTo(entity.get(attribute), value),
					cb.isNull(entity.get(attribute)));
			}
			return cb.and();
		};
	}

	/**
	 * Method builds a filter depending on sent in time stamps. If both values are null, method returns an always-true
	 * predicate (meaning no filtering will be applied for sent in attribute)
	 *
	 * @param  attribute name that will be used in filter
	 * @param  value     value (or null) to compare against
	 * @return           Specification<T> matching sent in comparison
	 */
	public Specification<T> buildDateIsEqualOrAfterFilter(final String attribute, final LocalDate value) {
		return (entity, cq, cb) -> nonNull(value) ? cb.greaterThanOrEqualTo(entity.get(attribute), value) : cb.and();
	}

	/**
	 * Method builds a filter depending on sent in time stamps. Matches dates that is equal, after or null. If both values
	 * are null, method returns an always-true predicate (meaning no filtering will be applied for sent in attribute)
	 *
	 * @param  attribute name that will be used in filter
	 * @param  value     value (or null) to compare against
	 * @return           Specification<T> matching sent in comparison
	 */
	public Specification<T> buildDateIsEqualOrAfterOrNullFilter(final String attribute, final LocalDate value) {
		return (entity, cq, cb) -> {
			if (value != null) {
				return cb.or(
					cb.greaterThanOrEqualTo(entity.get(attribute), value),
					cb.isNull(entity.get(attribute)));
			}
			return cb.and();
		};
	}

	/**
	 * Method builds a filter depending on sent in list of string, case-insensitive. If values are null, method returns an
	 * always-true predicate (meaning no filtering will be applied for sent in attribute)
	 *
	 * @param  attribute name of attribute that will be used in filter
	 * @param  values    String-values (or null) to compare against
	 * @return           Specification<T> matching sent in comparison
	 */
	public Specification<T> buildInFilterIgnoreCase(final String attribute, final List<String> values) {
		return (root, query, cb) -> {
			if (values == null || values.isEmpty()) {
				return null;
			}
			var lowerCaseValues = values.stream()
				.filter(Objects::nonNull)
				.map(String::toLowerCase)
				.toList();
			return cb.lower(root.get(attribute)).in(lowerCaseValues);
		};
	}

	public Specification<T> buildNumberInFilter(final String attribute, final List<Integer> values) {
		return (root, query, cb) -> {
			if (values == null || values.isEmpty()) {
				return null;
			}
			return root.get(attribute).in(values);
		};
	}

	/**
	 * Method builds a free text search filter for a list of attributes. If the search string is null or empty, an
	 * always-true predicate is returned (meaning no filtering will be applied for the sent in attributes)
	 *
	 * @param  attributes   list of attributes to search in
	 * @param  searchString the search string
	 * @return              Specification<T> matching the sent in comparison
	 */
	public Specification<T> buildFreeTextSearch(final List<String> attributes, final String searchString) {
		return (root, query, cb) -> {
			if (searchString == null || searchString.trim().isEmpty()) {
				return cb.conjunction();
			}

			String likePattern = "%" + searchString.toLowerCase() + "%";

			var predicates = attributes.stream()
				.map(attribute -> cb.like(cb.lower(root.get(attribute)), likePattern))
				.toArray(Predicate[]::new);

			return cb.or(predicates);
		};
	}

	/**
	 * Method builds a date range filter for two attributes. If both start and end date are null, an always-true predicate
	 * is returned (meaning no filtering will be applied for the sent in attributes)
	 *
	 * @param  startAttribute Date attribute to start filtering from
	 * @param  endAttribute   Date attribute to end filtering at
	 * @param  startDate      The start date of the range
	 * @param  endDate        The end date of the range
	 * @return                Specification<T> matching the sent in comparison
	 */
	public Specification<T> buildDateRangeFilter(final String startAttribute, final String endAttribute, final LocalDate startDate, final LocalDate endDate) {
		return (root, query, cb) -> {
			if (startDate != null && endDate != null) {
				Predicate startPredicate = cb.lessThanOrEqualTo(root.get(startAttribute), endDate);
				Predicate endPredicate = cb.greaterThanOrEqualTo(root.get(endAttribute), startDate);
				return cb.and(startPredicate, endPredicate);
			} else if (startDate != null) {
				return cb.greaterThanOrEqualTo(root.get(startAttribute), startDate);
			} else if (endDate != null) {
				return cb.lessThanOrEqualTo(root.get(endAttribute), endDate);
			}
			return cb.conjunction();
		};
	}

	public Specification<T> distinct() {
		return (entity, cq, cb) -> {
			cq.distinct(true);
			return null;
		};
	}

}
