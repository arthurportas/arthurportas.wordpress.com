package com.arthurportas.persistence.daos;

/**
 * Created by arthurportas on 10/05/2017.
 */
public interface GenericDao<T> {

    T save(T entity);

    void delete(T entity);

    T update(T entity);
}
