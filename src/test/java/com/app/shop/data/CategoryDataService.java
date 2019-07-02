package com.app.shop.data;

import com.app.shop.dao.CategoryRepository;
import com.app.shop.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryDataService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category create(String name) {
        final var category = new Category();
        category.setName(name);
        return categoryRepository.save(category);
    }

}
