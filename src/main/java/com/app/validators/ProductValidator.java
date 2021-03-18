package com.app.validators;

import com.app.dto.ProductDto;
import com.app.exceptions.MyException;
import com.app.model.Product;
import com.app.repository.CategoryDao;
import com.app.repository.ProducerDao;
import com.app.repository.ProductDao;
import com.app.repository.impl.CategoryDaoImpl;
import com.app.repository.impl.ProducerDaoImpl;
import com.app.repository.impl.ProductDaoImpl;

import java.util.Map;
import java.util.Optional;

public class ProductValidator extends AbstractValidator<ProductDto> {
    ProductDao productDao = new ProductDaoImpl();

    CategoryDao categoryDao = new CategoryDaoImpl();
    ProducerDao producerDao = new ProducerDaoImpl();

    @Override
    public Map<String, String> validate(ProductDto product) {

        errors.clear();

        if (product == null) {
            errors.put("object", "is null");
        }

        if (!isProductNameValid(product)) {
            System.out.println("JESTEM");
            errors.put("name", "is not correct: " + product.getName());
        }
        if(!categoryDao.getbyName(product.getName()).isPresent()){
            errors.put("category",
                    "is not correct becouse its not exist, check your json file or database" +
                            "");
        }


        Optional<Product> productOP =
                productDao.getByNameCategoryProducent(product.getName(), product.getCategoryDto().getName(), product.getProducerDto().getName());


        if (productOP.isPresent()) {
            errors.put("product", "is not correct becouse its exist");

        }


        return errors;
    }

    private boolean isProductNameValid(ProductDto product) {
        return product.getName() != null && product.getName().matches("[A-Z ]+");
    }
}
