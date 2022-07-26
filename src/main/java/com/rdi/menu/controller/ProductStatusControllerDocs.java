package com.rdi.menu.controller;

import com.rdi.menu.dto.ProductDTO;
import com.rdi.menu.dto.ProductStatusDTO;
import com.rdi.menu.exception.ProductNotAllowedToUpdateStatusException;
import com.rdi.menu.exception.ProductNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Api("Manages Product's Status")
public interface ProductStatusControllerDocs {

    @ApiOperation(value = "Returns a list of all product's status")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success list of all product's status."),
    })
    List<ProductStatusDTO> listAll();

    @ApiOperation(value = "Update a product's status by a given valid Id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success product's status updated."),
            @ApiResponse(code = 404, message = "Product with given id not found."),
            @ApiResponse(code = 404, message = "Product with id can not be updated.")
    })
    ProductStatusDTO updateProductStatus(@RequestBody @Valid ProductStatusDTO productStatusDTO)
            throws ProductNotFoundException, ProductNotAllowedToUpdateStatusException;
}
