//package com.app.shop.service;
//
//import com.app.shop.dao.ShoppingListRepository;
//import com.app.shop.dto.ShoppingListDto;
//import com.app.shop.entity.ShoppingList;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.Optional;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//public class ShoppingListServiceTest {
//
//    @Autowired
//    private ShoppingListRepository shoppingListRepository;
//    @Autowired
//    private ShoppingListService shoppingListService;
//    private final String NAME = "name1";
//    private final String NAME2 = "name2";
//
//    @Test
//    public void shouldAddShoppingListWhenDoesNotExist() {
//        ShoppingListDto shoppingListDto = new ShoppingListDto(NAME);
//        int identifier = shoppingListService.add(shoppingListDto);
//        Optional<ShoppingListDto> shoppingListVerification = shoppingListService.get(identifier);
//        var v = shoppingListVerification.get();
//
//        assertEquals(shoppingListDto, v);
//    }
//
//    @Test
//    public void shouldReturnShoppingListWhenExist() {
//        ShoppingList givenShoppingList = shoppingListRepository.save(new ShoppingList(NAME));
//        Optional<ShoppingListDto> expectedDto = shoppingListService.get(givenShoppingList.getId());
//        var v = expectedDto.get();
//
//        assertTrue(shoppingListRepository.existsById(givenShoppingList.getId()));
//        assertEquals(v.getName(), givenShoppingList.getName());
//    }
//
//    @Test
//    public void shouldDeleteShoppingListWhenExist() {
//        ShoppingListDto shoppingListDto = new ShoppingListDto(NAME);
//        int identifier = shoppingListService.add(shoppingListDto);
//
//        shoppingListService.remove(identifier);
//
//        String shoppingListExist = assertShoppingListDoesNotExist(identifier);
//        assertEquals("Shopping list not found", shoppingListExist);
//    }
//
//    private String assertShoppingListDoesNotExist(int identifier) {
//        if(shoppingListService.exist(identifier)){
//            return "Shopping list exist";
//        }else{
//            return "Shopping list not found";
//        }
//    }
//
//    @Test
//    public void shouldUpdateShoppingList() {
//        ShoppingListDto shoppingListDto = new ShoppingListDto(NAME);
//        int identifier = shoppingListService.add(shoppingListDto);
//
//        shoppingListService.update(identifier, new ShoppingListDto(NAME2));
//        Optional<ShoppingListDto> newShoppingListDto = shoppingListService.get(identifier);
//        var v = newShoppingListDto.get();
//
//        assertEquals(NAME2, v.getName());
//    }
//}
