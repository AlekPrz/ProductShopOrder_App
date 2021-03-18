package com.app.repository.impl;

import com.app.repository.StockDao;
import com.app.repository.generic.AbstractGenericDao;
import com.app.model.Stock;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

public class StockDaoImpl  extends AbstractGenericDao<Stock> implements StockDao {

    @Override
    public Optional<Stock> getByProductandShop(String productName, String shopName) {
        Optional<Stock> stockOp = Optional.empty();
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();


            try {
                tx.begin();
                Query query = entityManager.createQuery("select s " +
                        "from Stock s " +
                        "join s.product p " +
                        "join s.shop sh " +
                        "where p.name = :product_name  and sh.name=:shop_name");
                query.setParameter("shop_name", shopName);
                query.setParameter("product_name", productName);

                stockOp = Optional.ofNullable((Stock) query.getSingleResult());

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

        return stockOp;


    }
}



