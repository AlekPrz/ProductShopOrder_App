package com.app.repository.impl;


import com.app.repository.CategoryDao;
import com.app.repository.generic.AbstractGenericDao;
import com.app.model.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

public class CategoryDaoImpl extends AbstractGenericDao<Category> implements CategoryDao {
    @Override
    public Optional<Category> getbyName(String name){
        Optional<Category> categoryOP = Optional.empty();

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        try {


            tx.begin();

            Query query = entityManager.createQuery("select p from Category p where p.name = :category_name");
            query.setParameter("category_name", name);
            categoryOP = Optional.ofNullable((Category) query.getSingleResult());

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
        return categoryOP;

    }
}

