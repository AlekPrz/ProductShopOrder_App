package com.app.validators;

import com.app.dto.CategoryDto;
import com.app.dto.CustomerDto;
import com.app.exceptions.MyException;
import com.app.model.Category;
import com.app.model.Customer;
import com.app.repository.CategoryDao;
import com.app.repository.CustomerDao;
import com.app.repository.impl.CategoryDaoImpl;
import com.app.repository.impl.CustomerDaoImpl;

import java.util.Map;
import java.util.Optional;

public class CategoryValidator extends AbstractValidator<CategoryDto> {
    private CategoryDao categoryDao = new CategoryDaoImpl();


    @Override
    public Map<String, String> validate(CategoryDto categoryDto) {

        errors.clear();

        if (categoryDto == null) {
            errors.put("category", "is null");
        }

        if (!isCategoryNameValid(categoryDto)) {
            errors.put("name or surname", "is not correct: " + categoryDto.getName());
        }

        Optional<Category> categoryOp = categoryDao.getbyName(categoryDto.getName());

        if (categoryOp.isPresent()) {
            errors.put("category", "is not correct becouse it's exist");
        }


        return errors;
    }

    private boolean isCategoryNameValid(CategoryDto categoryDto) {
        return categoryDto.getName() != null
                &&
                categoryDto.getName().matches("[A-Z ]+");
    }

}
