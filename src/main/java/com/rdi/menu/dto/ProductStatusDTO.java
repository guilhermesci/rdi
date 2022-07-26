package com.rdi.menu.dto;

import com.rdi.menu.entity.ProductStatus;
import com.rdi.menu.enums.Status;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductStatusDTO {

    @NotNull
    private Long id;

    private ProductDTO product = new ProductDTO();

    @NotNull
    private Status status;

    public ProductStatusDTO(ProductStatus productStatus){
        this.id         = productStatus.getId();
        this.product    = new ProductDTO(productStatus.getProduct());
        this.status     = productStatus.getStatus();
    }
}
