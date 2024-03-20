package se.sundsvall.educationfinder.integration.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface ReadOnlyRepository<T, I> extends Repository<T, I> {

	/**
	 * Returns an entity by its id.
	 *
	 * @param  id                       must not be {@literal null}.
	 * @return                          the entity with the given id or {@literal Optional#empty()} if none found.
	 * @throws IllegalArgumentException if {@literal id} is {@literal null}.
	 */
	Optional<T> findById(I id);

	/**
	 * Returns all instances of the type.
	 *
	 * @return all entities
	 */
	List<T> findAll();

	/**
	 * Returns all distinct values denoted by the provided type.
	 *
	 * @param  <P>
	 * @param  type The type to find all distinct values for.
	 * @param  sort The Sortable to use.
	 * @return
	 */
	<P> List<P> findDistinctBy(Class<P> type, Sort sort);
}
