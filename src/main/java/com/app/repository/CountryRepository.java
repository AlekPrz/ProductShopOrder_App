package com.app.repository;

import com.app.exceptions.MyException;
import com.app.repository.generic.GenericDao;
import com.app.model.Country;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Optional;

public interface CountryRepository extends GenericDao<Country> {
    void deleteAll();

    Optional<Country> getbyName(String name);
}
