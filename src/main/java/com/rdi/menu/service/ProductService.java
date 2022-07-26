package com.rdi.menu.service;

import com.rdi.menu.dto.ProductComponentsDTO;
import com.rdi.menu.dto.ProductDTO;
import com.rdi.menu.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDTO> listAll(){
        return productRepository
                .findAll()
                .stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    public List<ProductComponentsDTO> listAllComponents(){
        return productRepository
                .findAll()
                .stream()
                .map(ProductComponentsDTO::new)
                .map(x -> x.setProductChild(productRepository.findByChildId(x.getId())))
                .collect(Collectors.toList());
    }

}
