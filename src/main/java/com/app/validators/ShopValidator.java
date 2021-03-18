package com.app.validators;

import com.app.dto.CustomerDto;
import com.app.dto.ShopDto;
import com.app.exceptions.MyException;
import com.app.model.Customer;
import com.app.model.Shop;
import com.app.repository.CustomerDao;
import com.app.repository.ShopDao;
import com.app.repository.impl.CustomerDaoImpl;
import com.app.repository.impl.ShopDaoImpl;

import java.util.Map;
import java.util.Optional;

public class ShopValidator extends AbstractValidator<ShopDto> {
    private ShopDao shopDao = new ShopDaoImpl();


    @Override
    public    Map<String, String> validate(ShopDto shopDto) {

        errors.clear();

        if (shopDto == null) {
            errors.put("shop", "is null");
        }

        if (!isShopNameValid(shopDto)) {
            errors.put("name or surname", "is not correct: " + shopDto.getName());
        }

        Optional<Shop> shopOp = shopDao.getByNameAndCountry(shopDto.getName(), shopDto.getCountryDto().getCity());

        if (shopOp.isPresent()) {
            errors.put("shop", "is not correct becouse its exist");
        }


        return errors;
    }

    private boolean isShopNameValid(ShopDto shopDto) {
        return shopDto.getName() != null
                &&
                shopDto.getName().matches("[A-Z ]+");


    }
}
