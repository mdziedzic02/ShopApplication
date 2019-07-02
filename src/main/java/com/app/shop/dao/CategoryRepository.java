package com.app.shop.dao;

import com.app.shop.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends GenericRepository<Category> {

//    @Query(SELECT COUNT(p.name) AS "ProductsCount", c.name AS "CategoryName" FROM product p
//           JOIN category c WHERE c.parent_id = NULL GROUP BY c.name)
//    Category getCategoriesWithNumberOfProducts(
//            @Param () ,
//            @Param() );
//
//    @Query(SELECT COUNT(p.name) AS "ProductsCount" , c.name AS "SubCategoryName" FROM product p
//            JOIN category c WHERE p.category_id = c.id AND c.parent_id IS NOT NULL GROUP BY c.name)
//            Category getSubCategoriesWithNumberOfProducts(
//                    @Param())


}
