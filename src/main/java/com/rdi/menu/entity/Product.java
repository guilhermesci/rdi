package com.rdi.menu.entity;

import com.rdi.menu.dto.ProductDTO;
import com.rdi.menu.enums.Type;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRD_ID")
    private Long id;

    @Column(name = "PRD_NAME", nullable = false)
    private String name;

    @Column(name = "TYPE", nullable = false)
    private Integer type;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_components",
            joinColumns = @JoinColumn(name = "CHILD_PRD_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRD_ID"))
    private Set<Product> child = new HashSet<>();

    public Product (Long id, String name, Type type){
        this.id = id;
        this.name = name;
        setType(type);
    }

    public Product (ProductDTO productDTO){
        this.id     = productDTO.getId();
        this.name   = productDTO.getName();
        setType(productDTO.getType());
    }

    public Type getType(){
        return Type.valueOf(type);
    }

    public void setType(Type type){
        if (type != null){
            this.type = type.getCode();
        }
    }
}
