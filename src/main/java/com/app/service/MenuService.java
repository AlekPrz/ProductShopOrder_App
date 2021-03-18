package com.app.service;

import com.app.dto.*;
import com.app.model.*;
import com.app.repository.ErrorsDao;
import com.app.repository.connection.DbConeection;
import com.app.exceptions.MyException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuService {

    private final MyServiceImpl myService;
    private final ErrorsDao errorsdao;

    public MenuService(MyServiceImpl myService, ErrorsDao errorsdao) {
        this.myService = myService;
        this.errorsdao = errorsdao;
    }


    public void mainMenu() {
        while (true) {
            try {
                int mainMenuOption = printMainMenu();


                switch (mainMenuOption) {
                    case 1:
                        int addMenuOption = printAddMenu();
                        switch (addMenuOption) {
                            case 1:
                                option1AddMenu();
                                mainMenu();
                            case 2:
                                option2AddMenu();
                                mainMenu();
                            case 3:
                                option3AddMenu();
                                mainMenu();
                            case 4:
                                option4AddMenu();
                                mainMenu();
                            case 5:
                                option5AddMenu();
                                mainMenu();
                            case 6:
                                option6AddMenu();
                                mainMenu();
                            case 7:
                                option7AddMenu();
                                mainMenu();
                            case 8:
                                option8AddMenu();
                                mainMenu();
                            case 9:
                                option9AddMenu();
                                mainMenu();
                            case 10:
                                option10AddMenu();
                                mainMenu();
                            case 11:
                                mainMenu();
                            case 12:
                                System.out.println("End of program");
                                UserDataService.close();
                                DbConeection.getInstance().close();
                                return;
                        }
                    case 2:
                        StuffInfoService stuffInfoService = new StuffInfoService(myService);

                        int viewMenuOption = stuffInfoService.printViewMenu();
                        switch (viewMenuOption) {
                            case 1:
                                stuffInfoService.option1ViewMenu();
                                mainMenu();
                            case 2:
                                stuffInfoService.option2ViewMenu();
                                mainMenu();
                            case 3:
                                UserDataService.close();
                                System.out.println("Zakończenie programu");
                                return;


                        }
                    case 3:

                        JsonService jsonService = JsonService.getInstance();
                        jsonService.printView(myService);
                        mainMenu();

                    case 4:
                        System.out.println("End of program");
                        UserDataService.close();
                        DbConeection.getInstance().close();
                        return;
                    default:
                        System.out.println("Option number is not valid");
                }

            } catch (MyException e) {
                System.out.println("\n\n-------------------- Exception ---------------------");
                System.out.println(e.getMessage());
                System.out.println("----------------------------------------------------\n\n");
                errorsdao.add(Errors.builder()
                        .message(e.getMessage())
                        .dateTime(LocalDateTime.now())
                        .build());
            }
        }
    }


    private int printMainMenu() {
        System.out.println("Choice option");
        System.out.println("1 - Add new stuff");
        System.out.println("2 - Get info about stuff");
        System.out.println("3 - Delete all data and get from jsonFiles");
        System.out.println("4 - Close program");
        return UserDataService.getInt();
    }

    private int printAddMenu() {
        System.out.println("Choose option");
        System.out.println("1 - Add Client");
        System.out.println("2 - Add Shop");
        System.out.println("3 - Add Producer");
        System.out.println("4 - Add Product");
        System.out.println("5 - Dodanie nowej pozycji w magazynie");
        System.out.println("6 - Dodanie zamówienia");
        System.out.println("7 - Add Country");
        System.out.println("8 - Add Payment");
        System.out.println("9 - Add Category");
        System.out.println("10 - Add Trade");
        System.out.println("11 - Back");
        System.out.println("12 - End");

        return UserDataService.getInt();

    }


    public void option1AddMenu() {
        System.out.println("Give the name");
        String name = UserDataService.takeString();
        System.out.println("Give the surname");
        String surname = UserDataService.takeString();
        System.out.println("Give the age");
        Integer age = UserDataService.getInt();


        System.out.println("Choose a number of Country which u want too add");


        List<Country> countries = UserDataService.getAllCountry();
        for (Country tmp : countries) {
            System.out.println(countries.indexOf(tmp) + "-" + tmp.getCity());
        }


        Integer w = UserDataService.getInt();
        if (w > countries.size()) {
            throw new MyException("There is no Country with this index try again");
        }


        Country country = countries.get(w);


        myService.addCustomer(
                CustomerDto.builder()
                        .name(name)
                        .surname(surname)
                        .age(age)
                        .countryDto(ModelMapper.fromCountryToCountryDto(country))
                        .build());


    }

    public void option2AddMenu() {

        System.out.println("Give the name of the shop");
        String shopName = UserDataService.takeString();


        Country country = UserDataService.getCountryForUser(shopName);


        myService.addShop(ModelMapper.fromShopToShopDto(Shop.builder().name(shopName).country(country).build()));

    }

    public void option3AddMenu() {

        System.out.println("Give the name of producer");
        String producerName = UserDataService.takeString();

        Country country = UserDataService.getCountryForUser(producerName);
        Trade trade = UserDataService.getTradeForUser();

        myService.addProducer(ModelMapper.fromProducerToProducerDto(Producer.builder().name(producerName).country(country).trade(trade).build()));

    }

    public void option4AddMenu() {
        System.out.println("Give the name of product");

        String nameOfProduct = UserDataService.takeString();

        System.out.println("Give the price of Product");

        BigDecimal bigDecimal = new BigDecimal(UserDataService.getInt());

        System.out.println("Choose nr of Category");

        Category category = UserDataService.getCategoryForUser();

        System.out.println("Choose nr of Producer");

        Producer producer = UserDataService.getProducentForUser();


        myService.addProduct(ModelMapper.fromProductToProductDto(
                Product.builder().name(nameOfProduct)
                        .category(category)
                        .price(bigDecimal)
                        .producer(producer)
                        .components(new ArrayList<>()).build()
        ));


    }

    public void option5AddMenu() {


        System.out.println("Give the quantity of Product");


        Integer quantity = UserDataService.getInt();

        System.out.println("Choose nr of the Category");

        Category category = UserDataService.getCategoryForUser();

        System.out.println("Choose nr of Shop");

        Shop shop = UserDataService.getShopForUser();

        System.out.println("Choose nr of Product");

        Product product = UserDataService.getProductForUser();


        myService.addStock(ModelMapper.fromStocktoToStockDto(
                Stock.builder().product(product).quantity(quantity).shop(shop).build()));


    }

    public void option6AddMenu() {


        System.out.println("Choose customer");
        Customer customer = UserDataService.getCustomerForUser();


        System.out.println("Choose product");
        Product product = UserDataService.getProductForUser();

        System.out.println("Give the quantity");
        Integer quantity = UserDataService.getInt();

        System.out.println("Give the discount ");
        BigDecimal discount = new BigDecimal(UserDataService.getInt());


        System.out.println("Choose payment");

        List<EPayment> ePayments = Arrays.asList(EPayment.values());


        for (EPayment tmp : ePayments) {
            System.out.println(ePayments.indexOf(tmp) + " " + tmp);
        }

        Integer w = UserDataService.getInt();
        if (w > ePayments.size()) {
            throw new MyException("There is no Country with this index try again");
        }

        Payment payment = Payment.builder().payment(ePayments.get(w)).build();


        myService.addOrder(ModelMapper.fromCustomerToCustomerOrderDto(CustomerOrder.builder()
                .customer(customer).product(product).discount(discount).quantity(quantity).date(LocalDate.now()).payment(payment).build()));


    }

    public void option7AddMenu() {
        System.out.println("Podaj nazwe kraju)");
        String text = UserDataService.takeString();
        myService.addCountry(text);
    }

    public void option8AddMenu() {


        List<EPayment> ePayments = Arrays.asList(EPayment.values());


        for (EPayment tmp : ePayments) {
            System.out.println(ePayments.indexOf(tmp) + " " + tmp);
        }

        Integer w = UserDataService.getInt();
        if (w > ePayments.size()) {
            throw new MyException("There is no Country with this index try again");
        }

        Payment payment = Payment.builder().payment(ePayments.get(w)).build();

        myService.addPayment(ModelMapper.fromPaymentToPaymentDto(payment));

    }

    public void option9AddMenu() {

        System.out.println("Give the name of category");
        String categoryName = UserDataService.takeString();


        myService.addCategory(CategoryDto.builder().name(categoryName).build());
    }

    public void option10AddMenu() {

        System.out.println("Give the name of trade");
        String tradeName = UserDataService.takeString();


        myService.addTrade(ModelMapper.fromTradeToTradeDto(Trade.builder().name(tradeName).build()));
    }


}

