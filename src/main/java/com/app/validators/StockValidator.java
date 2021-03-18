package com.app.validators;

import com.app.dto.CustomerDto;
import com.app.dto.ShopDto;
import com.app.dto.StockDto;
import com.app.exceptions.MyException;
import com.app.model.Customer;
import com.app.model.Shop;
import com.app.model.Stock;
import com.app.repository.CustomerDao;
import com.app.repository.ShopDao;
import com.app.repository.StockDao;
import com.app.repository.impl.CustomerDaoImpl;
import com.app.repository.impl.ShopDaoImpl;
import com.app.repository.impl.StockDaoImpl;

import java.util.Map;
import java.util.Optional;

public class StockValidator extends AbstractValidator<StockDto> {
    private StockDao stockDao = new StockDaoImpl();


    @Override
    public Map<String, String> validate(StockDto stockDto) {

        errors.clear();

        if (stockDto == null) {
            errors.put("stock", "is null");
        }




        return errors;
    }


}
