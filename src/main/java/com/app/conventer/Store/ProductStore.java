package com.app.conventer.Store;

import com.app.model.Producer;
import com.app.model.Product;
import lombok.Data;

import java.util.Set;

@Data
public class ProductStore {
    private Set<Product> products;

}
