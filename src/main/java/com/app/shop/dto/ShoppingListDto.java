//package com.app.shop.dto;
//
//import java.util.Objects;
//
//public class ShoppingListDto {
//
//    private String name;
//
//    protected ShoppingListDto() {
//    }
//
//    public ShoppingListDto(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof ShoppingListDto)) return false;
//        ShoppingListDto that = (ShoppingListDto) o;
//        return Objects.equals(name, that.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name);
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public String toString() {
//        return "ShoppingListDto{" +
//                "name='" + name + '\'' +
//                '}';
//    }
//}
