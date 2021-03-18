package com.app.repository;

import com.app.repository.generic.GenericDao;
import com.app.model.EPayment;
import com.app.model.Payment;

import java.util.Optional;

public interface PaymentDao extends GenericDao<Payment> {

    Optional<Payment> getbyName(EPayment payment);
}
