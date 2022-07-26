package com.rdi.menu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductNotAllowedToUpdateStatusException extends RuntimeException{

    public ProductNotAllowedToUpdateStatusException(Long id) {
        super(String.format("Product with id %s can not be updated.", id));
    }

    private ProductNotAllowedToUpdateStatusException(){};
}
