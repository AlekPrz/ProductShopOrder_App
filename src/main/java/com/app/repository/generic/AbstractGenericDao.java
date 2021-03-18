package com.app.repository.generic;

import com.app.repository.connection.DbConeection;
import com.app.exceptions.MyException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class AbstractGenericDao<T> implements GenericDao<T> {

    private EntityManagerFactory entityManagerFactory
            = DbConeection.getInstance().getEntityManagerFactory();

    // ten obiekt kiedy juz skonkretyzujesz T na konkretna klase bedzie wiedzial jaka to
    // klasa
    private Class<T> entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
            .getActualTypeArguments()[0];

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    @Override
    public Optional<T> addOrUpdate(T t) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        Optional<T> op = Optional.empty();

        try {
            tx.begin();
            op = Optional.of(entityManager.merge(t));
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new MyException("[DAO] ERROR WHILE INSERTING NEW ROW INTO " + entityClass.getCanonicalName() + ", ERROR MESSAGE: " + e.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return op;
    }



    @Override
    public Optional<T> delete(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        Optional<T> op = Optional.empty();

        try {
            tx.begin();
            op = Optional.of(entityManager.find(entityClass, id));
            entityManager.remove(id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new MyException("[DAO] ERROR WHILE REMOVING ROW FROM " + entityClass.getCanonicalName() + ", ERROR MESSAGE: " + e.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return op;
    }

    @Override
    public Optional<T> findOne(Long id)  {
        Optional<T> element = Optional.empty();

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        try {
            tx.begin();
            element = Optional.of(entityManager.find(entityClass, id));
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new MyException("[DAO] ERROR WHILE GETTING ONE ROW FROM " + entityClass.getCanonicalName() + ", ERROR MESSAGE: " + e.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return element;
    }

    @Override
    public List<T> findAll(){
        List<T> elements = null;

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        try {
            tx.begin();
            Query query = entityManager.createQuery("select t from " + entityClass.getCanonicalName() + " t");
            elements = query.getResultList();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new MyException("[DAO] ERROR WHILE GETTING ALL ROWS FROM " + entityClass.getCanonicalName() + ", ERROR MESSAGE: " + e.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return elements;
    }
}
