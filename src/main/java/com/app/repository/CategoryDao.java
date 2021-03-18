package com.app.repository;

import com.app.repository.generic.GenericDao;
import com.app.model.Category;

import java.util.Optional;

public interface CategoryDao extends GenericDao<Category>{
    Optional<Category> getbyName(String name);
}
