package com.app.dto;

import com.app.model.*;

import java.util.ArrayList;
import java.util.HashSet;


public interface ModelMapper {

    static CategoryDto fromCategoryToCategoryDto(Category category) {
        return category == null ? null : CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    static Category fromCategoryDtoToCategory(CategoryDto categoryDto) {
        return categoryDto == null ? null : Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .products(new HashSet<>())
                .build();
    }

    static CountryDto fromCountryToCountryDto(Country country) {
        return country == null ? null : CountryDto.builder()
                .id(country.getId())
                .city(country.getCity())
                .build();
    }

    static Country fromCountryDtoToCountry(CountryDto countryDto) {
        return countryDto == null ? null : Country.builder()
                .id(countryDto.getId())
                .city(countryDto.getCity())
                .producers(new HashSet<>())
                .customer(new HashSet<>())
                .build();
    }


    static CustomerOrderDto fromCustomerToCustomerOrderDto(CustomerOrder customerOrder) {
        return customerOrder == null ? null :
                new CustomerOrderDto(
                        customerOrder.getId(),
                        customerOrder.getDate(),
                        customerOrder.getDiscount(),
                        customerOrder.getQuantity(),
                        fromCustomerToCustomerDto(customerOrder.getCustomer()),
                        fromPaymentToPaymentDto(customerOrder.getPayment()),
                        fromProductToProductDto(customerOrder.getProduct())
                );
    }


    static CustomerOrder fromCustomerOrderDtoToCustomer(CustomerOrderDto customerOrderDto) {
        return customerOrderDto == null ? null :
                new CustomerOrder(
                        customerOrderDto.getId(),
                        customerOrderDto.getDate(),
                        customerOrderDto.getDiscount(),
                        customerOrderDto.getQuantity(),
                        fromCustomerDtoToCustomer(customerOrderDto.getCustomerDto()),
                        fromPaymentDtoToPayment(customerOrderDto.getPaymentDto()),
                        fromProductDtoToProduct(customerOrderDto.getProductDto())
                );
    }


    static CustomerDto fromCustomerToCustomerDto(Customer customer) {
        return customer == null ? null :
                CustomerDto.builder()
                        .id(customer.getId())
                        .name(customer.getName())
                        .age(customer.getAge())
                        .surname(customer.getSurname())
                        .countryDto(fromCountryToCountryDto(customer.getCountry()))
                        .build();

    }

    static Customer fromCustomerDtoToCustomer(CustomerDto customerDto) {
        return customerDto == null ? null :
                Customer.builder()
                        .id(customerDto.getId())
                        .name(customerDto.getName())
                        .age(customerDto.getAge())
                        .surname(customerDto.getSurname())
                        .country(fromCountryDtoToCountry(customerDto.getCountryDto()))
                        .build();

    }

    static PaymentDto fromPaymentToPaymentDto(Payment payment) {
        return payment == null ? null :
                PaymentDto.builder()
                        .id(payment.getId())
                        .payment(payment.getPayment())
                        .build();
    }

    static Payment fromPaymentDtoToPayment(PaymentDto paymentDto) {
        return paymentDto == null ? null :
                Payment.builder()
                        .id(paymentDto.getId())
                        .payment(paymentDto.getPayment())
                        .customerOrders(new HashSet<>())
                        .build();
    }


    static TradeDto fromTradeToTradeDto(Trade trade) {
        return trade == null ? null : TradeDto.builder()
                .id(trade.getId())
                .name(trade.getName())
                .build();
    }

    static Trade fromTradeDtoToTrade(TradeDto tradeDto) {
        return tradeDto == null ? null : Trade.builder()
                .id(tradeDto.getId())
                .name(tradeDto.getName())
                .producers(new HashSet<>())
                .build();
    }

    static ProducerDto fromProducerToProducerDto(Producer producer) {
        return producer == null ? null :
                ProducerDto.builder()
                        .id(producer.getId())
                        .name(producer.getName())
                        .countryDto(fromCountryToCountryDto(producer.getCountry()))
                        .tradeDto(fromTradeToTradeDto(producer.getTrade()))
                        .build();
    }

    static Producer fromProducerDtoToProducer(ProducerDto producerDto) {
        return producerDto == null ? null :
                Producer.builder()
                        .id(producerDto.getId())
                        .name(producerDto.getName())
                        .country(fromCountryDtoToCountry(producerDto.getCountryDto()))
                        .trade(fromTradeDtoToTrade(producerDto.getTradeDto()))
                        .build();
    }


    static ProductDto fromProductToProductDto(Product product) {
        return product == null ? null : ProductDto.builder()
                /* product.getId(),
                 product.getName(),
                 product.getPrice(),
                 fromCategoryToCategoryDto(product.getCategory()),
                 fromProducerToProducerDto(product.getProducer()),
                 product.getComponents()*/
                .id(product.getId())
                .price(product.getPrice())
                .name(product.getName())
                .categoryDto(fromCategoryToCategoryDto(product.getCategory()))
                .components(product.getComponents())
                .producerDto(fromProducerToProducerDto(product.getProducer()))
                .build();


    }

    static Product fromProductDtoToProduct(ProductDto productDto) {
        return productDto == null ? null : Product.builder()
                .id(productDto.getId())
                .price(productDto.getPrice())
                .name(productDto.getName())
                .category(fromCategoryDtoToCategory(productDto.getCategoryDto()))
                .components(productDto.getComponents())
                .producer(fromProducerDtoToProducer(productDto.getProducerDto()))
                .stocks(new HashSet<>())
                .customerOrders(new HashSet<>())
                .build();


    }

    static ShopDto fromShopToShopDto(Shop shop) {
        return shop == null ? null : ShopDto.builder()
                .id(shop.getId())
                .name(shop.getName())
                .countryDto(fromCountryToCountryDto(shop.getCountry()))
                .build();
    }

    static Shop fromShopDtoToShop(ShopDto shopDto) {
        return shopDto == null ? null : Shop.builder()
                .id(shopDto.getId())
                .name(shopDto.getName())
                .country(fromCountryDtoToCountry(shopDto.getCountryDto()))
                .build();

    }

    static StockDto fromStocktoToStockDto(Stock stock) {
        return stock == null ? null : StockDto.builder()
                .id(stock.getId())
                .productDto(fromProductToProductDto(stock.getProduct()))
                .shopDto(fromShopToShopDto(stock.getShop()))
                .quantity(stock.getQuantity())
                .build();


    }


    static Stock fromStockDtoToStock(StockDto stockDto) {
        return stockDto == null ? null : Stock.builder()
                .id(stockDto.getId())
                .product(fromProductDtoToProduct(stockDto.getProductDto()))
                .shop(fromShopDtoToShop(stockDto.getShopDto()))
                .quantity(stockDto.getQuantity())
                .build();

    }


}

