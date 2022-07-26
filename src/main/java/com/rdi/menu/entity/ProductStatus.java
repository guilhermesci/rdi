package com.rdi.menu.entity;

import com.rdi.menu.dto.ProductStatusDTO;
import com.rdi.menu.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUCT_STATUS")
public class ProductStatus {

    @Id
    @Column(name = "PRD_ID")
    private Long id;

    @Column(name = "STATUS")
    private Status status;

    @OneToOne
    @MapsId
    @JoinColumn(name = "PRD_ID")
    private Product product;

    public ProductStatus(ProductStatusDTO productStatusDTO) {
        this.id         = productStatusDTO.getId();
        this.status     = productStatusDTO.getStatus();
        this.product    = new Product(productStatusDTO.getProduct());
    }
}
