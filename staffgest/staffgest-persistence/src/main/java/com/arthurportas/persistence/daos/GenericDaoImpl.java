package com.arthurportas.persistence.daos;

import com.arthurportas.persistence.PersistenceEnum;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by arthurportas on 14/05/2017.
 */
public abstract class GenericDaoImpl {

    protected EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory(PersistenceEnum.PERSISTENCE_UNIT.getValue()).createEntityManager();
    }
}
