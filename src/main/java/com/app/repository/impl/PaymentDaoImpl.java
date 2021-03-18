package com.app.repository.impl;

import com.app.repository.PaymentDao;
import com.app.repository.generic.AbstractGenericDao;
import com.app.model.EPayment;
import com.app.model.Payment;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

public class PaymentDaoImpl extends AbstractGenericDao<Payment> implements PaymentDao {

    @Override
    public Optional<Payment> getbyName(EPayment payment) {
        Optional<Payment> paymentOp = Optional.empty();

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        try {


            tx.begin();

            Query query = entityManager.createQuery("select p from Payment p where p.payment = :payment_name");
            query.setParameter("payment_name", payment);
            paymentOp = Optional.ofNullable((Payment) query.getSingleResult());

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

        return paymentOp;
    }
}
