package com.app.validators;

import com.app.dto.ProducerDto;
import com.app.dto.TradeDto;
import com.app.model.Country;
import com.app.model.Producer;
import com.app.model.Trade;
import com.app.repository.CountryRepository;
import com.app.repository.ProducerDao;
import com.app.repository.TradeDao;
import com.app.repository.impl.CountryRepositoryImpl;
import com.app.repository.impl.ProducerDaoImpl;
import com.app.repository.impl.TradeDaoImpl;

import java.util.Map;
import java.util.Optional;

public class ProducerValidator extends AbstractValidator<ProducerDto> {
    private ProducerDao producerDao = new ProducerDaoImpl();
    private CountryRepository countryRepository = new CountryRepositoryImpl();
    private TradeDao tradeDao = new TradeDaoImpl();


    @Override
    public Map<String, String> validate(ProducerDto producerDto) {
        Optional<Trade> trade = tradeDao.getByName(producerDto.getTradeDto().getName());


        errors.clear();


        if (!countryRepository.getbyName(producerDto.getCountryDto().getCity()).isPresent()) {
            errors.put("country",
                    "is not correct becouse its not exist, check your json file or database" +
                            "");
        }
        if (!trade.isPresent()) {
            errors.put("trade",
                    "is not correct becouse its not exist, check your json file or database" +
                            "");
        }

        if (!isProducerNameValid(producerDto)) {
            errors.put("name", "is not correct: " + producerDto.getName());
        }


        Optional<Producer> producerOp =
                producerDao.getByNameAndCountryAndTrade
                        (producerDto.getName(),
                                producerDto.getCountryDto().getCity(),
                                producerDto.getTradeDto().getName());
        if (producerOp.isPresent()) {
            errors.put("producer", "is not correct becouse its exist");
        }


        return errors;
    }

    private boolean isProducerNameValid(ProducerDto producerDto) {
        return producerDto.getName() != null
                &&
                producerDto.getName().matches("[A-Z ]+");


    }
}




