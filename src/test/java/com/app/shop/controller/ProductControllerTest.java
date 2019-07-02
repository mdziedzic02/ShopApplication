package com.app.shop.controller;

import com.app.shop.common.ControllerTest;
import com.app.shop.data.CategoryDataService;
import com.app.shop.data.ProductDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest extends ControllerTest {

    @Autowired
    private CategoryDataService categoryDataService;

    @Autowired
    private ProductDataService productDataService;

    private final String PRODUCT_NAME_1 = "name";
    private final String PRODUCT_NAME_2 = "name2";

    @Test
    public void shouldGetAll() throws Exception {
        IntStream.rangeClosed(1, 7).forEach(id -> createProduct(String.valueOf(id)));
        chrome.perform(MockMvcRequestBuilders.get("/products")
                .param(QUERY_PAGE, "0")
                .param(QUERY_PAGE_SIZE, "10"))
                .andDo(this::tapError)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pageable.offset").value(0))
                .andExpect(jsonPath("$.pageable.pageSize").value(10))
                .andExpect(jsonPath("$.totalElements").value(9));
    }

    @Test
    public void shouldGet() throws Exception {
        final var productId = createProduct(PRODUCT_NAME_1);

        chrome.perform(MockMvcRequestBuilders.get("/products/{id}", productId))
                .andDo(this::tapError)
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAdd() throws Exception {
        final var category = categoryDataService.create("Category_1");
        final var requestBody = Map.ofEntries(
                Map.entry("name", PRODUCT_NAME_2),
                Map.entry("price", BigDecimal.ONE),
                Map.entry("quantity", 1),
                Map.entry("categoryId", category.getId())
        );

        chrome.perform(MockMvcRequestBuilders.put("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(requestBody)))
                .andDo(this::tapError)
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldUpdate() throws Exception {
        final var productId = createProduct(PRODUCT_NAME_1);
        final var requestBody = Collections.singletonMap("name", PRODUCT_NAME_2);

        chrome.perform(MockMvcRequestBuilders.post("/products/{id}", productId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(requestBody)))
                .andDo(this::tapError)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(PRODUCT_NAME_2));
    }

    @Test
    public void shouldDelete() throws Exception {
        final var productId = createProduct(PRODUCT_NAME_1);

        chrome.perform(MockMvcRequestBuilders.delete("/products/{id}", productId))
                .andDo(this::tapError)
                .andExpect(status().isOk());


    }

    private Integer createProduct(String name) {
        final var category = categoryDataService.create("Category_1");
        final var product = productDataService.create(category, name, BigDecimal.TEN, 1);

        return product.getId();
    }

}
