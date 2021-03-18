package com.app;

import com.app.dto.ProductDto;
import com.app.model.Errors;
import com.app.repository.*;
import com.app.repository.impl.*;
import com.app.service.MenuService;
import com.app.service.MyServiceImpl;

public class App1 {
    public static void main(String[] args) {


        CountryRepository countryRepository = new CountryRepositoryImpl();
        CustomerDao customerDao = new CustomerDaoImpl();
        ProducerDao producerDao = new ProducerDaoImpl();
        ProductDao productDao = new ProductDaoImpl();
        CategoryDao categoryDao = new CategoryDaoImpl();
        TradeDao tradeDao = new TradeDaoImpl();
        ShopDao shopDao = new ShopDaoImpl();
        StockDao stockDao = new StockDaoImpl();
        PaymentDao paymentDao = new PaymentDaoImpl();
        CustomerOrderDao customerOrderDao = new CustomerOrderDaoImpl();
        ErrorsDao errorsDao = new ErrorsDaoImpl();

        MyServiceImpl myService = new MyServiceImpl(
                countryRepository,
                customerDao,
                producerDao,
                productDao,
                categoryDao,
                tradeDao,
                shopDao,
                stockDao,
                paymentDao,
                customerOrderDao );

        MenuService menuService = new MenuService(myService, errorsDao );
        menuService.mainMenu();

    }
}
