package com.rdi.menu.dto;

import com.rdi.menu.entity.Product;
import com.rdi.menu.enums.Type;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Type type;

    public ProductDTO(Product product){
        this.id     = product.getId();
        this.name   = product.getName();
        this.type   = product.getType();
    }

}
