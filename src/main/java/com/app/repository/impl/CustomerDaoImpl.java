package com.app.repository.impl;

import com.app.repository.CustomerDao;
import com.app.repository.generic.AbstractGenericDao;
import com.app.model.Customer;

import javax.persistence.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import java.util.Optional;

public class CustomerDaoImpl extends AbstractGenericDao<Customer> implements CustomerDao {



    @Override
    public Optional<Customer> getByNameSurnameAndCountry(String name, String surname, String country) {
        Optional<Customer> customerOp = Optional.empty();
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();


            try {
                tx.begin();
                Query query = entityManager.createQuery("select p " +
                        "from Customer p " +
                        "join p.country c " +
                        "where p.name = :customer_name and p.surname =:customer_surname and c.city=:country_name");
                query.setParameter("customer_name", name);
                query.setParameter("customer_surname", surname);
                query.setParameter("country_name", country);

                customerOp = Optional.ofNullable((Customer) query.getSingleResult());

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

        return customerOp;
    }
}
