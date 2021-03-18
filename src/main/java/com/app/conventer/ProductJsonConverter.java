package com.app.conventer;

import com.app.conventer.Store.CustomerStore;
import com.app.conventer.Store.ProducerStore;
import com.app.conventer.Store.ProductStore;

public class ProductJsonConverter extends converter<ProductStore> {
    public ProductJsonConverter(String fileName) {
        super(fileName);
    }
}
