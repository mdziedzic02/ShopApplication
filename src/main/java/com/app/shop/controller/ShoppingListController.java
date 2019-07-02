//package com.app.shop.controller;
//
//import com.app.shop.dto.EntityCreatedDto;
//import com.app.shop.dto.ShoppingListDto;
//import com.app.shop.service.ShoppingListService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class ShoppingListController {
//
//    @Autowired
//    private ShoppingListService shoppingListService;
//
//    @RequestMapping(method = RequestMethod.PUT, value = "/shoppingList")
//    public ResponseEntity<?> create(@RequestBody ShoppingListDto shoppingListDto) {
//        final Integer id = shoppingListService.add(shoppingListDto);
//        return new ResponseEntity<>(new EntityCreatedDto(id), HttpStatus.CREATED);
//    }
//
//    @RequestMapping(method = RequestMethod.POST, value = "/shoppingList/{id}")
//    @ResponseBody
//    public ResponseEntity<?> update(@RequestBody ShoppingListDto shoppingListDto, @PathVariable int id) {
//        return shoppingListService.update(id, shoppingListDto)
//                .map(shoppingList -> new ResponseEntity<>(HttpStatus.OK))
//                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE, value = "/shoppingList/{id}")
//    public ResponseEntity<?> delete(@PathVariable int id) {
//        return shoppingListService.remove(id)
//                .map(shoppingList -> new ResponseEntity<>(HttpStatus.OK))
//                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/shoppingList/{id}")
//    public ResponseEntity<?> get(@PathVariable int id) {
//        return shoppingListService.get(id)
//                .map(shoppingListDto -> new ResponseEntity<>(shoppingListDto, HttpStatus.OK))
//                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/shoppingList")
//    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
//                                    @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
//        return new ResponseEntity<>(shoppingListService.getAll(PageRequest.of(page, pageSize)), HttpStatus.OK);
//    }
//}
