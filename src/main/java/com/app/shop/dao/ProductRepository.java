package com.app.shop.dao;


import com.app.shop.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends GenericRepository<Product> {

    boolean existsById(int id);

    Optional<Product> findById(int id);

}