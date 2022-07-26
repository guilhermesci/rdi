package com.rdi.menu.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rdi.menu.dto.ProductStatusDTO;
import com.rdi.menu.entity.ProductStatus;
import com.rdi.menu.enums.Status;

import java.util.List;

import static com.rdi.menu.utils.ProductUtils.*;

public class ProductStatusUtils {

    public static ProductStatus createFakeProductStatus() {
        return ProductStatus.builder()
                .id(1L)
                .status(Status.ACTIVE)
                .product(createFakeProduct())
                .build();
    }

    public static ProductStatusDTO createFakeProductStatusDTO() {
        return ProductStatusDTO.builder()
                .id(1L)
                .status(Status.ACTIVE)
                .product(createFakeProductDTO())
                .build();
    }

    public static List<ProductStatusDTO> createFakeProductStatusDTOList() {
        return List.of(createFakeProductStatusDTO());
    }

    public static ProductStatusDTO createInvalidFakeProductStatusDTO() {
        return ProductStatusDTO.builder()
                .id(9999L)
                .product(createInvalidFakeProductDTO())
                .build();
    }

    public static String asJsonString(ProductStatusDTO productStatusDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModules(new JavaTimeModule());

            return objectMapper.writeValueAsString(productStatusDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
