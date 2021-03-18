package com.app.repository.impl;

import com.app.repository.TradeDao;
import com.app.repository.generic.AbstractGenericDao;
import com.app.model.Trade;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

public class TradeDaoImpl extends AbstractGenericDao<Trade> implements TradeDao {

    @Override
    public Optional<Trade> getByName(String producentName) {
        Optional<Trade> countryOP = Optional.empty();
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

            try {
                tx.begin();

                Query query = entityManager.createQuery("select p from Trade p where p.name = :trade_name");
                query.setParameter("trade_name", producentName);
                countryOP = Optional.ofNullable((Trade) query.getSingleResult());

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
