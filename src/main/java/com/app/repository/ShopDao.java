package com.app.repository;

import com.app.repository.generic.GenericDao;
import com.app.model.Shop;

import java.util.Optional;

public interface ShopDao extends GenericDao<Shop> {
    Optional<Shop> getByNameAndCountry(String name, String country);
}