package com.rdi.menu.service;

import com.rdi.menu.dto.ProductDTO;
import com.rdi.menu.dto.ProductStatusDTO;
import com.rdi.menu.entity.Product;
import com.rdi.menu.entity.ProductStatus;
import com.rdi.menu.enums.Status;
import com.rdi.menu.enums.Type;
import com.rdi.menu.exception.ProductNotAllowedToUpdateStatusException;
import com.rdi.menu.exception.ProductNotFoundException;
import com.rdi.menu.repository.ProductRepository;
import com.rdi.menu.repository.ProductStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductStatusService {

    public static boolean first;
    private final ProductStatusRepository productStatusRepository;
    private final ProductRepository productRepository;

    public List<ProductStatusDTO> listAll(){
        return productStatusRepository
                .findAll()
                .stream()
                .map(ProductStatusDTO::new)
                .collect(Collectors.toList());
    }

    public ProductStatusDTO findById(Long id) throws ProductNotFoundException {
        ProductStatus foundProductStatus = productStatusRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return new ProductStatusDTO(foundProductStatus);
    }

    public ProductStatusDTO updateProductStatus(ProductStatusDTO productStatusDTO)
            throws ProductNotFoundException, ProductNotAllowedToUpdateStatusException {

        Product product = verifyIfProductExists(productStatusDTO.getId());
        productStatusDTO.setProduct(new ProductDTO(product));

        if (verifyIfAllowedToUpdateProductStatus(productStatusDTO)) {
            productStatusRepository.save(new ProductStatus(productStatusDTO));
        } else if (isFirst()) {
            throw new ProductNotAllowedToUpdateStatusException(productStatusDTO.getId());
        }
        if (isFirst()) { setFirst(false); }

        product.getChild()
                .forEach(x -> updateProductStatus(ProductStatusDTO
                        .builder()
                        .id(x.getId())
                        .status(productStatusDTO.getStatus())
                        .build())
                );

        return productStatusDTO;
    }

    private Product verifyIfProductExists(Long id) throws ProductNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    private Boolean verifyIfAllowedToUpdateProductStatus(ProductStatusDTO productStatusDTO){
        if (productStatusDTO.getProduct().getType() == Type.PRODUCT){
            return true;
        }else{
            List<Long> childIds = productRepository.findByChildId(productStatusDTO.getId())
                    .stream()
                    .map(x -> x.getId())
                    .collect(Collectors.toList());

            if (productStatusDTO.getProduct().getType() == Type.CHOICE){
                if (productStatusDTO.getStatus() == Status.ACTIVE){
                    return !productStatusRepository.findByStatusAndIdIn(Status.ACTIVE, childIds).isEmpty();
                }else{
                    return productStatusRepository.findByStatusAndIdIn(Status.ACTIVE, childIds).isEmpty();
                }
            }else{
                if (productStatusDTO.getStatus() == Status.ACTIVE){
                    return productStatusRepository.findByStatusAndIdIn(Status.INACTIVE, childIds).isEmpty();
                }else{
                    return !productStatusRepository.findByStatusAndIdIn(Status.INACTIVE, childIds).isEmpty();
                }
            }
        }
    }

    public static boolean isFirst() {
        return first;
    }

    public static void setFirst(boolean first) {
        ProductStatusService.first = first;
    }
}
