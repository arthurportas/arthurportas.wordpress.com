package com.arthurportas.persistence.daos;

import com.arthurportas.persistence.PersistenceEnum;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by arthurportas on 14/05/2017.
 */
public abstract class GenericDaoImpl<T> implements GenericDao<T>{

    protected EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory(PersistenceEnum.PERSISTENCE_UNIT.getValue()).createEntityManager();
    }

    public T save(T entity) {
        getEntityManager().persist(entity);
        return entity;
    }

    @Override
    public void delete(T entity) {
        getEntityManager().remove(entity);
    }

    @Override
    public T update(T entity) {
        return getEntityManager().merge(entity);
    }
}
