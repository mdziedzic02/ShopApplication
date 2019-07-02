//package com.app.shop.service;
//
//import com.app.shop.dao.ShoppingListRepository;
//import com.app.shop.dto.ShoppingListDto;
//import com.app.shop.entity.ShoppingList;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//@Transactional
//public class ShoppingListService {
//
//    @Autowired
//    private ShoppingListRepository shoppingListRepository;
//
//    public int add(ShoppingListDto shoppingListDto) {
//        final ShoppingList shoppingList = new ShoppingList(shoppingListDto.getName());
//        return shoppingListRepository.save(shoppingList).getId();
//    }
//
//    public Optional<ShoppingListDto> get(int id) {
//        return shoppingListRepository.findById(id).map(shoppingList -> new ShoppingListDto(shoppingList.getName()));
//    }
//
//    public Page<ShoppingListDto> getAll(PageRequest pageRequest) {
//        final Page<ShoppingList> shoppingLists = shoppingListRepository.findAll(pageRequest);
//        final List<ShoppingListDto> shoppingListDtoList = shoppingLists.get().map(shoppingList -> new ShoppingListDto(shoppingList.getName())).collect(Collectors.toList());
//        return new PageImpl<>(shoppingListDtoList, pageRequest, shoppingLists.getNumberOfElements());
//    }
//
//    public Optional<?> update(int id, ShoppingListDto shoppingListDto) {
//        return shoppingListRepository.findById(id)
//                .map(shoppingList -> {
//                    shoppingList.setName(shoppingListDto.getName());
//                    shoppingListRepository.save(shoppingList);
//                    return shoppingList;
//                });
//    }
//
//    public Optional<?> remove(int id) {
//        return shoppingListRepository.findById(id)
//                .map(shoppingList -> {
//                    shoppingListRepository.deleteById(id);
//                    return shoppingList;
//                });
//    }
//
//    public boolean exist(int id) {
//        return shoppingListRepository.existsById(id);
//    }
//}
