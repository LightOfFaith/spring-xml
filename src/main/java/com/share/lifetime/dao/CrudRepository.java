package com.share.lifetime.dao;

import com.share.lifetime.domain.AbstractQuery;
import com.share.lifetime.entity.AbstractEntity;
import com.share.lifetime.exception.DAOException;

public interface CrudRepository<T extends AbstractEntity, ID, Q extends AbstractQuery> extends Repository<T, ID, Q> {

	<S extends T> S save(S entity) throws DAOException;

	<S extends T> Iterable<S> saveAll(Iterable<S> entities) throws DAOException;

	T findById(ID id) throws DAOException;

	boolean existsById(ID id) throws DAOException;

	Iterable<T> findAll() throws DAOException;

	Iterable<T> findAllById(Iterable<ID> ids) throws DAOException;

	Iterable<T> findAllByQuery(AbstractQuery query) throws DAOException;

	long count() throws DAOException;

	long countByQuery(AbstractQuery query) throws DAOException;

	void deleteById(ID id) throws DAOException;

	void delete(T entity) throws DAOException;

	void deleteAll(Iterable<? extends T> entities);

	void deleteAll() throws DAOException;

	<U extends T> U updateById(ID id) throws DAOException;

}
