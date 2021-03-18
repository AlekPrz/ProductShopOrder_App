package com.app.service;

import com.app.conventer.*;
import com.app.dto.*;
import com.app.model.*;
import com.app.repository.connection.DbConeection;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class JsonService {

    private static JsonService jsonService_instance = null;


    private final Set<Country> countries;
    private final Set<Customer> customers;
    private final Set<Category> categories;
    private final Set<Producer> producers;
    private final Set<Trade> trades;
    private final Set<Product> products;

    public JsonService() {
        this.countries = new CountriesJsonConverter("country.json").fromJson().orElseThrow(() -> new RuntimeException("CountyrProblem")).getCountries();
        this.customers = new CustomerJsonConverter("customers.json").fromJson().orElseThrow(() -> new RuntimeException("CustomersProblem")).getCustomers();
        this.categories = new CategoriesJsonConverter("category.json").fromJson().orElseThrow(() -> new RuntimeException("CategoryProblem")).getCategories();
        this.producers = new ProducersJsonConverter("producers.json").fromJson().orElseThrow(() -> new RuntimeException("ProducersProblem")).getProducers();
        this.trades = new TradeJsonConverter("trade.json").fromJson().orElseThrow(() -> new RuntimeException("TradeProblem")).getTrades();
        this.products = new ProductJsonConverter("product.json").fromJson().orElseThrow(() -> new RuntimeException("ProductProblem")).getProducts();
    }

    public static JsonService getInstance() {
        if (jsonService_instance == null) {
            jsonService_instance = new JsonService();
        }
        return jsonService_instance;

    }


    public void printView(MyServiceImpl myService) {
        String password = "Alek";
        System.out.println("Give passsword");
        String givenPassword = UserDataService.takeString();

        if (givenPassword.equals(password)) {

            DbConeection.deleteAll();


            countries.forEach(p -> myService.addCountry(p.getCity()));

            customers.forEach(p -> myService.addCustomer(
                    CustomerDto.builder()
                            .name(p.getName())
                            .surname(p.getSurname())
                            .age(p.getAge())
                            .countryDto(ModelMapper.fromCountryToCountryDto(p.getCountry()))
                            .build()));

            categories.forEach(p -> myService.addCategory(
                    CategoryDto.builder().name(p.getName()).build()
            ));

            trades.forEach(p -> myService.addTrade(
                    TradeDto.builder()
                            .name(p.getName())
                            .build()
            ));

            producers.forEach(
                    p -> myService.addProducer(
                            ProducerDto.builder()
                                    .name(p.getName())
                                    .countryDto(ModelMapper.fromCountryToCountryDto(p.getCountry()))
                                    .tradeDto(ModelMapper.fromTradeToTradeDto(p.getTrade())).build()
                    )
            );
            products.forEach(p -> myService.addProduct(
                    ProductDto.builder()
                            .name(p.getName())
                            .price(p.getPrice())
                            .categoryDto(ModelMapper.fromCategoryToCategoryDto(p.getCategory()))
                            .producerDto(ModelMapper.fromProducerToProducerDto(p.getProducer()))
                            .build()
            ));
        }
        return;


    }


}
