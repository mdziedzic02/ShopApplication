package com.app.shop.controller;

import com.app.shop.common.ControllerTest;
import com.app.shop.dto.CategoryDto;
import com.app.shop.service.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;
import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest extends ControllerTest {

    @Autowired
    private CategoryService categoryService;

    private final String CATEGORY_NAME_1 = "name";
    private final String CATEGORY_NAME_2 = "name2";

    @Test
    public void shouldGet() throws Exception {
        final var categoryId = createCategory(CATEGORY_NAME_1);

        chrome.perform(MockMvcRequestBuilders.get("/category/{id}", categoryId))
                .andDo(this::tapError)
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAdd() throws Exception {
        final var requestBody = Collections.singletonMap("name", CATEGORY_NAME_2);

        chrome.perform(MockMvcRequestBuilders.put("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(requestBody)))
                .andDo(this::tapError)
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldUpdate() throws Exception {
        final var categoryId = createCategory(CATEGORY_NAME_1);
        final var requestBody = Collections.singletonMap("name", CATEGORY_NAME_2);

        chrome.perform(MockMvcRequestBuilders.post("/category/{id}", categoryId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(requestBody)))
                .andDo(this::tapError)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(CATEGORY_NAME_2));
    }

    @Test
    public void shouldDelete() throws Exception {
        final var categoryId = createCategory(CATEGORY_NAME_1);

        chrome.perform(MockMvcRequestBuilders.delete("/category/{id}", categoryId))
                .andDo(this::tapError)
                .andExpect(status().isOk());

        final var categoryExists = categoryService.exist(categoryId);
        assertThat(categoryExists).isFalse();
    }

    private Integer createCategory(String name) {
        return categoryService.add(new CategoryDto(name));
    }
}
