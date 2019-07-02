package com.app.shop.controller;

import com.app.shop.dto.CategoryDto;
import com.app.shop.dto.EntityCreatedDto;
import com.app.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController{

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.PUT, value = "/category")
    public ResponseEntity<?> create(@RequestBody CategoryDto categoryDto) {
        final Integer id = categoryService.add(categoryDto);
        return new ResponseEntity<>(new EntityCreatedDto(id), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/category/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody CategoryDto categoryDto, @PathVariable int id) {
        return categoryService.update(id, categoryDto)
                .map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/category/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return categoryService.remove(id)
                .map(category -> new ResponseEntity<>(HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/category/{id}")
    public ResponseEntity<?> get(@PathVariable int id) {
        return categoryService.get(id)
                .map(categoryDto -> new ResponseEntity<>(categoryDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/category")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return new ResponseEntity<>(categoryService.getAll(PageRequest.of(page, pageSize)), HttpStatus.OK);
    }
}

