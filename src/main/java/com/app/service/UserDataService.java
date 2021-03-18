package com.app.service;

import com.app.dto.ProductDto;
import com.app.exceptions.MyException;
import com.app.model.*;
import com.app.repository.*;
import com.app.repository.impl.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserDataService {

    private UserDataService() {
    }

    private static Scanner scanner = new Scanner(System.in);

    public static int getInt() {

        String text = scanner.nextLine();
        if (!text.matches("\\d+")) {
            throw new MyException("Int value is not valid: " + text);
        }
        return Integer.parseInt(text);
    }

    public static String takeString() {
        String text = scanner.nextLine();


        return text;
    }

    public static List<Country> getAllCountry() {
        CountryRepository countryRepository = new CountryRepositoryImpl();
        return countryRepository
                .findAll();
    }

    public static Country getCountryForUser(String name) {
        CountryRepository countryRepository = new CountryRepositoryImpl();
        List<Country> countries = countryRepository.findAll();

        System.out.println("Choose a number of Country which u want too add for " + name);
        for (Country tmp : countries) {
            System.out.println(countries.indexOf(tmp) + "-" + tmp.getCity());
        }

        Integer w = UserDataService.getInt();
        System.out.println(countries.size());
        if (w >= countries.size()) {
            throw new MyException("There is no Country with this index try again");
        }


        return countries.get(w);
    }

    public static Trade getTradeForUser() {
        TradeDao tradeDao = new TradeDaoImpl();
        List<Trade> trades = tradeDao.findAll();

        System.out.println("Choose a number of Trade which u want too add");
        for (Trade tmp : trades) {
            System.out.println(trades.indexOf(tmp) + "-" + tmp.getName());
        }

        Integer w = UserDataService.getInt();
        System.out.println(trades.size());
        if (w >= trades.size()) {
            throw new MyException("There is no Trade with this index try again");
        }


        return trades.get(w);
    }

    public static Category getCategoryForUser() {
        CategoryDao categoryDao = new CategoryDaoImpl();
        List<Category> categories = categoryDao.findAll();

        System.out.println("Choose a number of Category which u want too add");
        for (Category tmp : categories) {
            System.out.println(categories.indexOf(tmp) + "-" + tmp.getName());
        }

        Integer w = UserDataService.getInt();
        System.out.println(categories.size());
        if (w >= categories.size()) {
            throw new MyException("There is no Category with this index try again");
        }


        return categories.get(w);
    }

    public static Producer getProducentForUser() {
        ProducerDao producerDao = new ProducerDaoImpl();
        List<Producer> producers = producerDao.findAll();

        System.out.println("Choose a number of Producer which u want too add");
        for (Producer tmp : producers) {
            System.out.println(producers.indexOf(tmp) + "-" + tmp.getName());
        }

        Integer w = UserDataService.getInt();
        System.out.println(producers.size());
        if (w >= producers.size()) {
            throw new MyException("There is no Producer with this index try again");
        }


        return producers.get(w);
    }

    public static Shop getShopForUser() {
        ShopDao shopDao = new ShopDaoImpl();
        List<Shop> shops = shopDao.findAll();

        System.out.println("Choose a number of Shop which u want too add");
        for (Shop tmp : shops) {
            System.out.println(shops.indexOf(tmp) + "-" + tmp.getName());
        }

        Integer w = UserDataService.getInt();
        System.out.println(shops.size());
        if (w >= shops.size()) {
            throw new MyException("There is no Shop with this index try again");
        }


        return shops.get(w);
    }

    public static Customer getCustomerForUser() {
        CustomerDao customerDao = new CustomerDaoImpl();
        List<Customer> customers = customerDao.findAll();

        for (Customer tmp : customers) {
            System.out.println(customers.indexOf(tmp) + "-" + tmp.getName());
        }

        Integer w = UserDataService.getInt();
        System.out.println(customers.size());
        if (w >= customers.size()) {
            throw new MyException("There is no Shop with this index try again");
        }


        return customers.get(w);
    }

    public static Product getProductForUser() {
        ProductDao productDao = new ProductDaoImpl();
        List<Product> products = productDao.findAll();

        System.out.println("Choose a number of Product which u want too add");
        for (Product tmp : products) {
            System.out.println(products.indexOf(tmp) + "-" + tmp.getName());
        }

        Integer w = UserDataService.getInt();
        System.out.println(products.size());
        if (w >= products.size()) {
            throw new MyException("There is no Product with this index try again");
        }


        return products.get(w);
    }


    public static List<Payment> payments() {
        PaymentDao paymentDao = new PaymentDaoImpl();

        return paymentDao.findAll();
    }

    public static Country getCountryById(Long id) {
        CountryRepository countryRepository = new CountryRepositoryImpl();
        return countryRepository.findOne(id).orElseThrow(null);
    }

    public static void close() {

        if (scanner != null) {
            scanner.close();
            scanner = null;
        }


    }
}
