package com.app.repository.impl;

import com.app.repository.connection.DbConeection;

import javax.persistence.EntityManagerFactory;

public class Delet {



    private EntityManagerFactory entityManagerFactory
            = DbConeection.getInstance().getEntityManagerFactory();
}
