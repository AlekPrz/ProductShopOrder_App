package com.app.repository.generic;

import com.app.exceptions.MyException;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {
    Optional<T> addOrUpdate(T t)throws MyException;
    Optional<T> delete(Long id)throws MyException;

    Optional<T> findOne(Long id) throws MyException;
    List<T> findAll()throws MyException;



}
