package com.app.validators;

import com.app.dto.CountryDto;
import com.app.dto.CustomerDto;
import com.app.dto.ModelMapper;
import com.app.exceptions.MyException;
import com.app.model.Customer;
import com.app.repository.CountryRepository;
import com.app.repository.CustomerDao;
import com.app.repository.impl.CountryRepositoryImpl;
import com.app.repository.impl.CustomerDaoImpl;

import java.util.Map;
import java.util.Optional;

public class CustomerValidator extends AbstractValidator<CustomerDto> {
    private CustomerDao customerDao = new CustomerDaoImpl();
    private CountryRepository countryRepository = new CountryRepositoryImpl();


    @Override
    public Map<String, String> validate(CustomerDto customerDto) {

        errors.clear();


        CountryDto countryDto = ModelMapper.fromCountryToCountryDto(
                countryRepository.getbyName(customerDto.getCountryDto().getCity()).orElse(null));

        if (customerDto == null) {
            errors.put("customer", "is null");
        }
        if (!countryRepository.getbyName(customerDto.getCountryDto().getCity()).isPresent()) {
            errors.put("country",
                    "is not correct becouse its not exist, check your json file or database" +
                            "");
        }else{


            customerDto.setCountryDto(countryDto);


        }

        if (!isCustomerNameValid(customerDto)) {
            errors.put("name or surname", "is not correct: " + customerDto.getName() + "," + customerDto.getSurname());
        }
        if (!isAgeValid(customerDto)) {
            errors.put("age", "is not correct: " + customerDto.getAge());
        }
        Optional<Customer> customerOp = customerDao.getByNameSurnameAndCountry
                (customerDto.getName(), customerDto.getSurname(), customerDto.getCountryDto().getCity());

        if (customerOp.isPresent()) {
            errors.put("customer", "is not correct becouse its exist");
        }


        return errors;
    }

    private boolean isCustomerNameValid(CustomerDto customerDto) {
        return customerDto.getName() != null
                &&
                customerDto.getName().matches("[A-Z ]+")
                &&
                customerDto.getSurname().matches("[A-Z ]+")
                &&
                customerDto.getSurname() != null;
    }

    private boolean isAgeValid(CustomerDto customerDto) {
        return customerDto.getAge() >= 18;
    }
}
