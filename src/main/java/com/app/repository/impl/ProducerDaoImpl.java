package com.app.repository.impl;

import com.app.repository.ProducerDao;
import com.app.repository.generic.AbstractGenericDao;
import com.app.model.Producer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

public class ProducerDaoImpl extends AbstractGenericDao<Producer> implements ProducerDao {

    @Override
    public Optional<Producer> getByNameAndCountryAndTrade(String producentName, String countryName, String tradeName) {
        Optional<Producer> producerOp = Optional.empty();

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        try {


            tx.begin();

                Query query = entityManager.createQuery("select p " +
                        "from Producer p " +
                        "join p.country c " +
                        "join p.trade t " +
                        "where p.name = :producer_Name " +
                        "and c.city =:country_Name " +
                        "and t.name=:trade_name");

                query.setParameter("producer_Name", producentName);
                query.setParameter("country_Name", countryName);
                query.setParameter("trade_name", tradeName);


                producerOp = Optional.ofNullable((Producer) query.getSingleResult());



                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }


            }

            finally {
                if (entityManager != null) {
                    entityManager.close();
                }
            }

        return producerOp;
    }

    @Override
    public Optional<Producer> getByNameAndCountry(String producentName, String countryName)  {
        Optional<Producer> producerOp = Optional.empty();

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

            try {
                tx.begin();
                Query query = entityManager.createQuery("select p " +
                        "from Producer p " +
                        "join p.country c " +
                        "where p.name = :producer_Name " +
                        "and c.city =:country_Name " );

                query.setParameter("producer_Name", producentName);
                query.setParameter("country_Name", countryName);

                producerOp = Optional.ofNullable((Producer) query.getSingleResult());


                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            } finally {
                if (entityManager != null) {
                    entityManager.close();
                }
            }

        return producerOp;
    }
}




