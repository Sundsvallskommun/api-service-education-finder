package se.sundsvall.educationfinder.integration.db.specification.custom;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import net.kaczmarzyk.spring.data.jpa.domain.ComparableSpecification;
import net.kaczmarzyk.spring.data.jpa.utils.Converter;
import net.kaczmarzyk.spring.data.jpa.utils.QueryContext;

/**
 * Will match when the condition is true (i.e. the HTTP parameter/DB-value comparison is true) or when the DB-value is
 * null.
 */
public class LessThanOrEqualIncludeNullValues<T> extends ComparableSpecification<T> {

	private static final long serialVersionUID = -5950595698794605675L;

	public LessThanOrEqualIncludeNullValues(QueryContext queryContext, String path, String[] httpParamValues, Converter converter) {
		super(queryContext, path, httpParamValues, converter);
	}

	@Override
	protected <Y extends Comparable<? super Y>> Predicate makePredicate(CriteriaBuilder cb, Expression<? extends Y> x, Y y) {
		return cb.or(x.isNull(), cb.lessThanOrEqualTo(x, y));
	}
}
