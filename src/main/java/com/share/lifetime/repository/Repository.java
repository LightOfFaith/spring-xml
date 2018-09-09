package com.share.lifetime.repository;

import java.util.List;

import com.share.lifetime.entity.Entity;

public interface Repository<T extends Entity> {

	T create(T entity);

	void delete(T entity);

	void update(T entity);

	T get(String id);

	List<T> getByEntity(T entity);

}
