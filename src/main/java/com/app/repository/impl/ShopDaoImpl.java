package com.app.repository.impl;

import com.app.repository.ShopDao;
import com.app.repository.generic.AbstractGenericDao;
import com.app.model.Shop;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

public class ShopDaoImpl extends AbstractGenericDao<Shop> implements ShopDao {

    @Override
    public Optional<Shop> getByNameAndCountry(String name, String country) {
        Optional<Shop> shopOp = Optional.empty();
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        try {
            tx.begin();
            Query query = entityManager.createQuery("select p " +
                    "from Shop p " +
                    "join p.country c " +
                    "where p.name = :shop_name  and c.city=:country_name");
            query.setParameter("shop_name", name);
            query.setParameter("country_name", country);

            shopOp = Optional.ofNullable((Shop) query.getSingleResult());

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

        return shopOp;
    }


}
