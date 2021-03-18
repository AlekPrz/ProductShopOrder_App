package com.app.validators;

import com.app.dto.CustomerDto;
import com.app.dto.ShopDto;
import com.app.dto.TradeDto;
import com.app.exceptions.MyException;
import com.app.model.Customer;
import com.app.model.Shop;
import com.app.model.Trade;
import com.app.repository.CustomerDao;
import com.app.repository.ShopDao;
import com.app.repository.TradeDao;
import com.app.repository.impl.CustomerDaoImpl;
import com.app.repository.impl.ShopDaoImpl;
import com.app.repository.impl.TradeDaoImpl;

import java.util.Map;
import java.util.Optional;

public class TradeValidator extends AbstractValidator<TradeDto> {
    private TradeDao tradeDao = new TradeDaoImpl();


    @Override
    public Map<String, String> validate(TradeDto tradeDto) {

        errors.clear();

        if (tradeDto == null) {
            errors.put("trade", "is null");
        }

        if (!isTradeNameValid(tradeDto)) {
            errors.put("name", "is not correct: " + tradeDto.getName());
        }

        Optional<Trade> tradeOp = tradeDao.getByName(tradeDto.getName());

        if (tradeOp.isPresent()) {
            errors.put("trade", "is not correct becouse its exist");
        }


        return errors;
    }

    private boolean isTradeNameValid(TradeDto tradeDto) {
        return tradeDto.getName() != null
                &&
                tradeDto.getName().matches("[A-Z ]+");


    }
}
