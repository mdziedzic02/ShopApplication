package com.app.shop.data;

import com.app.shop.dao.ProductRepository;
import com.app.shop.entity.Category;
import com.app.shop.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductDataService {

    @Autowired
    private ProductRepository productRepository;

    public Product create(Category category, String name, BigDecimal price, Integer quantity) {
        return productRepository.save(new Product(name, price, quantity, category));
    }

}
