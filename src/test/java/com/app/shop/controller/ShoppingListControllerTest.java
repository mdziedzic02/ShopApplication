//package com.app.shop.controller;
//
//import com.app.shop.dto.ShoppingListDto;
//import com.app.shop.service.ShoppingListService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.io.UnsupportedEncodingException;
//import java.util.Collections;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ShoppingListControllerTest {
//
//    @Autowired
//    private MockMvc chrome;
//    @Autowired
//    private ShoppingListService shoppingListService;
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private final String SHOPPINGLIST_NAME_1 = "name";
//    private final String SHOPPINGLIST_NAME_2 = "name2";
//
//    @Test
//    public void shouldGet() throws Exception {
//        final var shoppingListId = createShoppingList(SHOPPINGLIST_NAME_1);
//
//        chrome.perform(MockMvcRequestBuilders.get("/shoppingList/{id}", shoppingListId))
//                .andDo(this::tapError)
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void shouldAdd() throws Exception {
//        final var requestBody = Collections.singletonMap("name", SHOPPINGLIST_NAME_2);
//
//        chrome.perform(MockMvcRequestBuilders.put("/shoppingList")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJson(requestBody)))
//                .andDo(this::tapError)
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    public void shouldUpdate() throws Exception {
//        final var shoppingListId = createShoppingList(SHOPPINGLIST_NAME_1);
//        final var requestBody = Collections.singletonMap("name", SHOPPINGLIST_NAME_2);
//
//        chrome.perform(MockMvcRequestBuilders.post("/shoppingList/{id}", shoppingListId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJson(requestBody)))
//                .andDo(this::tapError)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value(SHOPPINGLIST_NAME_2));
//
//
//    @Test
//    public void shouldDelete() throws Exception {
//        final var shoppingListId = createShoppingList(SHOPPINGLIST_NAME_1);
//
//        chrome.perform(MockMvcRequestBuilders.delete("/shoppingList/{id}", shoppingListId))
//                .andDo(this::tapError)
//                .andExpect(status().isOk());
//
//
//    }
//
//    private Integer createShoppingList(String name) {
//        return shoppingListService.add(new ShoppingListDto(name));
//    }
//
//    private String asJson(Object o) throws JsonProcessingException {
//        return objectMapper.writeValueAsString(o);
//    }
//
//    private void tapError(MvcResult mvcResult) throws UnsupportedEncodingException {
//        //if (mvcResult.getResponse().getStatus() >= 400) {
//        System.out.println(mvcResult.getResponse().getContentAsString());
//        // }
//    }
//}
