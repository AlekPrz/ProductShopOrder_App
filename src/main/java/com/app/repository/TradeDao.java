package com.app.repository;


import com.app.repository.generic.GenericDao;
import com.app.model.Trade;

import java.util.Optional;

public interface TradeDao extends GenericDao<Trade> {


    Optional<Trade> getByName(String tradeName);
}
