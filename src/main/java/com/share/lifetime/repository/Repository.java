package com.share.lifetime.repository;

import java.util.List;

import com.share.lifetime.entity.Entity;
import com.share.lifetime.exception.DAOException;

public interface Repository<T extends Entity> {

	T create(T entity) throws DAOException;

	void delete(T entity) throws DAOException;

	void update(T entity) throws DAOException;

	T get(String id) throws DAOException;

	List<T> getByEntity(T entity) throws DAOException;

}
