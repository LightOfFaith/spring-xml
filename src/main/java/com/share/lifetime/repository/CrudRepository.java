package com.share.lifetime.repository;

import com.share.lifetime.domain.Query;
import com.share.lifetime.entity.Entity;
import com.share.lifetime.exception.DAOException;

public interface CrudRepository<T extends Entity, ID, Q extends Query> extends Repository<T, ID, Q> {

	<S extends T> S save(S entity) throws DAOException;

	<S extends T> Iterable<S> saveAll(Iterable<S> entities) throws DAOException;

	T findById(ID id) throws DAOException;

	boolean existsById(ID id) throws DAOException;

	Iterable<T> findAll() throws DAOException;

	Iterable<T> findAllById(Iterable<ID> ids) throws DAOException;

	Iterable<T> findAllByQuery(Query query) throws DAOException;

	long count() throws DAOException;

	long countByQuery(Query query) throws DAOException;

	void deleteById(ID id) throws DAOException;

	void delete(T entity) throws DAOException;

	void deleteAll(Iterable<? extends T> entities);

	void deleteAll() throws DAOException;

	<U extends T> U updateById(ID id) throws DAOException;

}
