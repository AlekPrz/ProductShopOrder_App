package com.app.repository;

import com.app.repository.generic.GenericDao;
import com.app.model.Producer;

import java.util.Optional;

public interface ProducerDao extends GenericDao<Producer> {


    Optional<Producer> getByNameAndCountryAndTrade(String producentName,String countryName,String tradeName);
    Optional<Producer> getByNameAndCountry(String producentName,String countryName);

}
