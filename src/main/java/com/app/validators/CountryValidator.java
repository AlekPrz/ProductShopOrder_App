package com.app.validators;

import com.app.dto.CountryDto;
import com.app.model.Country;
import com.app.repository.CountryRepository;
import com.app.repository.CustomerDao;
import com.app.repository.impl.CountryRepositoryImpl;
import com.app.repository.impl.CustomerDaoImpl;

import java.util.Map;
import java.util.Optional;

public class CountryValidator extends AbstractValidator<CountryDto> {
    private CountryRepository countryRepository = new CountryRepositoryImpl();

    @Override
    public Map<String, String> validate(CountryDto countryDto) {

        errors.clear();

        Optional<Country> countryDtoOptional = countryRepository.getbyName(countryDto.getCity());


        if (!countryDtoOptional.isPresent()) {
            errors.put("country",
                    "is not correct becouse its not exist, check your json file or database" +
                            "");
        }

        return errors;
    }
}