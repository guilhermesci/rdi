package com.rdi.menu.service;

import com.rdi.menu.dto.ProductStatusDTO;
import com.rdi.menu.enums.Status;
import com.rdi.menu.exception.ProductNotAllowedToUpdateStatusException;
import com.rdi.menu.exception.ProductNotFoundException;
import com.rdi.menu.repository.ProductRepository;
import com.rdi.menu.repository.ProductStatusRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static com.rdi.menu.utils.ProductStatusUtils.createFakeProductStatusDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductStatusServiceTest {

    private static final Long INVALID_PRODUCT_ID = 9999L;
    private static final Status NEW_STATUS = Status.INACTIVE;
    private MockMvc mockMvc;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductStatusRepository productStatusRepository;

    @InjectMocks
    private ProductStatusService productStatusService;

    @Test
    void whenInvalidIdIsGivenThenThrowAnException() {
        // when
        when(productStatusRepository.findById(INVALID_PRODUCT_ID)).thenReturn(Optional.empty());
        // then
        assertThrows(ProductNotFoundException.class, () -> productStatusService.findById(INVALID_PRODUCT_ID));
    }

//    @Test
//    void whenAnAllowedProductIsGivenThenReturnAUpdatedProduct() throws ProductNotFoundException {
//        // given
//        ProductStatusDTO expectedProductStatusDTO = createFakeProductStatusDTO();
//        expectedProductStatusDTO.setStatus(NEW_STATUS);
//        // when
//        when(productStatusService.updateProductStatus(expectedProductStatusDTO)).thenReturn(expectedProductStatusDTO);
//        // then
//        ProductStatusDTO foundProductStatusDTO = productStatusService.findById(expectedProductStatusDTO.getId());
//        assertThat(foundProductStatusDTO, is(equalTo(expectedProductStatusDTO)));
//    }
//
//    @Test
//    void whenANotAllowedProductIsGivenThenReturnNotAllowedToUpdateStatusException() throws ProductNotAllowedToUpdateStatusException {
//        // given
//        ProductStatusDTO productStatusDTO = createFakeProductStatusDTO();
////        productStatusDTO.setId(9L);
//        // when
//        when(productStatusService.updateProductStatus(productStatusDTO)).thenReturn(productStatusDTO);
//        // then
//        assertThrows(ProductNotAllowedToUpdateStatusException.class, () -> productStatusService.updateProductStatus(productStatusDTO));
//    }
    
//    @Test
//    void whenListProductStatusIsCalledThenReturnAListOfProductStatus() {
//        // given
//        List<ProductStatusDTO> productStatusDTOList = createFakeProductStatusDTOList();
//        ProductStatusDTO expectedProductStatusDTO = createFakeProductStatusDTO();
//
//        // when
//        when(productStatusService.listAll()).thenReturn((productStatusDTOList));
//
//        //then
//        List<ProductStatusDTO> foundProductStatusDTO = productStatusService.listAll();
//
//        assertThat(foundProductStatusDTO, is(not(empty())));
//        assertThat(foundProductStatusDTO.get(0).getId(), is(equalTo(expectedProductStatusDTO.getId())));
//    }

}
