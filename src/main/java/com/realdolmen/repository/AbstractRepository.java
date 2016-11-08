package com.realdolmen.repository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.realdolmen.exception.ConcurrentRemoveException;
import com.realdolmen.exception.ConcurrentUpdateException;

public class AbstractRepository<T> {

    @PersistenceContext
    EntityManager entityManager;

    private Logger logger = LoggerFactory.getLogger(AbstractRepository.class);

    private Class<T> entityClass;

    public T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    /*
      @throws ConcurrentUpdateException when concurrent modification happens. In this case the state of the entity is reset.
    */
    public T update(T entity) throws ConcurrentUpdateException {
        T updatedEntity = null;
        try {
            updatedEntity = entityManager.merge(entity);
        } catch (PersistenceException e) {
            if (e.getCause() instanceof StaleObjectStateException) {
                logger.error(e.getMessage());
                throw new ConcurrentUpdateException("Trying to update an entity that was already updated!");
            } else {
                logger.error("Exception occured " + e.getCause());
            }
        }

        return updatedEntity;
    }



    /*
        @throws ConcurrentRemoveException when concurrent modification happens. Remove does not happen twice.
      */
    public void delete(T entity) throws ConcurrentRemoveException {
        try {
            entityManager.remove(entityManager.merge(entity));
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new ConcurrentRemoveException("Trying to remove an entity that was already deleted!");
        }
    }

    /*
        @throws ConcurrentRemoveException when concurrent modification happens. Remove does not happen twice.
      */
    public void deleteById(Integer id) throws ConcurrentRemoveException {
        try {
            entityManager.remove(entityManager.find(getEntityClass(), id));
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new ConcurrentRemoveException("Trying to remove an entity that was already deleted!");
        }
    }

    public T findById(Long id) {
        return entityManager.find(getEntityClass(), id);
    }

    public List<T> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getEntityClass());
        cq.select(cq.from(getEntityClass()));
        return entityManager.createQuery(cq).getResultList();

    }

    @SuppressWarnings("unchecked")
    private Class<T> getEntityClass() {
        if (entityClass == null) {
            Type type = this.getClass().getGenericSuperclass();
            ParameterizedType paramType = (ParameterizedType) type;
            entityClass = (Class<T>) paramType.getActualTypeArguments()[0];
        }
        return entityClass;
    }

    public EntityManager entityManager() {
        return this.entityManager;
    }

}
