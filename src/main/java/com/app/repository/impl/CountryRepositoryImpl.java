package com.app.repository.impl;

import com.app.repository.CountryRepository;
import com.app.repository.generic.AbstractGenericDao;
import com.app.model.Country;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

public class CountryRepositoryImpl extends AbstractGenericDao<Country> implements CountryRepository {


    @Override
    public void deleteAll() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();


        try {
            tx.begin();
            entityManager.createQuery("delete from Trade ").executeUpdate();
            entityManager.createQuery("delete from Payment ").executeUpdate();
            entityManager.createQuery("delete from Stock ").executeUpdate();
            entityManager.createQuery("delete from Shop ").executeUpdate();
            entityManager.createQuery("delete from CustomerOrder ").executeUpdate();
            entityManager.createQuery("delete from Product ").executeUpdate();
            entityManager.createQuery("delete from Category ").executeUpdate();
            entityManager.createQuery("delete from Producer ").executeUpdate();
            entityManager.createQuery("delete from Customer").executeUpdate();
            entityManager.createQuery("delete from Country").executeUpdate();


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


    @Override
    public Optional<Country> getbyName(String name) {
        Optional<Country> countryOP = Optional.empty();
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();


            try {
                tx.begin();

                Query query = entityManager.createQuery("select p from Country p where p.city = :city_name");
                query.setParameter("city_name", name);
                countryOP = Optional.ofNullable((Country) query.getSingleResult());

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
        return countryOP;

    }
    }

