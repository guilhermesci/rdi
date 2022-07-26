package com.rdi.menu.utils;

import com.rdi.menu.dto.ProductComponentsDTO;
import com.rdi.menu.dto.ProductDTO;
import com.rdi.menu.entity.Product;
import com.rdi.menu.enums.Type;

import java.util.List;

public class ProductUtils {

    public static Product createFakeProduct() {
        return new Product(createFakeProductDTO());
    }

    public static ProductDTO createFakeProductDTO() {
        return ProductDTO.builder()
                .id(1L)
                .name("ProductFake")
                .type(Type.PRODUCT)
                .build();
    }

    public static ProductComponentsDTO createFakeProductComponentDTOTypeChoice() {
        return ProductComponentsDTO.builder()
                .id(2L)
                .name("ProductChoiceFake")
                .type(Type.CHOICE)
                .child(createFakeProductDTOList())
                .build();
    }

    public static ProductDTO createFakeProductComponentDTOTypeValueMeal() {
        return ProductDTO.builder()
                .id(3L)
                .name("ProductValueMealFake")
                .type(Type.VALUE_MEAL)
                .build();
    }

    public static ProductDTO createInvalidFakeProductDTO() {
        return ProductDTO.builder()
                .id(9999L)
                .name("ProductFake")
                .type(Type.PRODUCT)
                .build();
    }

    public static List<ProductDTO> createFakeProductDTOList() {
        return List.of(createFakeProductDTO());
    }

    public static List<ProductComponentsDTO> createFakeProductComponentsDTOList() {
        return List.of(ProductComponentsDTO.builder()
                .id(2L)
                .name("ProductChoiceFake")
                .type(Type.CHOICE)
                .child(createFakeProductDTOList())
                .build());
    }
    
}
