package com.app.service;

import com.app.dto.info.ProductInfo;
import com.app.repository.*;
import com.app.dto.*;
import com.app.exceptions.MyException;
import com.app.model.*;
import com.app.validators.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class MyServiceImpl {
    private String className = this.getClass().getSimpleName();

    private final CountryRepository countryRepository;
    private final CustomerDao customerDao;
    private final ProducerDao producerDao;
    private final ProductDao productDao;
    private final CategoryDao categoryDao;
    private final TradeDao tradeDao;
    private final ShopDao shopDao;
    private final StockDao stockDao;
    private final PaymentDao paymentDao;
    private final CustomerOrderDao customerOrder;

    public MyServiceImpl(
            CountryRepository countryRepository,
            CustomerDao customerDao,
            ProducerDao producerDao,
            ProductDao productDao,
            CategoryDao categoryDao,
            TradeDao tradeDao,
            ShopDao shopDao,
            StockDao stockDao,
            PaymentDao paymentDao,
            CustomerOrderDao customerOrder) {
        this.countryRepository = countryRepository;
        this.customerDao = customerDao;
        this.producerDao = producerDao;
        this.productDao = productDao;
        this.categoryDao = categoryDao;
        this.tradeDao = tradeDao;
        this.shopDao = shopDao;
        this.stockDao = stockDao;
        this.paymentDao = paymentDao;
        this.customerOrder = customerOrder;
    }

    public CategoryDto addCategory(CategoryDto categoryDto) throws MyException {

        CategoryValidator categoryValidator = new CategoryValidator();
        Map<String, String> errors = categoryValidator.validate(categoryDto);

        if (!errors.isEmpty()) {
            String errorMessage = errors.entrySet().stream().map(e -> e.getKey() + ": " + e.getValue()).collect(Collectors.joining(", "));
            throw new MyException("Customer validation error: " + errorMessage);
        }

        Category categoryFromDb =
                categoryDao.addOrUpdate(ModelMapper.fromCategoryDtoToCategory(categoryDto))
                        .orElseThrow(() -> new MyException("add category exception"));


        return ModelMapper.fromCategoryToCategoryDto(categoryFromDb);

    }


    public CustomerDto addCustomer(CustomerDto customerDto) throws MyException {


        CustomerValidator customerValidator = new CustomerValidator();
        Map<String, String> errors = customerValidator.validate(customerDto);


        if (!errors.isEmpty()) {
            String errorMessage = errors.entrySet().stream().map(e -> e.getKey() + ": " + e.getValue()).collect(Collectors.joining(", "));
            throw new MyException("Customer validation error: " + errorMessage);
        }

        System.out.println("Customer added");
        Customer customerFromDb = customerDao
                .addOrUpdate(ModelMapper.fromCustomerDtoToCustomer(customerDto))
                .orElseThrow(() -> new MyException("add customer exception"));

        return ModelMapper.fromCustomerToCustomerDto(customerFromDb);
    }

    public ProducerDto addProducer(ProducerDto producerDto) {
        ProducerValidator producerValidator = new ProducerValidator();
        Map<String, String> errors = producerValidator.validate(producerDto);


        if (!errors.isEmpty()) {
            String errorMessage = errors.entrySet().stream().map(e -> e.getKey() + ": " + e.getValue()).collect(Collectors.joining(", "));
            throw new MyException("Producer validation error: " + errorMessage);
        }


        System.out.println("Producer added");
        Producer producerFromDb = producerDao.addOrUpdate(ModelMapper.fromProducerDtoToProducer(producerDto)).orElseThrow(() -> new MyException("producer exception"));

        return ModelMapper.fromProducerToProducerDto(producerFromDb);
    }

    public PaymentDto addPayment(PaymentDto paymentDto) {


        Optional<Payment> paymentOp = paymentDao.getbyName(EPayment.valueOf(paymentDto.getPayment().name()));
        if (paymentOp.isPresent()) {
            throw new MyException("There is payment like that ");
        }

        Payment paymentFromDb = paymentDao.addOrUpdate(ModelMapper.fromPaymentDtoToPayment(paymentDto)).orElseThrow(() -> new MyException("payment exception"));
        return ModelMapper.fromPaymentToPaymentDto(paymentFromDb);
    }

    public TradeDto addTrade(TradeDto tradeDto) {
        TradeValidator tradeValidator = new TradeValidator();
        Map<String, String> errors = tradeValidator.validate(tradeDto);


        if (!errors.isEmpty()) {
            String errorMessage = errors.entrySet().stream().map(e -> e.getKey() + ": " + e.getValue()).collect(Collectors.joining(", "));
            throw new MyException("Shop validation error: " + errorMessage);
        }

        Trade tradeFromDb = tradeDao.addOrUpdate(ModelMapper.fromTradeDtoToTrade(tradeDto)).orElseThrow(() -> new MyException("Trade problem"));
        return ModelMapper.fromTradeToTradeDto(tradeFromDb);

    }


    public ShopDto addShop(ShopDto shopDto) {
        ShopValidator shopValidator = new ShopValidator();
        Map<String, String> errors = shopValidator.validate(shopDto);


        if (!errors.isEmpty()) {
            String errorMessage = errors.entrySet().stream().map(e -> e.getKey() + ": " + e.getValue()).collect(Collectors.joining(", "));
            throw new MyException("Shop validation error: " + errorMessage);
        }
        Shop shopFromDb = shopDao.addOrUpdate(ModelMapper.fromShopDtoToShop(shopDto)).orElseThrow(() -> new MyException("Shop problem"));
        System.out.println("Shop added");
        return ModelMapper.fromShopToShopDto(shopFromDb);
    }

    public void addCountry(String country) {
        Optional<Country> countryOP = countryRepository.getbyName(country);
        if (countryOP.isPresent()) {
            throw new MyException("COUNTRY EXIST");

        }
        countryRepository.addOrUpdate(ModelMapper.fromCountryDtoToCountry(new CountryDto(null, country)));
        System.out.println("COUNTRY ADDED");
    }


    // public void addProduct(String productName, BigDecimal price, String categoryName, String producentName, String producentCountryName, List<Guarantee_components> componentsList) {
    public ProductDto addProduct(ProductDto productDto) {


        ProductValidator productValidator = new ProductValidator();
        Map<String, String> errors = productValidator.validate(productDto);
        if (!errors.isEmpty()) {
            String errorMessage = errors.entrySet().stream().map(e -> e.getKey() + ": " + e.getValue()).collect(Collectors.joining(", "));
            throw new MyException("Product validation error: " + errorMessage);
        }


        Product productFromDb = productDao.addOrUpdate(ModelMapper.fromProductDtoToProduct(productDto)).orElseThrow(() -> new MyException("product error"));

        return ModelMapper.fromProductToProductDto(productFromDb);

    }


    public void addOrder(CustomerOrderDto customerOrderDto) {


        customerOrder.addOrUpdate(ModelMapper.fromCustomerOrderDtoToCustomer(customerOrderDto));
    }

    public StockDto addStock(StockDto stockDto) {


        Optional<Stock> stock = stockDao.getByProductandShop
                (stockDto.getProductDto().getName(), stockDto.getShopDto().getName());

        Stock stockFromDb = null;
        if (stock.isPresent()) {
            System.out.println("JESTEM TU");
            System.out.println(stock.get().getId());
            System.out.println("------------");
            stockFromDb = stockDao.addOrUpdate(ModelMapper.fromStockDtoToStock(StockDto.builder()
                    .id(stock.get().getId())
                    .productDto(stockDto.getProductDto())
                    .shopDto(stockDto.getShopDto())
                    .quantity(stock.get().getQuantity() + stockDto.getQuantity())
                    .build())).orElseThrow(() -> new MyException("stock error"));
            return ModelMapper.fromStocktoToStockDto(stockFromDb);
        }

        System.out.println("NIE MA");
        stockFromDb = stockDao.addOrUpdate(ModelMapper.fromStockDtoToStock(stockDto)).orElseThrow(() -> new MyException("stock error v2"));
        return ModelMapper.fromStocktoToStockDto(stockFromDb);

    }

/*
    Pobranie z bazy danych pełnej informacji na temat produktów o największej cenie w każdej kategorii.
     Informacja zawiera nazwę produktu, cenę produktu, kategorię produktu, nazwę producenta, kraj w którym wyprodukowano produkt oraz ilość pozycji, w
     którym zamawiano ten produkt.
*/


    public List<ProductInfo> getProducts3a() {
        return productDao.findAll().stream().collect(Collectors.groupingBy(Product::getCategory,
                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Product::getPrice)), p -> p.orElse(null))))
                .values().stream()
                .map(o -> new ProductInfo(o.getName(), o.getPrice(), o.getCategory().getName(), o.getProducer().getName(), o.getProducer().getCountry().getCity()))
                .collect(Collectors.toList());

    }


    /*
        Pobranie z bazy danych listy wszystkich produktów, które zamawiane były przez klientów pochodzących z kraju o nazwie podanej przez użytkownika i wieku z przedziału
        określanego przez użytkownika. Produkty powinny być posortowane malejąco według ceny. Informacja zawiera nazwę produktu, cenę produktu, kategorię produktu,
        nazwę producenta oraz kraj w którym wyprodukowano produkt.*/
    public List<Product> getProducts3b(String name, Integer age1, Integer age2) {
        return customerOrder
                .findAll()
                .stream()
                .filter(s -> s.getCustomer().getCountry().getCity().equals(name) && s.getCustomer().getAge() >= age1 && s.getCustomer().getAge() <= age2)
                .map(s -> s.getProduct())
                .sorted(Comparator.comparing(Product::getPrice, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }


    public List<Shop> getShops3c() {


        /*Pobranie z bazy danych listy sklepów, które w magazynie
        posiadają produkty, których kraj pochodzenia jest inny niż
        kraje, w których występują oddziały sklepu.*/

        return stockDao
                .findAll()
                .stream()
                .filter(s -> !s.getProduct().getProducer().getCountry().equals(s.getShop().getCountry()))
                .map(s -> s.getShop())
                .collect(Collectors.toList());
    }

    /*Pobranie z bazy danych producentów o nazwie branży podanej przez użytkownika,
    którzy wyprodukowali produkty o łącznej ilości sztuk większej niż liczba podana przez użytkownika.*/
    public List<Producer> getProducers3d(String tradeName, Integer quantity) {
        return stockDao
                .findAll()
                .stream()
                .filter(s -> s.getProduct().getProducer().getTrade().getName().equals(tradeName))
                .collect(Collectors.groupingBy(s -> s.getProduct().getProducer()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().stream().collect(Collectors.summarizingInt(s -> s.getQuantity())).getSum()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > quantity)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<CustomerOrder> getOrdes3e(LocalDate start, LocalDate end, BigDecimal price) {
        return customerOrder.findAll().stream()
                .filter(s -> s.getDate().compareTo(start) > 0 && s.getDate().compareTo(end) < 1 &&
                        s.getProduct().getPrice().compareTo(price) < 0)
                .collect(Collectors.toList());
    }
}

