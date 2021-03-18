package com.app.repository;

import com.app.repository.generic.GenericDao;
import com.app.model.Customer;

import java.util.Optional;

public interface CustomerDao extends GenericDao<Customer> {

    Optional<Customer> getByNameSurnameAndCountry(String name, String surname, String country);

}
