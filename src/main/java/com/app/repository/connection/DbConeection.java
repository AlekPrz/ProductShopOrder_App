package com.app.repository.connection;

import com.app.model.Category;

import javax.persistence.*;
import java.util.Optional;

public class DbConeection {
    private static DbConeection ourInstance = new DbConeection();

    public static DbConeection getInstance() {
        return ourInstance;
    }

    private static EntityManagerFactory entityManagerFactory;

    private DbConeection() {
        entityManagerFactory
                = Persistence.createEntityManagerFactory("HIBERNATE_UNIT");
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void close() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }

    }

    public static void deleteAll() {


        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();


        try {


            tx.begin();
            entityManager.createQuery("delete from Producer ").executeUpdate();
            entityManager.createQuery("delete from Trade ").executeUpdate();

            entityManager.createQuery("delete from Payment ").executeUpdate();
            entityManager.createQuery("delete from Stock ").executeUpdate();
            entityManager.createQuery("delete from Shop ").executeUpdate();
            entityManager.createQuery("delete from CustomerOrder ").executeUpdate();
            entityManager.createQuery("delete from Product ").executeUpdate();
            entityManager.createQuery("delete from Category ").executeUpdate();
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

}
