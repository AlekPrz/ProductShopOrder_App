package com.app.conventer.Store;

import com.app.model.Category;
import lombok.Data;

import java.util.Set;
@Data
public class CategoriesStore {
    Set<Category> categories;
}
