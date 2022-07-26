package com.rdi.menu.controller;

import com.rdi.menu.dto.ProductComponentsDTO;
import com.rdi.menu.dto.ProductDTO;
import com.rdi.menu.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController implements ProductControllerDocs {

    private final ProductService productService;

    @GetMapping
    public List<ProductDTO> listAll(){
        return productService.listAll();
    }

    @GetMapping("/components")
    public List<ProductComponentsDTO> listAllComponents(){
        return productService.listAllComponents();
    }
}
