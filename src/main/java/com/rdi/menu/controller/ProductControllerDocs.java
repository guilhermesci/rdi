package com.rdi.menu.controller;

import com.rdi.menu.dto.ProductComponentsDTO;
import com.rdi.menu.dto.ProductDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@Api("Manages Products")
public interface ProductControllerDocs {

    @ApiOperation(value = "Returns a list of all products")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success list of all products."),
    })
    List<ProductDTO> listAll();

    @ApiOperation(value = "Returns a list of all products and it's components")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success list of all products and it's components."),
    })
    List<ProductComponentsDTO> listAllComponents();
}
