package com.app.shop.service;


import com.app.shop.dao.ProductRepository;
import com.app.shop.data.CategoryDataService;
import com.app.shop.data.ProductDataService;
import com.app.shop.dto.ProductDto;
import com.app.shop.entity.Category;
import com.app.shop.entity.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryDataService categoryDataService;

    private final String PRODUCT_NAME_1 = "name1";
    private final String PRODUCT_NAME_2 = "name2";
    private final BigDecimal PRODUCT_PRICE = BigDecimal.ONE;
    private final Integer PRODUCT_QUANTITY = 1;

    @Test
    public void shouldAddProductWhenDoesNotExist() {
        final var category = categoryDataService.create("Category_1");
        ProductDto productDto = new ProductDto(PRODUCT_NAME_1, PRODUCT_PRICE, PRODUCT_QUANTITY, category.getId());
        int identifier = productService.add(productDto);
        Optional<ProductDto> productVerification = productService.get(identifier);
        var v = productVerification.get();

        assertEquals(productDto, v);
    }

    @Test
    public void shouldReturnProductWhenExist() {
        final var category = categoryDataService.create("Category_1");
        Product givenProduct = productRepository.save(new Product(PRODUCT_NAME_1, PRODUCT_PRICE,PRODUCT_QUANTITY,category));
        Optional<ProductDto> expectedDto = productService.get(givenProduct.getId());
        var v = expectedDto.get();

        assertTrue(productRepository.existsById(givenProduct.getId()));
        assertEquals(v.getName(), givenProduct.getName());
    }

    @Test
    public void shouldDeleteProductWhenExist() {
        final var category = categoryDataService.create("Category_1");
        ProductDto productDto = new ProductDto(PRODUCT_NAME_1, PRODUCT_PRICE, PRODUCT_QUANTITY, category.getId());
        int identifier = productService.add(productDto);

        productService.remove(identifier);

        String productExist = assertProductDoesNotExist(identifier);
        assertEquals("Product not found", productExist);
    }

    private String assertProductDoesNotExist(int identifier) {
        if(productService.exist(identifier)){
            return "Product exist";
        }else{
            return "Product not found";
        }
    }

    @Test
    public void shouldUpdateProduct() {
        final var category = categoryDataService.create("Category_1");
        ProductDto productDto = new ProductDto(PRODUCT_NAME_1, PRODUCT_PRICE, PRODUCT_QUANTITY, category.getId());
        int identifier = productService.add(productDto);

        productService.update(identifier, new ProductDto(PRODUCT_NAME_2, PRODUCT_PRICE, PRODUCT_QUANTITY, category.getId()));
        Optional<ProductDto> newProductDto = productService.get(identifier);
        var v = newProductDto.get();

        assertEquals(PRODUCT_NAME_2, v.getName());
    }
}
