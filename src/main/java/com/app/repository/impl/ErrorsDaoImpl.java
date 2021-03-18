package com.app.repository.impl;

import com.app.repository.ErrorsDao;
import com.app.repository.generic.AbstractGenericDao;
import com.app.model.Errors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ErrorsDaoImpl extends AbstractGenericDao<Error> implements ErrorsDao {


    @Override
    public void add(Errors error) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        try {
            tx.begin();
            entityManager.persist(error);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
