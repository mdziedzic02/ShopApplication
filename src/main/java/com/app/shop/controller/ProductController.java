package com.app.shop.controller;

import com.app.shop.dto.EntityCreatedDto;
import com.app.shop.dto.ProductDto;
import com.app.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.PUT, value = "/products")
    public ResponseEntity<?> create(@Valid @RequestBody ProductDto productDto) {
        final Integer id = productService.add(productDto);
        return new ResponseEntity<>(new EntityCreatedDto(id), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/products/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody ProductDto productDto, @PathVariable int id) {
        return productService.update(id, productDto)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/products/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return productService.remove(id)
                .map(product -> new ResponseEntity<>(HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/products/{id}")
    public ResponseEntity<?> get(@PathVariable int id) {
        return productService.get(id)
                .map(productDto -> new ResponseEntity<>(productDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/products")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return new ResponseEntity<>(productService.getAll(PageRequest.of(page, pageSize)), HttpStatus.OK);
    }
}
