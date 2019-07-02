package com.app.shop.service;

import com.app.shop.dao.CategoryRepository;
import com.app.shop.dao.ProductRepository;
import com.app.shop.dto.ProductDto;
import com.app.shop.entity.Category;
import com.app.shop.entity.Product;
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
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public int add(ProductDto productDto) {
        final Category category = categoryRepository.findOrThrow(productDto.getCategoryId());

        final Product product = new Product(
                productDto.getName(),
                productDto.getPrice(),
                productDto.getQuantity(),
                category
        );
        return productRepository.save(product).getId();
    }

    public Optional<ProductDto> get(int id) {
        return productRepository.findById(id).map(product -> new ProductDto(
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory().getId()
        ));
    }

    public Page<ProductDto> getAll(PageRequest pageRequest) {
        final Page<Product> products = productRepository.findAll(pageRequest);
        final List<ProductDto> productDtoList = products.get().map(product -> new ProductDto(
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory().getId()
        )).collect(Collectors.toList());
        return new PageImpl<>(productDtoList, pageRequest, products.getNumberOfElements());
    }

    public Optional<?> update(int id, ProductDto productDto) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(productDto.getName());
                    productRepository.save(product);
                    return product;
                });
    }

    public Optional<?> remove(int id) {
        return productRepository.findById(id)
                .map(product -> {
                    productRepository.deleteById(id);
                    return product;
                });
    }

    public boolean exist(int id) {
        return productRepository.existsById(id);
    }
}
