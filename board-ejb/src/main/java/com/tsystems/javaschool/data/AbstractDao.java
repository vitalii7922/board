package com.tsystems.javaschool.data;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;


public abstract class AbstractDao<T extends Serializable> {

    private Class<T> clazz;

    @Produces
    @PersistenceContext
    EntityManager entityManager;

    @Inject
    public AbstractDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T findOne(long id) {
        return entityManager.find(clazz, id);
    }

    public List findAll() {
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }

    public T create(T entity) {
        if (entity != null) {
            entityManager.persist(entity);
        }
        return entity;
    }

    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }

    public void deleteById(long entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }
}
