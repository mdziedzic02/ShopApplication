package com.app.shop.service;

import com.app.shop.dao.CategoryRepository;
import com.app.shop.dao.ProductRepository;
import com.app.shop.dto.CategoryDto;
import com.app.shop.dto.ProductDto;
import com.app.shop.entity.Category;
import com.app.shop.entity.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;
    private final String NAME = "name1";
    private final String NAME2 = "name2";

    @Test
    public void shouldAddCategoryWhenDoesNotExist() {
        CategoryDto categoryDto = new CategoryDto(NAME);
        int identifier = categoryService.add(categoryDto);
        Optional<CategoryDto> categoryVerification = categoryService.get(identifier);
        var v = categoryVerification.get();

        assertEquals(categoryDto, v);
    }

    @Test
    public void shouldReturnCategoryWhenExist() {
        Category givenCategory = categoryRepository.save(new Category(NAME));
        Optional<CategoryDto> expectedDto = categoryService.get(givenCategory.getId());
        var v = expectedDto.get();

        assertTrue(categoryRepository.existsById(givenCategory.getId()));
        assertEquals(v.getName(), givenCategory.getName());
    }

    @Test
    public void shouldDeleteCategoryWhenExist() {
        CategoryDto categoryDto = new CategoryDto(NAME);
        int identifier = categoryService.add(categoryDto);

        categoryService.remove(identifier);

        String categoryExist = assertCategoryDoesNotExist(identifier);
        assertEquals("Category not found", categoryExist);
    }

    private String assertCategoryDoesNotExist(int identifier) {
        if(categoryService.exist(identifier)){
            return "Category exist";
        }else{
            return "Category not found";
        }
    }

    @Test
    public void shouldUpdateCategory() {
        CategoryDto categoryDto = new CategoryDto(NAME);
        int identifier = categoryService.add(categoryDto);

        categoryService.update(identifier, new CategoryDto(NAME2));
        Optional<CategoryDto> newCategoryDto = categoryService.get(identifier);
        var v = newCategoryDto.get();

        assertEquals(NAME2, v.getName());
    }
}
