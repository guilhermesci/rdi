package com.rdi.menu.controller;

import com.rdi.menu.dto.ProductStatusDTO;
import com.rdi.menu.exception.ProductNotAllowedToUpdateStatusException;
import com.rdi.menu.exception.ProductNotFoundException;
import com.rdi.menu.service.ProductStatusService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products/status")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductStatusController implements ProductStatusControllerDocs {

    private ProductStatusService productStatusService;

    @GetMapping
    public List<ProductStatusDTO> listAll(){
        return productStatusService.listAll();
    }

    @PatchMapping()
    public ProductStatusDTO updateProductStatus(@RequestBody @Valid ProductStatusDTO productStatusDTO)
            throws ProductNotFoundException, ProductNotAllowedToUpdateStatusException {
        ProductStatusService.setFirst(true);
        return productStatusService.updateProductStatus(productStatusDTO);
    }
}
