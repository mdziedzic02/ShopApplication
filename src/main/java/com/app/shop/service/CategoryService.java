package com.app.shop.service;

import com.app.shop.dao.CategoryRepository;
import com.app.shop.dto.CategoryDto;
import com.app.shop.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public int add(CategoryDto categoryDto) {
        final Category category = new Category(categoryDto.getName());
        return categoryRepository.save(category).getId();
    }

    public Optional<CategoryDto> get(int id) {
        return categoryRepository.findById(id).map(category -> new CategoryDto(category.getName()));
    }

    public Page<CategoryDto> getAll(PageRequest pageRequest) {
        final Page<Category> categories = categoryRepository.findAll(pageRequest);
        final List<CategoryDto> categoryDtoList = categories.get().map(category -> new CategoryDto(category.getName())).collect(Collectors.toList());
        return new PageImpl<>(categoryDtoList, pageRequest, categories.getNumberOfElements());
    }

    public Optional<?> update(int id, CategoryDto categoryDto) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(categoryDto.getName());
                    categoryRepository.save(category);
                    return category;
                });
    }

    public Optional<?> remove(int id) {
        return categoryRepository.findById(id)
                .map(category -> {
                    categoryRepository.deleteById(id);
                    return category;
                });
    }

    public boolean exist(int id) {
        return categoryRepository.existsById(id);
    }
}
