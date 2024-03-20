package se.sundsvall.educationfinder.integration.db;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface ReadOnlyRepository<T, I> extends Repository<T, I> {

	T findById(I id);

	List<T> findAll();

	/**
	 * Find all distinct values denoted by the provided type.
	 *
	 * @param  <P>
	 * @param  type The type to find all distinct values for.
	 * @param  sort The Sortable to use.
	 * @return
	 */
	<P> List<P> findDistinctBy(Class<P> type, Sort sort);
}
