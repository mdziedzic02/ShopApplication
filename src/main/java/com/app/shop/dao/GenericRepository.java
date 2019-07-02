package com.app.shop.dao;

import com.app.shop.exception.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface GenericRepository<T> extends CrudRepository<T, Integer>, JpaSpecificationExecutor<T>, PagingAndSortingRepository<T, Integer> {

    default T findOrThrow(Integer id) {
        return findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(getClass().getGenericSuperclass().getTypeName(), String.valueOf(id));
        });
    }

}
