package com.share.lifetime.service;

import com.share.lifetime.domain.DTO;
import com.share.lifetime.domain.AbstractQuery;
import com.share.lifetime.exception.ServiceException;

public interface RepositoryService<T extends DTO, ID, Q extends AbstractQuery> extends Service {

	<S extends T> S save(S entity) throws ServiceException;

	<S extends T> Iterable<S> saveAll(Iterable<S> entities) throws ServiceException;

	T findById(ID id) throws ServiceException;

	boolean existsById(ID id) throws ServiceException;

	Iterable<T> findAll() throws ServiceException;

	Iterable<T> findAllById(Iterable<ID> ids) throws ServiceException;

	Iterable<T> findAllByQuery(AbstractQuery query) throws ServiceException;

	long count() throws ServiceException;

	long countByQuery(AbstractQuery query) throws ServiceException;

	void deleteById(ID id) throws ServiceException;

	void delete(T entity) throws ServiceException;

	void deleteAll(Iterable<? extends T> entities) throws ServiceException;

	void deleteAll() throws ServiceException;

	<U extends T> U updateById(ID id) throws ServiceException;

}
