package com.rdi.menu.dto;

import com.rdi.menu.entity.Product;
import com.rdi.menu.enums.Type;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductComponentsDTO {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Type type;

    private List<ProductDTO> child = new ArrayList<>();

    public ProductComponentsDTO(Product product){
        this.id     = product.getId();
        this.name   = product.getName();
        this.type   = product.getType();
    }

    public ProductComponentsDTO setProductChild(List<Product> products) {
        this.child.addAll(products.stream().map(ProductDTO::new).collect(Collectors.toList()));
        return this;
    }

}
