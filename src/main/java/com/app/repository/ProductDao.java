package com.app.repository;

import com.app.dto.info.ProductInfo;
import com.app.repository.generic.GenericDao;
import com.app.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao extends GenericDao<Product>{


    Optional<Product> getByNameCategoryProducent(String productName,String categoryName,
                                          String producentName );
    Optional<Product> getByNameandCategory(String productName, String category );


    List<ProductInfo> getProductsByClientCountryAndAge(String name, Integer age1, Integer age2);

    List<ProductInfo> getProductsWithMaxPrizeInCategory();

    List<Product> getProductsWithMaxPrizeInCategoryy();





}