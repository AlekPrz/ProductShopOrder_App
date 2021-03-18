package com.app.repository;

import com.app.repository.generic.GenericDao;
import com.app.model.Stock;

import java.util.Optional;

public interface StockDao extends GenericDao<Stock> {

    Optional<Stock> getByProductandShop(String name, String country);
}
